package Gestion;

import Clases.Factura;
import Clases.GastoOperativo;
import java.util.ArrayList;

public class GestionFinanciera {
    // Almacenamiento jerárquico dinámico para el control de ingresos (Árbol Binario de Búsqueda)
    private NodoFactura raiz;

    // Control y registro auxiliar de egresos del taller
    private ArrayList<GastoOperativo> listaGastos;

    public GestionFinanciera() {
        this.raiz = null;
        this.listaGastos = new ArrayList<>();
        simularDatosDisponibles();
    }

    /**
     * Carga de registros iniciales para demostración en memoria del sistema
     */
    private void simularDatosDisponibles() {
        // Registro de ingresos iniciales (Se auto-ordenan por ID dentro de las ramas del árbol)
        emitirFactura(new Factura("001-001-000000002", "2026-06-18", "Tarjeta", 50.0, 0.0, true, "", "admin", "", "CLV2", "1722222222", "ORD-02"));
        emitirFactura(new Factura("001-001-000000001", "2026-06-18", "Efectivo", 100.0, 10.0, true, "", "admin", "", "CLV1", "1711111111", "ORD-01"));
        emitirFactura(new Factura("001-001-000000003", "2026-06-18", "Transferencia", 200.0, 0.0, true, "", "admin", "", "CLV3", "1733333333", "ORD-03"));

        // Registro de egresos iniciales por costos operativos del taller
        registrarGasto(new GastoOperativo(1, "Compra de Aceite Multigrado y Filtros", 45.00, "Insumos", "2026-06-18"));
        registrarGasto(new GastoOperativo(2, "Pago de servicio de energía eléctrica del taller", 30.00, "Servicios Básicos", "2026-06-18"));
    }

    // =========================================================================
    // CONTROL DE INGRESOS (Estructura de Árbol ABB)
    // =========================================================================

    /**
     * Emisión y registro de una nueva factura en el árbol binario
     */
    public boolean emitirFactura(Factura nueva) {
        if (buscarFactura(nueva.getId_comp()) != null) {
            return false; // Evita duplicados de comprobantes
        }
        raiz = insertarRecursivo(raiz, nueva);
        return true;
    }

    private NodoFactura insertarRecursivo(NodoFactura actual, Factura nueva) {
        if (actual == null) {
            return new NodoFactura(nueva);
        }
        // Ubicación en las ramas del árbol comparando las cadenas de ID
        if (nueva.getId_comp().compareTo(actual.getFactura().getId_comp()) < 0) {
            actual.setIzquierdo(insertarRecursivo(actual.getIzquierdo(), nueva));
        } else if (nueva.getId_comp().compareTo(actual.getFactura().getId_comp()) > 0) {
            actual.setDerecho(insertarRecursivo(actual.getDerecho(), nueva));
        }
        return actual;
    }

    /**
     * Búsqueda binaria recursiva optimizada sobre los nodos del árbol
     */
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

    /**
     * Extracción ordenada de facturas mediante el algoritmo de recorrido Inorder
     */
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

    /**
     * Actualización del método de pago de una factura válida
     */
    public boolean actualizarMetodoPago(String id_comp, String nuevoMetodo, String usuario) {
        Factura f = buscarFactura(id_comp);
        if (f != null && f.isActivo()) {
            f.setMet_pago(nuevoMetodo);
            f.setUsuarioModificacion(usuario);
            f.setFechaModificacion("2026-06-18");
            return true;
        }
        return false;
    }

    /**
     * Anulación contable lógica de un comprobante para mantener la trazabilidad de auditoría
     */
    public boolean eliminarFacturaLogica(String id_comp, String motivo, String usuario) {
        Factura f = buscarFactura(id_comp);
        if (f != null) {
            f.setActivo(false);
            f.setMotivoAnulacion(motivo);
            f.setUsuarioModificacion(usuario);
            f.setFechaModificacion("2026-06-18");
            return true;
        }
        return false;
    }

    // =========================================================================
    // CONTROL DE GASTOS OPERATIVOS (EGRESOS)
    // =========================================================================

    /**
     * Inserta un nuevo gasto operativo en el registro de egresos
     */
    public void registrarGasto(GastoOperativo gasto) {
        this.listaGastos.add(gasto);
    }

    /**
     * Devuelve la totalidad de los egresos registrados
     */
    public ArrayList<GastoOperativo> obtenerGastos() {
        return this.listaGastos;
    }

    // =========================================================================
    // AUDITORÍA FINANCIERA: CIERRE DE JORNADA, CONCILIACIÓN Y BALANCES
    // =========================================================================

    /**
     * Consolida los flujos de caja cruzando el árbol de ingresos y la lista de gastos.
     * Genera el desglose contable, calcula la rentabilidad real del negocio y el margen de error.
     */
    public String generarCierreCajaYRentabilidad(double efectivoFisicoContado) {
        ArrayList<Factura> facturas = obtenerFacturasOrdenadas();

        double totalEfectivoSistema = 0;
        double totalTransferencias = 0;
        double totalTarjetas = 0;
        int facturasValidas = 0;
        int facturasAnuladas = 0;

        // Desglose analítico de los métodos de pago de ingresos activos
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

        // Procesamiento sumatorio de los egresos operacionales registrados
        double totalEgresos = 0;
        for (GastoOperativo g : listaGastos) {
            totalEgresos += g.getMonto();
        }

        double ingresosTotales = totalEfectivoSistema + totalTransferencias + totalTarjetas;
        double rentabilidadReal = ingresosTotales - totalEgresos;
        double margenErrorCaja = efectivoFisicoContado - totalEfectivoSistema;

        // Construcción y formateo del reporte de resultados para la consola visual
        StringBuilder sb = new StringBuilder();
        sb.append("===================================================\n");
        sb.append("         INFORME DE AUDITORÍA Y CIERRE DE CAJA      \n");
        sb.append("===================================================\n");
        sb.append("Fecha de Conciliación:       2026-06-18\n");
        sb.append("Comprobantes Emitidos:       ").append(facturasValidas).append("\n");
        sb.append("Comprobantes Anulados (Log): ").append(facturasAnuladas).append("\n");
        sb.append("---------------------------------------------------\n");
        sb.append("CLASIFICACIÓN DE INGRESOS POR MEDIO DE PAGO:\n");
        sb.append(" -> Efectivo en Sistema:            $").append(String.format("%.2f", totalEfectivoSistema)).append("\n");
        sb.append(" -> Canales de Transferencia:       $").append(String.format("%.2f", totalTransferencias)).append("\n");
        sb.append(" -> Transacciones por Tarjeta:      $").append(String.format("%.2f", totalTarjetas)).append("\n");
        sb.append("INGRESOS TOTALES BRUTOS:            $").append(String.format("%.2f", ingresosTotales)).append("\n");
        sb.append("---------------------------------------------------\n");
        sb.append("CONSOLIDACIÓN DE GASTOS OPERATIVOS:\n");
        sb.append(" -> Egresos Reconocidos del Taller: -$").append(String.format("%.2f", totalEgresos)).append("\n");
        sb.append("---------------------------------------------------\n");
        sb.append("CÁLCULO DE RENTABILIDAD DEL PERIODO:\n");
        sb.append(" => UTILIDAD NETO REAL DEL TALLER:  $").append(String.format("%.2f", rentabilidadReal)).append("\n");
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