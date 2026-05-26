package Clases;

public class Cliente extends Persona {
    /** Creacion de los parametros */
    private String placa;
    private String modeloVehiculo;
    private int anioVehiculo;
    private String historialServicio;

    /** Anulación lógica */
    private boolean activo;

    /** Auditoría */
    private String usuarioModificacion;
    private String fechaModificacion;

    /** Creacion del constructor */

    public Cliente(String cedula, String nombre, String apellido, String telefono, String direccion, String correo, String placa, String modeloVehiculo, int anioVehiculo, String historialServicio, boolean activo, String usuarioModificacion, String fechaModificacion) {
        super(cedula, nombre, apellido, telefono, direccion, correo);
        this.placa = placa;
        this.modeloVehiculo = modeloVehiculo;
        this.anioVehiculo = anioVehiculo;
        this.historialServicio = historialServicio;
        this.activo = activo;
        this.usuarioModificacion = usuarioModificacion;
        this.fechaModificacion = fechaModificacion;
    }
    /** Getters and Setters */
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public int getAnioVehiculo() {
        return anioVehiculo;
    }

    public void setAnioVehiculo(int anioVehiculo) {
        this.anioVehiculo = anioVehiculo;
    }

    public String getHistorialServicio() {
        return historialServicio;
    }

    public void setHistorialServicio(String historialServicio) {
        this.historialServicio = historialServicio;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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

    /** Getters y Setters */



    @Override
    public String toString() {

        return "Cliente :" +
                "\nNombre: " + nombre +
                "\nApellido: " + apellido +
                "\nCedula: " + cedula +
                "\nTelefono: " + telefono +
                "\nDireccion: " + direccion +
                "\nCorreo: " + correo +
                "\nPlaca: " + placa +
                "\nModelo Vehiculo: " + modeloVehiculo +
                "\nAño Vehiculo: " + anioVehiculo +
                "\nHistorial Servicio: " + historialServicio +
                "\nEstado: " + (activo ? "ACTIVO" : "ANULADO") +
                "\n";
    }
}