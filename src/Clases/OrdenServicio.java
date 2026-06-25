package Clases;

import java.util.ArrayList;

public class OrdenServicio {

    // Datos principales de la orden
    private String codigoOrden;
    private String cedulaCliente;
    private String cedulaTecnico;
    private String descripcionServicio;

    // Materiales utilizados
    private ArrayList<String> materiales;

    // Estado de la orden
    private EstadoOrden estado;

    // Seguimiento técnico
    private String observaciones;
    private String evidenciaAntes;
    private String evidenciaDespues;

    // Fechas
    private String fechaCreacion;
    private String fechaEntregaEstimada;
    private String fechaFinalizacion;

    // Anulación lógica
    private boolean activo;
    private String motivoAnulacion;

    // Auditoría
    private String usuarioModificacion;
    private String fechaModificacion;

    // Constructor
    public OrdenServicio(String codigoOrden,
                         String cedulaCliente,
                         String cedulaTecnico,
                         String descripcionServicio,
                         ArrayList<String> materiales,
                         EstadoOrden estado,
                         String observaciones,
                         String evidenciaAntes,
                         String evidenciaDespues,
                         String fechaCreacion,
                         String fechaEntregaEstimada,
                         String fechaFinalizacion,
                         boolean activo,
                         String motivoAnulacion,
                         String usuarioModificacion,
                         String fechaModificacion) {

        this.codigoOrden = codigoOrden;
        this.cedulaCliente = cedulaCliente;
        this.cedulaTecnico = cedulaTecnico;
        this.descripcionServicio = descripcionServicio;
        this.materiales = materiales;
        this.estado = estado;
        this.observaciones = observaciones;
        this.evidenciaAntes = evidenciaAntes;
        this.evidenciaDespues = evidenciaDespues;
        this.fechaCreacion = fechaCreacion;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.fechaFinalizacion = fechaFinalizacion;
        this.activo = activo;
        this.motivoAnulacion = motivoAnulacion;
        this.usuarioModificacion = usuarioModificacion;
        this.fechaModificacion = fechaModificacion;
    }

    // Getters y Setters

    public String getCodigoOrden() {
        return codigoOrden;
    }

    public void setCodigoOrden(String codigoOrden) {
        this.codigoOrden = codigoOrden;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getCedulaTecnico() {
        return cedulaTecnico;
    }

    public void setCedulaTecnico(String cedulaTecnico) {
        this.cedulaTecnico = cedulaTecnico;
    }

    public String getDescripcionServicio() {
        return descripcionServicio;
    }

    public void setDescripcionServicio(String descripcionServicio) {
        this.descripcionServicio = descripcionServicio;
    }

    public ArrayList<String> getMateriales() {
        return materiales;
    }

    public void setMateriales(ArrayList<String> materiales) {
        this.materiales = materiales;
    }

    public EstadoOrden getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrden estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEvidenciaAntes() {
        return evidenciaAntes;
    }

    public void setEvidenciaAntes(String evidenciaAntes) {
        this.evidenciaAntes = evidenciaAntes;
    }

    public String getEvidenciaDespues() {
        return evidenciaDespues;
    }

    public void setEvidenciaDespues(String evidenciaDespues) {
        this.evidenciaDespues = evidenciaDespues;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(String fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return "========== ORDEN DE SERVICIO ==========" +
                "\nCódigo Orden: " + codigoOrden +
                "\nCliente: " + cedulaCliente +
                "\nTécnico Responsable: " + cedulaTecnico +
                "\nServicio: " + descripcionServicio +
                "\nMateriales: " + materiales +
                "\nEstado: " + estado +
                "\nObservaciones: " + observaciones +
                "\nFecha Creación: " + fechaCreacion +
                "\nFecha Entrega Estimada: " + fechaEntregaEstimada +
                "\nFecha Finalización: " + fechaFinalizacion +
                "\nEstado Registro: " + (activo ? "ACTIVO" : "ANULADO") +
                "\nMotivo Anulación: " + motivoAnulacion +
                "\nUsuario Modificación: " + usuarioModificacion +
                "\nFecha Modificación: " + fechaModificacion +
                "\n";
    }
}