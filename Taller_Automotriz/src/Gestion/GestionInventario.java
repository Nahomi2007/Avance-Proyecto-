package Gestion;

import Clases.Producto;
import java.util.ArrayList;

public class GestionInventario {
    private ArrayList<Producto> productos;

    public GestionInventario() {
        productos = new ArrayList<>();
    }

    /** Agregar Productos */
    public boolean agregarProducto(Producto producto) {
        productos.add(producto);
        return true;
    }

    /** Ordenamiento por Inserción */
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

    /** Buscar por código */
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

    /** Evitar índices fuera de rango */
    public Producto getValor(int indice) throws Exception {
        if (indice < 0 || indice >= productos.size()) {
            throw new Exception("ID Fuera de rango");
        }
        return productos.get(indice);
    }

    /** Búsqueda binaria con recursividad */
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

    /** Mostrar todos los productos */
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
            if (producto.getCantidad() <= 5) {
                sb.append("ALERTA: Stock Mínimo - Quedan " + producto.getCantidad() + " unidades\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /** Editar Producto - Nombre */
    public boolean editarNombre(int idBuscar, String nuevoNombre) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setNombre(nuevoNombre);
            return true;
        }
        return false;
    }

    /** Editar Producto - Categoría */
    public boolean editarCategoria(int idBuscar, String nuevaCategoria) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setCategoria(nuevaCategoria);
            return true;
        }
        return false;
    }

    /** Editar Producto - Cantidad */
    public boolean editarCantidad(int idBuscar, int nuevaCantidad) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setCantidad(nuevaCantidad);
            return true;
        }
        return false;
    }

    /** Editar Producto - Precio */
    public boolean editarCosto(int idBuscar, double nuevoPrecio) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setPrecio(nuevoPrecio);
            return true;
        }
        return false;
    }

    /** Editar Producto - Proveedor */
    public boolean editarProveedor(int idBuscar, String nuevoProveedor) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setProveedor(nuevoProveedor);
            return true;
        }
        return false;
    }

    /** Editar Producto - Fecha Caducidad */
    public boolean editarFechaCaducidad(int idBuscar, String nuevaFecha) {
        ordenarPorInsercion();
        int indice = buscarCodigo(idBuscar);
        if (indice != -1) {
            productos.get(indice).setFechaCaducidad(nuevaFecha);
            return true;
        }
        return false;
    }

    /** Eliminar Producto */
    public void eliminarProducto(int code) throws Exception {
        ordenarPorInsercion();

        int indice = buscarCodigo(code);

        if (indice != -1) {
            Producto eliminado = productos.get(indice);
            productos.remove(indice);
            System.out.println("Se eliminó el producto: " + eliminado.getNombre() + " (ID: " + code + ")");
        } else {
            throw new Exception("El producto con el código " + code + " no existe.");
        }
    }

    /** REPORTE DE PRODUCTOS CON STOCK MÍNIMO (<= 5) */
    public String generarReporteStockMinimo() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════════════════════════╗\n");
        sb.append("║           PRODUCTOS CON STOCK MÍNIMO (≤ 5 unidades)          ║\n");
        sb.append("╚══════════════════════════════════════════════════════════════╝\n\n");

        boolean hayStockMinimo = false;
        int contador = 0;

        for (Producto producto : productos) {
            if (producto.getCantidad() <= 5) {
                contador++;
                hayStockMinimo = true;
                sb.append("┌────────────────────────────────────────────────────────┐\n");
                sb.append("│ Producto #").append(contador).append("\n");
                sb.append("├────────────────────────────────────────────────────────┤\n");
                sb.append("│ Código:     ").append(producto.getCodigo()).append("\n");
                sb.append("│ Nombre:     ").append(producto.getNombre()).append("\n");
                sb.append("│ Cantidad:   ").append(producto.getCantidad()).append(" unidades \n");
                sb.append("│ Categoría:  ").append(producto.getCategoria()).append("\n");
                sb.append("│ Proveedor:  ").append(producto.getProveedor()).append("\n");
                sb.append("└────────────────────────────────────────────────────────┘\n\n");
            }
        }

        if (!hayStockMinimo) {
            sb.append("¡No hay productos con stock mínimo!\n");
            sb.append("   Todos los productos tienen más de 5 unidades disponibles.\n");
        } else {
            sb.append("\nTOTAL: ").append(contador).append(" producto(s) con stock mínimo.\n");
            sb.append("Recomendación: Realizar pedido de reposición.\n");
        }

        return sb.toString();
    }

    /** Obtener el tamaño del inventario */
    public int getSize() {
        return productos.size();
    }
}