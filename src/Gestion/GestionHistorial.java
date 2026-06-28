package Gestion;

import Clases.Historial;
import java.util.ArrayList;
import java.util.Stack;

public class GestionHistorial {

    /** Estructura principal: Pila (LIFO) -> el ultimo servicio queda en el tope */
    private Stack<Historial> pila;
    private int contadorId;

    public GestionHistorial() {
        pila = new Stack<>();
        contadorId = 1;
    }

    /** Agregar un servicio finalizado al historial (push) */
    public void agregarRegistro(String codigoOrden, String fecha, String placa,
                                String cedulaCliente, String servicio, String tecnico, double costo) {
        Historial h = new Historial(contadorId, codigoOrden, fecha, placa,
                cedulaCliente, servicio, tecnico, costo);
        pila.push(h);
        contadorId++;
    }

    /** Devuelve la pila completa, del mas reciente al mas antiguo */
    public ArrayList<Historial> obtenerTodos() {
        ArrayList<Historial> lista = new ArrayList<>();
        for (int i = pila.size() - 1; i >= 0; i--) {
            lista.add(pila.get(i));
        }
        return lista;
    }

    /**BUSQUEDAS SECUENCIALES (placa, cedula, fecha)*/
    public ArrayList<Historial> buscarPorPlaca(String placa) {
        ArrayList<Historial> resultado = new ArrayList<>();
        for (int i = pila.size() - 1; i >= 0; i--) {
            if (pila.get(i).getPlaca().equalsIgnoreCase(placa.trim())) {
                resultado.add(pila.get(i));
            }
        }
        return resultado;
    }

    public ArrayList<Historial> buscarPorCedula(String cedula) {
        ArrayList<Historial> resultado = new ArrayList<>();
        for (int i = pila.size() - 1; i >= 0; i--) {
            if (pila.get(i).getCedulaCliente().equals(cedula.trim())) {
                resultado.add(pila.get(i));
            }
        }
        return resultado;
    }

    public ArrayList<Historial> buscarPorFecha(String fecha) {
        ArrayList<Historial> resultado = new ArrayList<>();
        for (int i = pila.size() - 1; i >= 0; i--) {
            if (pila.get(i).getFecha().equals(fecha.trim())) {
                resultado.add(pila.get(i));
            }
        }
        return resultado;
    }

    /**BUSQUEDA BINARIA RECURSIVA POR ID*/
    public Historial buscarPorId(int id) {
        if (pila.isEmpty()) {
            return null;
        }
        ArrayList<Historial> copia = copiaOrdenadaPorId();
        int indice = buscarBinarioRecursivo(copia, id, 0, copia.size() - 1);
        if (indice == -1) {
            return null;
        }
        return copia.get(indice);
    }

    /** Ordena una COPIA por insercion (no toca la pila original, no rompe el LIFO) */
    private ArrayList<Historial> copiaOrdenadaPorId() {
        ArrayList<Historial> copia = new ArrayList<>(pila);
        for (int i = 1; i < copia.size(); i++) {
            Historial llave = copia.get(i);
            int j = i - 1;
            while (j >= 0 && copia.get(j).getIdRegistro() > llave.getIdRegistro()) {
                copia.set(j + 1, copia.get(j));
                j--;
            }
            copia.set(j + 1, llave);
        }
        return copia;
    }

    private int buscarBinarioRecursivo(ArrayList<Historial> lista, int id, int inf, int sup) {
        if (inf > sup) {
            return -1;
        }
        int centro = (inf + sup) / 2;
        if (lista.get(centro).getIdRegistro() == id) {
            return centro;
        } else if (lista.get(centro).getIdRegistro() > id) {
            return buscarBinarioRecursivo(lista, id, inf, centro - 1);
        } else {
            return buscarBinarioRecursivo(lista, id, centro + 1, sup);
        }
    }

    /**AGREGADOS PARA LA FICHA DEL VEHICULO*/
    public int contarVisitas(String placa) {
        return buscarPorPlaca(placa).size();
    }

    public double totalGastado(String placa) {
        double total = 0;
        for (Historial h : buscarPorPlaca(placa)) {
            total += h.getCosto();
        }
        return total;
    }

    public String ultimoServicio(String placa) {
        ArrayList<Historial> lista = buscarPorPlaca(placa);
        if (lista.isEmpty()) {
            return "Sin servicios";
        }
        return lista.get(0).getFecha();
    }

    /** Texto formateado para exportar/imprimir en un JTextArea */
    public String formatearResultados(ArrayList<Historial> lista) {
        if (lista == null || lista.isEmpty()) {
            return "No se encontraron registros.\n";
        }
        StringBuilder sb = new StringBuilder();
        for (Historial h : lista) {
            sb.append(h.toString());
        }
        sb.append("TOTAL: ").append(lista.size()).append(" servicio(s)\n");
        return sb.toString();
    }

    public int getSize() {
        return pila.size();
    }
}