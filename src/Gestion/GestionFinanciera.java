package Gestion;

import Clases.Factura;
import Clases.GastoOperativo;
import Clases.OrdenServicio;
import Clases.EstadoOrden;
import java.util.ArrayList;


public class GestionFinanciera {

    private NodoFactura raiz;
    private ArrayList<GastoOperativo> listaGastos;
    private int secuencialFactura;

    public GestionFinanciera() {
        this.raiz = null;
        this.listaGastos = new ArrayList<>();
        this.secuencialFactura = 1;
    }

    public OrdenServicio buscarOrdenParaFacturar(String cedulaCliente, GestionOrdenesServicio gestorOrdenes) {
        if (gestorOrdenes == null) return null;

        for (int i = 0; i < gestorOrdenes.getSize(); i++) {
            try {
                OrdenServicio orden = gestorOrdenes.getValor(i);
                // Validación estricta: misma cédula, activa y concluida en el taller
                if (orden.getCedulaCliente().trim().equals(cedulaCliente.trim())
                        && orden.isActivo()
                        && orden.getEstado() == EstadoOrden.FINALIZADO) {
                    return orden;
                }
            } catch (Exception e) {
                System.out.println("Error de lectura en el flujo de órdenes: " + e.getMessage());
            }
        }
        return null; // No hay órdenes disponibles para liquidar
    }

    /**Calcula de forma automatizada los costos financieros asociados a los materiales e insumos registrados dinámicamente en la orden de servicio.
     */
    public double calcularSubtotalMateriales(OrdenServicio orden) {
        double subtotalMateriales = 0.0;
        if (orden.getMateriales() != null) {
            for (String material : orden.getMateriales()) {
                String m = material.toLowerCase().trim();
                // Tabulador de costos del taller mecánico (2026)
                if (m.contains("aceite")) subtotalMateriales += 35.00;
                else if (m.contains("filtro")) subtotalMateriales += 12.00;
                else if (m.contains("pastilla") || m.contains("freno")) subtotalMateriales += 28.00;
                else if (m.contains("bujia") || m.contains("bujía")) subtotalMateriales += 6.50;
                else if (m.contains("llanta") || m.contains("neumatico")) subtotalMateriales += 75.00;
                else subtotalMateriales += 15.00; // Tarifa base para insumos menores no tipificados
            }
        }
        return subtotalMateriales;
    }

    /**Genera de forma incremental y segura el próximo número de comprobante secuencial exigido */

    public String generarSiguienteNumeroComprobante() {
        return String.format("001-001-%08d", secuencialFactura);
    }

    /**Auxiliar centralizado para el cálculo automático del impuesto fiscal (15%)*/

    public double calcularIvaAutomatizado(double subtotal) {
        return subtotal * 0.15;
    }

    /** Emisión y registro de una nueva factura en el árbol binario de búsqueda*/
    public boolean emitirFactura(Factura nueva) {
        if (buscarFactura(nueva.getId_comp()) != null) {
            return false; /**Evita la duplicidad de claves de comprobantes*/
        }
        raiz = insertarRecursivo(raiz, nueva);
        secuencialFactura++; /**Incrementa automáticamente la numeración tras el éxito de inserción*/
        return true;
    }

