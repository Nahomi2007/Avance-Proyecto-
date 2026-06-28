package Clases;

public class Historial {
    private int idRegistro;
    private String codigoOrden;
    private String fecha;
    private String placa;
    private String cedulaCliente;
    private String servicio;
    private String tecnico;
    private double costo;

    public Historial(int idRegistro, String codigoOrden, String fecha, String placa,
                     String cedulaCliente, String servicio, String tecnico, double costo) {
        this.idRegistro = idRegistro;
        this.codigoOrden = codigoOrden;
        this.fecha = fecha;
        this.placa = placa;
        this.cedulaCliente = cedulaCliente;
        this.servicio = servicio;
        this.tecnico = tecnico;
        this.costo = costo;
    }

    public int getIdRegistro() { return idRegistro; }
    public void setIdRegistro(int idRegistro) { this.idRegistro = idRegistro; }

    public String getCodigoOrden() { return codigoOrden; }
    public void setCodigoOrden(String codigoOrden) { this.codigoOrden = codigoOrden; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getCedulaCliente() { return cedulaCliente; }
    public void setCedulaCliente(String cedulaCliente) { this.cedulaCliente = cedulaCliente; }

    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }

    public String getTecnico() { return tecnico; }
    public void setTecnico(String tecnico) { this.tecnico = tecnico; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    @Override
    public String toString() {
        return "┌────────────────────────────────────────────┐\n" +
                "│ Orden: " + codigoOrden + "   Fecha: " + fecha + "\n" +
                "│ Servicio: " + servicio + "\n" +
                "│ Tecnico:  " + tecnico + "\n" +
                "│ Costo:    $" + String.format("%.2f", costo) + "\n" +
                "└────────────────────────────────────────────┘\n";
    }
}