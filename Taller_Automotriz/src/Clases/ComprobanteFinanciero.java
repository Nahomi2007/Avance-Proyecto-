package Clases;

public abstract class ComprobanteFinanciero {
    private String id_comp;
    private String fecha;
    private String met_pago;
    private double subtotal;
    private double descuento;
    private double total;
    private boolean activo;
    private String motivoAnulacion;
    private String usuarioModificacion;
    private String fechaModificacion;

    public ComprobanteFinanciero(String id_comp, String fecha, String met_pago, double subtotal, double descuento, double total, boolean activo, String motivoAnulacion, String usuarioModificacion, String fechaModificacion) {
        this.id_comp = id_comp;
        this.fecha = fecha;
        this.met_pago = met_pago;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
        this.activo = activo;
        this.motivoAnulacion = motivoAnulacion;
        this.usuarioModificacion = usuarioModificacion;
        this.fechaModificacion = fechaModificacion;
    }

    public String getId_comp() { return id_comp; }
    public void setId_comp(String id_comp) { this.id_comp = id_comp; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getMet_pago() { return met_pago; }
    public void setMet_pago(String met_pago) { this.met_pago = met_pago; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public String getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(String fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public String getUsuarioModificacion() { return usuarioModificacion; }
    public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }

    public String getMotivoAnulacion() { return motivoAnulacion; }
    public void setMotivoAnulacion(String motivoAnulacion) { this.motivoAnulacion = motivoAnulacion; }

    public abstract void calcularImpuestosYTotal();
}