    private NodoFactura insertarRecursivo(NodoFactura actual, Factura nueva) {
        if (actual == null) {
            return new NodoFactura(nueva);
        }

        /**Ubicación en las ramas comparando las cadenas de caracteres correspondientes al ID*/
        if (nueva.getId_comp().compareTo(actual.getFactura().getId_comp()) < 0) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), nueva));
        } else if (nueva.getId_comp().compareTo(actual.getFactura().getId_comp()) > 0) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), nueva));
        }
        return actual;
    }

    /**Búsqueda binaria recursiva optimizada sobre las claves del árbol ABB (O(log n)*/
    public Factura buscarFactura(String id_comp) {
        NodoFactura nodoEncontrado = buscarRecursivo(raiz, id_comp);
        return (nodoEncontrado != null) ? nodoEncontrado.getFactura() : null;
    }

    private NodoFactura buscarRecursivo(NodoFactura actual, String id_comp) {
        if (actual == null || actual.getFactura().getId_comp().equals(id_comp)) {
            return actual;
        }
        if (id_comp.compareTo(actual.getFactura().getId_comp()) < 0) {
            return buscarRecursivo(actual.getIzquierdo(), id_comp);
        }
        return buscarRecursivo(actual.getDerecho(), id_comp);
    }

    /**Extracción ordenada ascendente de facturas mediante el algoritmo de recorrido Inorde*/
    public ArrayList<Factura> obtenerFacturasOrdenadas() {
        ArrayList<Factura> listaPlana = new ArrayList<>();
        recorridoInorder(raiz, listaPlana);
        return listaPlana;
    }

    private void recorridoInorder(NodoFactura nodo, ArrayList<Factura> lista) {
        if (nodo != null) {
            recorridoInorder(nodo.getIzquierdo(), lista);
            lista.add(nodo.getFactura());
            recorridoInorder(nodo.getDerecho(), lista);
        }
    }

    /**Actualización del metodo de pago de una factura válida*/
    public boolean actualizarMetodoPago(String id_comp, String nuevoMetodo, String usuario) {
        Factura f = buscarFactura(id_comp);
        if (f != null && f.isActivo()) {
            f.setMet_pago(nuevoMetodo);
            f.setUsuarioModificacion(usuario);
            f.setFechaModificacion("2026-06-25");
            return true;
        }
        return false;
    }

    /**Anulación contable lógica de un comprobante para mantener la trazabilidad de auditoría*/
    public boolean eliminarFacturaLogica(String id_comp, String motivo, String usuario) {
        Factura f = buscarFactura(id_comp);
        if (f != null) {
            f.setActivo(false);
            f.setMotivoAnulacion(motivo);
            f.setUsuarioModificacion(usuario);
            f.setFechaModificacion("2026-06-25");
            return true;
        }
        return false;
    }

    /**Inserta un nuevo gasto operativo en el registro de egresos*/
    public void registrarGasto(GastoOperativo gasto) {
        this.listaGastos.add(gasto);
    }

    /**Devuelve la totalidad de los egresos operacionales registrados*/
    public ArrayList<GastoOperativo> obtenerGastos() {
        return this.listaGastos;
    }

    /**Consolida los flujos de caja cruzando el árbol de ingresos y la lista de gastos.
     * Genera el desglose contable, calcula la rentabilidad real y evalúa descuadres de efectivo.
     */
    public String generarCierreCajaYRentabilidad(double efectivoFisicoContado) {
        ArrayList<Factura> facturas = obtenerFacturasOrdenadas();

        double totalEfectivoSistema = 0;
        double totalTransferencias = 0;
        double totalTarjetas = 0;
        int facturasValidas = 0;
        int facturasAnuladas = 0;

        /**esglose analítico de los métodos de pago de ingresos activos*/
        for (Factura f : facturas) {
            if (f.isActivo()) {
                facturasValidas++;
                switch (f.getMet_pago().toLowerCase()) {
                    case "efectivo":
                        totalEfectivoSistema += f.getTotal();
                        break;
                    case "transferencia":
                        totalTransferencias += f.getTotal();
                        break;
                    case "tarjeta":
                        totalTarjetas += f.getTotal();
                        break;
                }
            } else {
                facturasAnuladas++;
            }
        }


        double ingresosTotales = totalEfectivoSistema + totalTransferencias + totalTarjetas;
        double margenErrorCaja = efectivoFisicoContado - totalEfectivoSistema;

        StringBuilder sb = new StringBuilder();
        sb.append("===================================================\n");
        sb.append("         INFORME DE AUDITORÍA Y CIERRE DE CAJA      \n");
        sb.append("===================================================\n");
        sb.append("Fecha de Conciliación:       2026-06-25\n");
        sb.append("Comprobantes Emitidos:       ").append(facturasValidas).append("\n");
        sb.append("Comprobantes Anulados (Log): ").append(facturasAnuladas).append("\n");
        sb.append("---------------------------------------------------\n");
        sb.append("CLASIFICACIÓN DE INGRESOS POR MEDIO DE PAGO:\n");
        sb.append(" -> Efectivo en Sistema:            $").append(String.format("%.2f", totalEfectivoSistema)).append("\n");
        sb.append(" -> Canales de Transferencia:       $").append(String.format("%.2f", totalTransferencias)).append("\n");
        sb.append(" -> Transacciones por Tarjeta:      $").append(String.format("%.2f", totalTarjetas)).append("\n");
        sb.append("INGRESOS TOTALES BRUTOS:            $").append(String.format("%.2f", ingresosTotales)).append("\n");
        sb.append("===================================================\n");
        sb.append("        CONCILIACIÓN Y MARGEN DE ERROR DE CAJA     \n");
        sb.append("===================================================\n");
        sb.append("Efectivo Físico Contado (Cajero):   $").append(String.format("%.2f", efectivoFisicoContado)).append("\n");
        sb.append("Efectivo Esperado (Flujo Sistema):  $").append(String.format("%.2f", totalEfectivoSistema)).append("\n");
        sb.append("DIFERENCIA CONCILIADA (Margen):     $").append(String.format("%.2f", margenErrorCaja)).append("\n");
        sb.append("---------------------------------------------------\n");

        if (Math.abs(margenErrorCaja) < 0.01) {
            sb.append("ESTADO CONTABLE: [CORRECTO] Caja cuadrada perfectamente sin novedades.\n");
        } else if (margenErrorCaja < 0) {
            sb.append("ESTADO CONTABLE: [ALERTA FALTANTE] Se detectó un faltante de $")
                    .append(String.format("%.2f", Math.abs(margenErrorCaja)))
                    .append(" en efectivo.\n");
        } else {
            sb.append("ESTADO CONTABLE: [ALERTA SOBRANTE] Se detectó un sobrante no registrado de $")
                    .append(String.format("%.2f", margenErrorCaja))
                    .append(" en la caja.\n");
        }
        sb.append("===================================================\n");

        return sb.toString();
    }
}