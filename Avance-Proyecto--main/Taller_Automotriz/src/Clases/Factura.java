package Clases;

public class Factura extends ComprobanteFinanciero {
    private String clv_SRI;
    private String ci_cliente;
    private String code_orden;
    private double iva;

    public Factura(String id_comp, String fecha, String met_pago, double subtotal, double descuento,
                   boolean activo, String motivoAnulacion, String usuarioModificacion, String fechaModificacion,
                   String clv_SRI, String ci_cliente, String code_orden) {

        super(id_comp, fecha, met_pago, subtotal, descuento, 0.0, activo, motivoAnulacion, usuarioModificacion, fechaModificacion);
        this.clv_SRI = clv_SRI;
        this.ci_cliente = ci_cliente;
        this.code_orden = code_orden;

        calcularImpuestosYTotal();
    }

    @Override
    public void calcularImpuestosYTotal() {
        this.iva = (getSubtotal() - getDescuento()) * 0.15;
        setTotal(getSubtotal() - getDescuento() + this.iva);
    }

    public String getClv_SRI() { return clv_SRI; }
    public void setClv_SRI(String clv_SRI) { this.clv_SRI = clv_SRI; }

    public String getCi_cliente() { return ci_cliente; }
    public void setCi_cliente(String ci_cliente) { this.ci_cliente = ci_cliente; }

    public String getCode_orden() { return code_orden; }
    public void setCode_orden(String code_orden) { this.code_orden = code_orden; }

    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }

    @Override
    public String toString() {
        return "COMPROBANTE ELECTRÓNICO SRI: " + getId_comp() +
                "\n-------------------------------------------" +
                "\nClave Acceso (49 dígitos): " + clv_SRI +
                "\nFecha Emisión:            " + getFecha() +
                "\nCliente (Cédula/RUC):     " + ci_cliente +
                "\nCódigo Orden de Trabajo:  " + code_orden +
                "\nMétodo de Pago:           " + getMet_pago() +
                "\n-------------------------------------------" +
                "\nSubtotal:                 $" + getSubtotal() +
                "\nDescuento:               -$" + getDescuento() +
                "\nIVA (15%):               +$" + iva +
                "\nTOTAL NETO A PAGAR:       $" + getTotal() +
                "\n-------------------------------------------" +
                "\nEstado:                   " + (isActivo() ? "EMITIDA / VALIDA" : "ANULADA (Motivo: " + getMotivoAnulacion() + ")") +
                "\nRegistrado por:           " + getUsuarioModificacion() + " el " + getFechaModificacion() + "\n";
    }
}