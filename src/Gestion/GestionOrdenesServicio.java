package Gestion;

import Clases.EstadoOrden;
import Clases.OrdenServicio;
import java.util.ArrayList;
import java.time.LocalDate;


public class GestionOrdenesServicio {

    private ArrayList<OrdenServicio> ordenes;

    public GestionOrdenesServicio() {
        ordenes = new ArrayList<>();
    }

    /**Agregar Orden*/
    public boolean agregarOrden(OrdenServicio orden) {

        if (buscarOrden(orden.getCodigoOrden()) != -1) {
            return false;
        }

        ordenes.add(orden);
        return true;
    }

    /**Buscar Orden*/
    public int buscarOrden(String codigoOrden) {

        for (int i = 0; i < ordenes.size(); i++) {

            if (ordenes.get(i).getCodigoOrden().equals(codigoOrden)) {
                return i;
            }
        }

        return -1;
    }

    /**Obener Orden*/
    public OrdenServicio getValor(int indice) throws Exception {

        if (indice < 0 || indice >= ordenes.size()) {
            throw new Exception("Indice fuera de rango");
        }

        return ordenes.get(indice);
    }

    /**Cambiar estado*/
    public boolean actualizarEstado(String codigoOrden,
                                    EstadoOrden nuevoEstado,
                                    String usuario,
                                    String fecha) {

        int indice = buscarOrden(codigoOrden);

        if (indice != -1) {

            ordenes.get(indice).setEstado(nuevoEstado);

            /**Si la orden se finaliza, se registra la fecha automáticamente*/
            if (nuevoEstado == EstadoOrden.FINALIZADO) {
                ordenes.get(indice).setFechaFinalizacion(fecha);
            }

            ordenes.get(indice).setUsuarioModificacion(usuario);
            ordenes.get(indice).setFechaModificacion(fecha);

            return true;
        }

        return false;
    }

    /**AGREGAR OBSERVACIONES*/
    public boolean agregarObservacion(String codigoOrden,
                                      String observacion,
                                      String usuario,
                                      String fecha) {

        int indice = buscarOrden(codigoOrden);

        if (indice != -1) {

            ordenes.get(indice).setObservaciones(observacion);
            ordenes.get(indice).setUsuarioModificacion(usuario);
            ordenes.get(indice).setFechaModificacion(fecha);

            return true;
        }

        return false;
    }

    /**ANULACIÓN LÓGICA*/
    public boolean anularOrden(String codigoOrden,
                               String motivo,
                               String usuario,
                               String fecha) {

        int indice = buscarOrden(codigoOrden);

        if (indice != -1) {

            ordenes.get(indice).setActivo(false);
            ordenes.get(indice).setEstado(EstadoOrden.ANULADA);
            ordenes.get(indice).setMotivoAnulacion(motivo);
            ordenes.get(indice).setUsuarioModificacion(usuario);
            ordenes.get(indice).setFechaModificacion(fecha);

            return true;
        }

        return false;
    }

    /**LISTAR ORDENES POR TECNICO*/
    public String listarOrdenesPorTecnico(String cedulaTecnico) {

        StringBuilder sb = new StringBuilder();

        sb.append("=== ORDENES ASIGNADAS AL TECNICO ===\n\n");

        int contador = 0;

        for (OrdenServicio orden : ordenes) {

            if (orden.getCedulaTecnico().trim()
                    .equalsIgnoreCase(cedulaTecnico.trim())
                    && orden.isActivo()) {

                contador++;

                sb.append("ORDEN #").append(contador).append("\n");
                sb.append("Codigo: ").append(orden.getCodigoOrden()).append("\n");
                sb.append("Cliente: ").append(orden.getCedulaCliente()).append("\n");
                sb.append("Servicio: ").append(orden.getDescripcionServicio()).append("\n");
                sb.append("Estado: ").append(orden.getEstado()).append("\n");
                sb.append("----------------------------------\n");
            }
        }

        if (contador == 0) {
            sb.append("No existen ordenes asignadas.\n");
        }

        return sb.toString();
    }

    /**REPORTE GENERAL*/
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append("=== LISTA DE ORDENES DE SERVICIO ===\n\n");

        if (ordenes.isEmpty()) {

            sb.append("No hay ordenes registradas.\n");
            return sb.toString();
        }

