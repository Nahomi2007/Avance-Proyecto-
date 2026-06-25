package Clases;

public class GastoOperativo {
    private int idGasto;
    private String descripcion;
    private double monto;
    private String fechaGasto;
    private String categoria; // 'Insumos', 'Servicios Básicos', 'Nómina', etc.

    public GastoOperativo(int idGasto, String descripcion, double monto, String categoria, String fechaGasto) {
        this.idGasto = idGasto;
        this.descripcion = descripcion;
        this.monto = monto;
        this.categoria = categoria;
        this.fechaGasto = fechaGasto;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(String fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
