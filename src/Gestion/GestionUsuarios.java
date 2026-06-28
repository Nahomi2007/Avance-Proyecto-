package Gestion;

import Clases.RolUsuario;
import Clases.Usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class GestionUsuarios {

    private ArrayList<Usuario> usuarios;
    private HashMap<String, Usuario> indicePorCorreo;
    private Stack<RegistroAuditoria> auditoria;
    private DateTimeFormatter formatoFechaHora;

    public GestionUsuarios() {
        usuarios = new ArrayList<>();
        indicePorCorreo = new HashMap<>();
        auditoria = new Stack<>();
        formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        cargarUsuariosBase();
    }

    private void cargarUsuariosBase() {
        agregarUsuarioInterno(new Usuario(
                "Administrador General",
                "admin@taller.com",
                "admin123",
                RolUsuario.ADMINISTRADOR,
                true,
                "Sistema",
                fechaHoraActual(),
                "Sistema",
                fechaHoraActual()
        ));

        agregarUsuarioInterno(new Usuario(
                "Recepcionista Prueba",
                "recepcion@taller.com",
                "12345",
                RolUsuario.RECEPCIONISTA,
                true,
                "Sistema",
                fechaHoraActual(),
                "Sistema",
                fechaHoraActual()
        ));

        agregarUsuarioInterno(new Usuario(
                "Tecnico Prueba",
                "tecnico@taller.com",
                "12345",
                RolUsuario.TECNICO,
                true,
                "Sistema",
                fechaHoraActual(),
                "Sistema",
                fechaHoraActual()
        ));
    }

    private void agregarUsuarioInterno(Usuario usuario) {
        usuarios.add(usuario);
        indicePorCorreo.put(usuario.getCorreo().toLowerCase(), usuario);
    }

    private String fechaHoraActual() {
        return LocalDateTime.now().format(formatoFechaHora);
    }

    private void registrarAuditoria(String usuarioResponsable, String accion, String detalle) {
        auditoria.push(new RegistroAuditoria(
                usuarioResponsable,
                accion,
                "Usuarios y Accesos",
                detalle,
                fechaHoraActual()
        ));
    }

    public boolean agregarUsuario(String nombre,
                                  String correo,
                                  String contrasenia,
                                  RolUsuario rol,
                                  String usuarioResponsable) {

        if (nombre == null || nombre.trim().isEmpty()
                || correo == null || correo.trim().isEmpty()
                || contrasenia == null || contrasenia.trim().isEmpty()
                || rol == null) {
            return false;
        }

        String clave = correo.trim().toLowerCase();

        if (indicePorCorreo.containsKey(clave)) {
            return false;
        }

        String fecha = fechaHoraActual();

        Usuario nuevoUsuario = new Usuario(
                nombre.trim(),
                correo.trim(),
                contrasenia.trim(),
                rol,
                true,
                usuarioResponsable,
                fecha,
                usuarioResponsable,
                fecha
        );

        usuarios.add(nuevoUsuario);
        indicePorCorreo.put(clave, nuevoUsuario);

        registrarAuditoria(
                usuarioResponsable,
                "CREACION",
                "Se registro el usuario: " + correo.trim()
        );

        return true;
    }

    public Usuario iniciarSesion(String correo, String contrasenia) {
        if (correo == null || contrasenia == null) {
            return null;
        }

        Usuario usuario = indicePorCorreo.get(correo.trim().toLowerCase());

        if (usuario != null && usuario.validarAcceso(correo.trim(), contrasenia)) {
            registrarAuditoria(
                    usuario.getNombre(),
                    "INICIO DE SESION",
                    "Acceso correcto al sistema"
            );
            return usuario;
        }

        return null;
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        if (correo == null) {
            return null;
        }

        return indicePorCorreo.get(correo.trim().toLowerCase());
    }

    public void ordenarPorInsercion() {
        int n = usuarios.size();

        for (int i = 1; i < n; i++) {
            Usuario llave = usuarios.get(i);
            int j = i - 1;

            while (j >= 0 &&
                    usuarios.get(j).getCorreo().compareToIgnoreCase(llave.getCorreo()) > 0) {
                usuarios.set(j + 1, usuarios.get(j));
                j--;
            }

            usuarios.set(j + 1, llave);
        }

        reconstruirIndice();
    }

    private void reconstruirIndice() {
        indicePorCorreo.clear();

        for (Usuario usuario : usuarios) {
            indicePorCorreo.put(usuario.getCorreo().toLowerCase(), usuario);
        }
    }

    public int buscarUsuario(String correo) {
        if (usuarios.isEmpty() || correo == null || correo.trim().isEmpty()) {
            return -1;
        }

        ordenarPorInsercion();
        return buscarBinarioRecursivo(correo.trim(), 0, usuarios.size() - 1);
    }

    private int buscarBinarioRecursivo(String correo, int inf, int sup) {
        if (inf > sup) {
            return -1;
        }

        int centro = (inf + sup) / 2;

        int comparacion = usuarios.get(centro)
                .getCorreo()
                .compareToIgnoreCase(correo);

        if (comparacion == 0) {
            return centro;
        } else if (comparacion > 0) {
            return buscarBinarioRecursivo(correo, inf, centro - 1);
        } else {
            return buscarBinarioRecursivo(correo, centro + 1, sup);
        }
    }

    public Usuario getValor(int indice) throws Exception {
        if (indice < 0 || indice >= usuarios.size()) {
            throw new Exception("Indice de usuario fuera de rango.");
        }

        return usuarios.get(indice);
    }

    public boolean editarUsuario(String correoOriginal,
                                 String nuevoNombre,
                                 String nuevoCorreo,
                                 RolUsuario nuevoRol,
                                 String usuarioResponsable) {

        Usuario usuario = buscarUsuarioPorCorreo(correoOriginal);

        if (usuario == null
                || nuevoNombre == null || nuevoNombre.trim().isEmpty()
                || nuevoCorreo == null || nuevoCorreo.trim().isEmpty()
                || nuevoRol == null) {
            return false;
        }

        String claveOriginal = usuario.getCorreo().toLowerCase();
        String claveNueva = nuevoCorreo.trim().toLowerCase();

        if (!claveOriginal.equals(claveNueva) && indicePorCorreo.containsKey(claveNueva)) {
            return false;
        }

        indicePorCorreo.remove(claveOriginal);

        usuario.setNombre(nuevoNombre.trim());
        usuario.setCorreo(nuevoCorreo.trim());
        usuario.setRol(nuevoRol);
        usuario.setUsuarioModificacion(usuarioResponsable);
        usuario.setFechaModificacion(fechaHoraActual());

        indicePorCorreo.put(claveNueva, usuario);

        registrarAuditoria(
                usuarioResponsable,
                "MODIFICACION",
                "Se modifico el usuario: " + correoOriginal
        );

        return true;
    }

    public boolean resetearContrasenia(String correo,
                                       String nuevaContrasenia,
                                       String usuarioResponsable) {

        Usuario usuario = buscarUsuarioPorCorreo(correo);

        if (usuario == null
                || nuevaContrasenia == null
                || nuevaContrasenia.trim().isEmpty()) {
            return false;
        }

        usuario.setContrasenia(nuevaContrasenia.trim());
        usuario.setUsuarioModificacion(usuarioResponsable);
        usuario.setFechaModificacion(fechaHoraActual());

        registrarAuditoria(
                usuarioResponsable,
                "RESETEO DE CONTRASENIA",
                "Se cambio la contrasenia del usuario: " + correo
        );

        return true;
    }

    public boolean desactivarUsuario(String correo, String usuarioResponsable) {
        Usuario usuario = buscarUsuarioPorCorreo(correo);

        if (usuario == null || !usuario.isActivo()) {
            return false;
        }

        usuario.setActivo(false);
        usuario.setUsuarioModificacion(usuarioResponsable);
        usuario.setFechaModificacion(fechaHoraActual());

        registrarAuditoria(
                usuarioResponsable,
                "DESACTIVACION",
                "Se desactivo el usuario: " + correo
        );

        return true;
    }

    public int contarUsuariosActivos() {
        int contador = 0;

        for (Usuario usuario : usuarios) {
            if (usuario.isActivo()) {
                contador++;
            }
        }

        return contador;
    }

    public int getSize() {
        return usuarios.size();
    }

    public String generarKPIUsuariosActivos() {
        int activos = contarUsuariosActivos();
        int total = usuarios.size();
        int inactivos = total - activos;

        double porcentaje = 0;

        if (total > 0) {
            porcentaje = (activos * 100.0) / total;
        }

        return " KPI USUARIOS Y ACCESOS " +
                "\nUsuarios registrados: " + total +
                "\nUsuarios activos con acceso: " + activos +
                "\nUsuarios inactivos: " + inactivos +
                "\nPorcentaje de acceso activo: " + String.format("%.2f", porcentaje) + "%\n";
    }

    public String listarUsuarios() {
        StringBuilder sb = new StringBuilder();

        sb.append(" LISTA DE USUARIOS \n\n");

        if (usuarios.isEmpty()) {
            sb.append("No hay usuarios registrados.\n");
            return sb.toString();
        }

        ordenarPorInsercion();

        for (Usuario usuario : usuarios) {
            sb.append(usuario.toString()).append("\n");
        }

        return sb.toString();
    }

    public String listarAuditoria() {
        StringBuilder sb = new StringBuilder();

        sb.append("REGISTRO DE AUTOR DE CAMBIOS \n\n");

        if (auditoria.isEmpty()) {
            sb.append("Todavia no existen cambios registrados.\n");
            return sb.toString();
        }

        for (int i = auditoria.size() - 1; i >= 0; i--) {
            sb.append(auditoria.get(i).toString());
        }

        return sb.toString();
    }

    private static class RegistroAuditoria {

        private String usuarioResponsable;
        private String accion;
        private String modulo;
        private String detalle;
        private String fechaHora;

        public RegistroAuditoria(String usuarioResponsable,
                                 String accion,
                                 String modulo,
                                 String detalle,
                                 String fechaHora) {

            this.usuarioResponsable = usuarioResponsable;
            this.accion = accion;
            this.modulo = modulo;
            this.detalle = detalle;
            this.fechaHora = fechaHora;
        }

        @Override
        public String toString() {
            return "[" + fechaHora + "] "
                    + "Usuario: " + usuarioResponsable
                    + " | Modulo: " + modulo
                    + " | Accion: " + accion
                    + " | Detalle: " + detalle + "\n";
        }
    }
}