        for (OrdenServicio orden : ordenes) {

            sb.append(orden.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    /**TAMAÑO*/
    public int getSize() {
        return ordenes.size();
    }

    public double calcularTasaCumplimiento() {

        int totalFinalizadas = 0;
        int entregadasATiempo = 0;

        for (OrdenServicio orden : ordenes) {

            if (orden.isActivo()
                    && orden.getEstado() == EstadoOrden.FINALIZADO
                    && orden.getFechaFinalizacion() != null
                    && !orden.getFechaFinalizacion().isEmpty()
                    && orden.getFechaEntregaEstimada() != null
                    && !orden.getFechaEntregaEstimada().isEmpty()) {

                totalFinalizadas++;

                String fechaTexto =
                        orden.getFechaEntregaEstimada()
                                .replace("/", "-");

                LocalDate fechaEstimada =
                        LocalDate.parse(fechaTexto);

                LocalDate fechaFinalizacion =
                        LocalDate.parse(orden.getFechaFinalizacion());

                if (!fechaFinalizacion.isAfter(fechaEstimada)) {
                    entregadasATiempo++;
                }
            }
        }

        if (totalFinalizadas == 0) {
            return 0;
        }

        return ((double) entregadasATiempo / totalFinalizadas) * 100;
    }

    public String generarReporteCumplimiento() {

        int totalFinalizadas = 0;
        int entregadasATiempo = 0;

        for (OrdenServicio orden : ordenes) {

            if (orden.isActivo()
                    && orden.getEstado() == EstadoOrden.FINALIZADO
                    && orden.getFechaFinalizacion() != null
                    && !orden.getFechaFinalizacion().isEmpty()
                    && orden.getFechaEntregaEstimada() != null
                    && !orden.getFechaEntregaEstimada().isEmpty()) {

                totalFinalizadas++;

                String fechaTexto =
                        orden.getFechaEntregaEstimada()
                                .replace("/", "-");

                LocalDate fechaEstimada =
                        LocalDate.parse(fechaTexto);

                LocalDate fechaFinalizacion =
                        LocalDate.parse(orden.getFechaFinalizacion());

                if (!fechaFinalizacion.isAfter(fechaEstimada)) {
                    entregadasATiempo++;
                }
            }
        }

        double porcentaje = calcularTasaCumplimiento();

        return "========== KPI CUMPLIMIENTO ==========\n"
                + "Ordenes Finalizadas: " + totalFinalizadas + "\n"
                + "Entregadas a Tiempo: " + entregadasATiempo + "\n"
                + "Tasa de Cumplimiento: "
                + String.format("%.2f", porcentaje) + "%\n";
    }

    public String generarReporteOrdenesActivas() {

        StringBuilder sb = new StringBuilder();

        sb.append("=== REPORTE DE ORDENES ACTIVAS ===\n\n");

        int contador = 0;

        for (OrdenServicio orden : ordenes) {

            if (orden.isActivo()) {

                contador++;

                sb.append("┌─────────────────────────────────────────────┐\n");
                sb.append("│ Orden #").append(contador).append("\n");
                sb.append("├─────────────────────────────────────────────┤\n");
                sb.append("│ Código: ").append(orden.getCodigoOrden()).append("\n");
                sb.append("│ Cliente: ").append(orden.getCedulaCliente()).append("\n");
                sb.append("│ Técnico: ").append(orden.getCedulaTecnico()).append("\n");
                sb.append("│ Servicio: ").append(orden.getDescripcionServicio()).append("\n");
                sb.append("│ Estado: ").append(orden.getEstado()).append("\n");
                sb.append("└─────────────────────────────────────────────┘\n\n");
            }
        }

        if (contador == 0) {
            sb.append("No existen órdenes activas.\n");
        } else {
            sb.append("TOTAL: ").append(contador).append(" orden(es) activa(s).\n");
        }

        return sb.toString();
    }

    /**BUSCAR Y DEVOLVER LA ORDEN*/
    public OrdenServicio buscarOrdenObjeto(String codigoOrden) {

        int indice = buscarOrden(codigoOrden);

        if (indice != -1) {
            return ordenes.get(indice);
        }

        return null;
    }

    /**ACTUALIZAR OBSERVACIONES*/
    public boolean actualizarObservaciones(String codigoOrden,
                                           String observacion,
                                           String usuario,
                                           String fecha) {

        int indice = buscarOrden(codigoOrden);

        if (indice != -1) {

            ordenes.get(indice).setObservaciones(observacion);
            ordenes.get(indice).setUsuarioModificacion(usuario);
            ordenes.get(indice).setFechaModificacion(fecha);

            return true;
        }

        return false;
    }
}