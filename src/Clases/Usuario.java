package Clases;

public class Usuario {

    private String nombre;
    private String correo;
    private String contrasenia;
    private RolUsuario rol;
    private boolean activo;

    private String usuarioCreacion;
    private String fechaCreacion;
    private String usuarioModificacion;
    private String fechaModificacion;

    public Usuario(String nombre,
                   String correo,
                   String contrasenia,
                   RolUsuario rol,
                   boolean activo,
                   String usuarioCreacion,
                   String fechaCreacion,
                   String usuarioModificacion,
                   String fechaModificacion) {

        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
        this.activo = activo;
        this.usuarioCreacion = usuarioCreacion;
        this.fechaCreacion = fechaCreacion;
        this.usuarioModificacion = usuarioModificacion;
        this.fechaModificacion = fechaModificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public boolean validarAcceso(String correo, String contrasenia) {
        return activo
                && this.correo.equalsIgnoreCase(correo)
                && this.contrasenia.equals(contrasenia);
    }

    @Override
    public String toString() {
        return "========== USUARIO ==========" +
                "\nNombre: " + nombre +
                "\nCorreo: " + correo +
                "\nRol: " + rol.getNombreVisible() +
                "\nEstado: " + (activo ? "ACTIVO" : "INACTIVO") +
                "\nCreado por: " + usuarioCreacion +
                "\nFecha de creacion: " + fechaCreacion +
                "\nUltima modificacion por: " + usuarioModificacion +
                "\nFecha de ultima modificacion: " + fechaModificacion +
                "\n";
    }
}