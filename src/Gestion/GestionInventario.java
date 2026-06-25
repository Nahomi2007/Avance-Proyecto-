package Gestion;

import Clases.Producto;
import java.util.ArrayList;

public class GestionInventario {

    private ArrayList<Producto> productos;

    public GestionInventario() {
        productos = new ArrayList<>();
    }

    public boolean agregarProducto(Producto producto) {
        productos.add(producto);
        return true;
    }

    public void ordenarPorInsercion() {
        int n = productos.size();

        for (int i = 1; i < n; i++) {
            Producto llave = productos.get(i);
            int j = i - 1;

            while (j >= 0 && productos.get(j).getCodigo() > llave.getCodigo()) {
                productos.set(j + 1, productos.get(j));
                j--;
            }
            productos.set(j + 1, llave);
        }
    }

    public int buscarCodigo(int id) {
        if (productos.isEmpty()) {
            return -1;
        }

        ordenarPorInsercion();

        if (id < productos.get(0).getCodigo() || id > productos.get(productos.size() - 1).getCodigo()) {
            return -1;
        }

        return buscarBinarioRecursivo(id, 0, productos.size() - 1);
    }

    public Producto getValor(int indice) throws Exception {
        if (indice < 0 || indice >= productos.size()) {
            throw new Exception("ID Fuera de rango");
        }
        return productos.get(indice);
    }

    private int buscarBinarioRecursivo(int id, int inf, int sup) {
        if (inf > sup) {
            return -1;
        }

        int centro = (inf + sup) / 2;

        if (productos.get(centro).getCodigo() == id) {
            return centro;
        } else if (productos.get(centro).getCodigo() > id) {
            return buscarBinarioRecursivo(id, inf, centro - 1);
        } else {
            return buscarBinarioRecursivo(id, centro + 1, sup);
        }
    }

    public boolean editarNombre(int idBuscar, String nuevoNombre) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setNombre(nuevoNombre);
            return true;
        }
        return false;
    }

    public boolean editarCategoria(int idBuscar, String nuevaCategoria) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setCategoria(nuevaCategoria);
            return true;
        }
        return false;
    }

    public boolean editarCantidad(int idBuscar, int nuevaCantidad) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setCantidad(nuevaCantidad);
            return true;
        }
        return false;
    }

    public boolean editarCosto(int idBuscar, double nuevoPrecio) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setPrecio(nuevoPrecio);
            return true;
        }
        return false;
    }

    public boolean editarProveedor(int idBuscar, String nuevoProveedor) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setProveedor(nuevoProveedor);
            return true;
        }
        return false;
    }

    public boolean editarFechaCaducidad(int idBuscar, String nuevaFecha) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setFechaCaducidad(nuevaFecha);
            return true;
        }
        return false;
    }

    public void eliminarProducto(int code) throws Exception {
        ordenarPorInsercion();

        int indice = buscarCodigo(code);

        if (indice != -1) {
            productos.remove(indice);
        } else {
            throw new Exception("El producto con el codigo " + code + " no existe.");
        }
    }

    public boolean descontarMaterial(int codigo, int cantidad) {
        int indice = buscarCodigo(codigo);
        if (indice != -1) {
            Producto p = productos.get(indice);
            int nuevaCantidad = p.getCantidad() - cantidad;
            if (nuevaCantidad < 0) {
                return false;
            }
            p.setCantidad(nuevaCantidad);
            return true;
        }
        return false;
    }

    public String generarReporteStockMinimo() {
        StringBuilder sb = new StringBuilder();
        sb.append("PRODUCTOS CON STOCK MINIMO (<= 5 unidades)\n\n");

        boolean hayStockMinimo = false;
        int contador = 0;

        for (Producto producto : productos) {
            if (producto.getCantidad() <= 5) {
                contador++;
                hayStockMinimo = true;
                sb.append("Producto #").append(contador).append("\n");
                sb.append("Codigo:     ").append(producto.getCodigo()).append("\n");
                sb.append("Nombre:     ").append(producto.getNombre()).append("\n");
                sb.append("Cantidad:   ").append(producto.getCantidad()).append(" unidades \n");
                sb.append("Categoria:  ").append(producto.getCategoria()).append("\n");
                sb.append("Proveedor:  ").append(producto.getProveedor()).append("\n");
                sb.append("----------------------------------------\n");
            }
        }

        if (!hayStockMinimo) {
            sb.append("No hay productos con stock minimo.\n");
        } else {
            sb.append("\nTOTAL: ").append(contador).append(" producto(s) con stock minimo.\n");
        }

        return sb.toString();
    }

    public int getSize() {
        return productos.size();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE PRODUCTOS ===\n\n");

        if (productos.isEmpty()) {
            sb.append("No hay productos registrados.\n");
            return sb.toString();
        }

        for (Producto producto : productos) {
            sb.append(producto.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}