package Clases;

public enum RolUsuario {
    ADMINISTRADOR("Administrador"),
    RECEPCIONISTA("Recepcionista"),
    TECNICO("Tecnico");

    private final String nombreVisible;

    RolUsuario(String nombreVisible) {
        this.nombreVisible = nombreVisible;
    }

    public String getNombreVisible() {
        return nombreVisible;
    }

    public static RolUsuario desdeTexto(String texto) {
        if (texto == null) {
            return RECEPCIONISTA;
        }

        String valor = texto.trim().toUpperCase()
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U")
                .replace(" ", "_");

        if (valor.equals("ADMIN") || valor.equals("ADMINISTRADOR")) {
            return ADMINISTRADOR;
        }

        if (valor.equals("TECNICO") || valor.equals("TECNICO")) {
            return TECNICO;
        }

        return RECEPCIONISTA;
    }

    public boolean puedeVerMenu(String tituloMenu) {
        if (tituloMenu == null) {
            return false;
        }

        String menu = tituloMenu.trim().toUpperCase()
                .replace("Á", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ú", "U");

        if (this == ADMINISTRADOR) {
            return true;
        }

        if (this == RECEPCIONISTA) {
            return menu.equals("CLIENTES")
                    || menu.equals("CITAS")
                    || menu.equals("FINANZAS")
                    || menu.equals("REPORTES")
                    || menu.equals("HISTORIAL");
        }

        if (this == TECNICO) {
            return menu.equals("ORDENES DE SERVICIO")
                    || menu.equals("INVENTARIO")
                    || menu.equals("HISTORIAL");
        }

        return false;
    }

    @Override
    public String toString() {
        return nombreVisible;
    }
}