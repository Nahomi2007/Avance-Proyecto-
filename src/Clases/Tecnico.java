package Clases;

public class Tecnico extends Persona {

    private String especialidad;
    private boolean activo;

    public Tecnico(String cedula, String nombre, String telefono,
                   String direccion, String correo,
                   String especialidad, boolean activo) {

        super(cedula, nombre, telefono, direccion, correo);

        this.especialidad = especialidad;
        this.activo = activo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return nombre;
    }
}