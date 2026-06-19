package Gestion;

import Clases.Cliente;
import java.util.ArrayList;

public class GestionClientes {

    private ArrayList<Cliente> clientes;

    public GestionClientes() {
        clientes = new ArrayList<>();
    }

    public boolean agregarCliente(Cliente cliente) {
        if (buscarClienteSimple(cliente.getCedula()) != -1) {
            return false;
        }
        clientes.add(cliente);
        return true;
    }

    public void ordenarPorInsercion() {
        int n = clientes.size();
        for (int i = 1; i < n; i++) {
            Cliente llave = clientes.get(i);
            int j = i - 1;
            while (j >= 0 && clientes.get(j).getCedula().compareTo(llave.getCedula()) > 0) {
                clientes.set(j + 1, clientes.get(j));
                j--;
            }
            clientes.set(j + 1, llave);
        }
    }

    /** BÚSQUEDA SIMPLE - MÁS CONFIABLE */
    public int buscarClienteSimple(String cedula) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCedula().equals(cedula)) {
                return i;
            }
        }
        return -1;
    }

    /** BÚSQUEDA BINARIA (requiere ordenamiento) */
    public int buscarCliente(String cedula) {
        if (clientes.isEmpty()) {
            return -1;
        }

        // Primero intentamos búsqueda simple (más confiable)
        int indiceSimple = buscarClienteSimple(cedula);
        if (indiceSimple != -1) {
            return indiceSimple;
        }

        // Si no funciona la simple, intentamos con binaria
        ordenarPorInsercion();
        return buscarBinarioRecursivo(cedula, 0, clientes.size() - 1);
    }

    private int buscarBinarioRecursivo(String cedula, int inf, int sup) {
        if (inf > sup) {
            return -1;
        }
        int centro = (inf + sup) / 2;
        int comparacion = clientes.get(centro).getCedula().compareTo(cedula);

        if (comparacion == 0) {
            return centro;
        } else if (comparacion > 0) {
            return buscarBinarioRecursivo(cedula, inf, centro - 1);
        } else {
            return buscarBinarioRecursivo(cedula, centro + 1, sup);
        }
    }

    public Cliente getValor(int indice) throws Exception {
        if (indice < 0 || indice >= clientes.size()) {
            throw new Exception("Indice fuera de rango");
        }
        return clientes.get(indice);
    }

    public boolean editarTelefono(String cedula, String nuevoTelefono) {
        int indice = buscarClienteSimple(cedula);
        if (indice != -1) {
            clientes.get(indice).setTelefono(nuevoTelefono);
            return true;
        }
        return false;
    }

    public boolean editarCorreo(String cedula, String nuevoCorreo) {
        int indice = buscarClienteSimple(cedula);
        if (indice != -1) {
            clientes.get(indice).setCorreo(nuevoCorreo);
            return true;
        }
        return false;
    }

    public boolean editarDireccion(String cedula, String nuevaDireccion) {
        int indice = buscarClienteSimple(cedula);
        if (indice != -1) {
            clientes.get(indice).setDireccion(nuevaDireccion);
            return true;
        }
        return false;
    }

    public boolean editarPlaca(String cedula, String nuevaPlaca) {
        int indice = buscarClienteSimple(cedula);
        if (indice != -1) {
            clientes.get(indice).setPlaca(nuevaPlaca);
            return true;
        }
        return false;
    }

    public boolean editarModeloVehiculo(String cedula, String nuevoModelo) {
        int indice = buscarClienteSimple(cedula);
        if (indice != -1) {
            clientes.get(indice).setModeloVehiculo(nuevoModelo);
            return true;
        }
        return false;
    }

    public boolean editarAnioVehiculo(String cedula, int nuevoAnio) {
        int indice = buscarClienteSimple(cedula);
        if (indice != -1) {
            clientes.get(indice).setAnioVehiculo(nuevoAnio);
            return true;
        }
        return false;
    }

    public boolean anularCliente(String cedula, String usuario, String fecha) {
        int indice = buscarClienteSimple(cedula);
        if (indice != -1) {
            clientes.get(indice).setActivo(false);
            clientes.get(indice).setUsuarioModificacion(usuario);
            clientes.get(indice).setFechaModificacion(fecha);
            return true;
        }
        return false;
    }

    public String generarReporteClientes() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE CLIENTES ACTIVOS ===\n\n");
        int contador = 0;

        for (Cliente cliente : clientes) {
            if (cliente.isActivo()) {
                contador++;
                sb.append("┌────────────────────────────────────────────────────────┐\n");
                sb.append("│ Cliente #").append(contador).append("\n");
                sb.append("├────────────────────────────────────────────────────────┤\n");
                sb.append("│ Cédula:    ").append(cliente.getCedula()).append("\n");
                sb.append("│ Nombre:    ").append(cliente.getNombre()).append("\n");
                sb.append("│ Teléfono:  ").append(cliente.getTelefono()).append("\n");
                sb.append("│ Dirección: ").append(cliente.getDireccion()).append("\n");
                sb.append("│ Correo:    ").append(cliente.getCorreo()).append("\n");
                sb.append("│ Vehículo:  ").append(cliente.getPlaca()).append(" - ").append(cliente.getModeloVehiculo()).append(" (").append(cliente.getAnioVehiculo()).append(")\n");
                sb.append("└────────────────────────────────────────────────────────┘\n\n");
            }
        }

        if (contador == 0) {
            sb.append("No hay clientes activos registrados.\n");
        } else {
            sb.append("TOTAL: ").append(contador).append(" cliente(s) activo(s).\n");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE CLIENTES ===\n\n");

        if (clientes.isEmpty()) {
            sb.append("No hay clientes registrados.\n");
            return sb.toString();
        }

        for (Cliente cliente : clientes) {
            sb.append(cliente.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}