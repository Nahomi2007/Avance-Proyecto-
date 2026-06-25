package Clases;

public class Producto {
    /**Declaración de atributos*/
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private String categoria;
    private String proveedor;
    private String fechaCaducidad;

    /**Creación de constructor*/
    public Producto(int codigo, String nombre, double precio, int cantidad, String categoria, String proveedor, String fechaCaducidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.fechaCaducidad = fechaCaducidad;
    }

    /**Getters y Setters*/
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(String fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    /**Metodo para imprimir*/
    @Override
    public String toString() {
        return "Producto" +
                "\nCodigo: " + codigo +
                "\nNombre: '" + nombre + '\'' +
                "\nPrecio: " + precio +
                "\nCantidad: " + cantidad +
                "\nCategoria: " + categoria + '\'' +
                "\nProveedor: " + proveedor + '\'' +
                "\nFechaCaducidad: " + fechaCaducidad + "\n";
    }
}