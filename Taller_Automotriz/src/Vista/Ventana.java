package Vista;

import Clases.Cliente;
import Clases.Producto;
import Gestion.GestionClientes;
import Gestion.GestionInventario;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {

    private JPanel ventana;
    private JTabbedPane tabbedPane1;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtCorreo;
    private JTextField txtPlaca;
    private JTextField txtModelo;
    private JTextField txtAnio;
    private JButton btnAgregarCliente;
    private JButton btnBuscarCliente;
    private JButton btnEditarCliente;
    private JButton btnMostrarCliente;
    private JButton btnEliminarCliente;
    private JTextArea areaClientes;
    private JTextField txtBusquedaCedula;
    private JTextField txtCodigo;
    private JTextField txtNombreProducto;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextField txtCategoria;
    private JTextField txtProveedor;
    private JTextField txtFechaCaducidad;
    private JButton btnAgregarProducto;
    private JButton btnBuscarProducto;
    private JButton btnEditarProducto;
    private JButton btnMostrarProducto;
    private JButton btnEliminarProducto;
    private JTextArea areaInventario;
    private JTextField txtBusquedaCodigo;
    private JButton btnReporteClientes;
    private JButton btnReporteInventario;
    private JButton btnStockMinimo;
    private JTextArea areaReportes;
    private JTabbedPane tabbedPane2;
    private JTabbedPane tabbedPane3;

    GestionClientes gestionClientes = new GestionClientes();
    GestionInventario gestionInventario = new GestionInventario();
    private Cliente clienteSeleccionado = null;

    public Ventana() {
        $$$setupUI$$$();
        agregarEventos();
    }

    private void agregarEventos() {
        btnAgregarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Cliente cliente = new Cliente(
                            txtCedula.getText(),
                            txtNombre.getText(),
                            txtApellido.getText(),
                            txtTelefono.getText(),
                            txtDireccion.getText(),
                            txtCorreo.getText(),
                            txtPlaca.getText(),
                            txtModelo.getText(),
                            Integer.parseInt(txtAnio.getText()),
                            "Sin historial",
                            true,
                            "admin",
                            "2026"
                    );

                    if (gestionClientes.agregarCliente(cliente)) {
                        JOptionPane.showMessageDialog(null, "Cliente agregado correctamente");
                        limpiarClientes();
                    } else {
                        JOptionPane.showMessageDialog(null, "El cliente ya existe");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en los datos: " + ex.getMessage());
                }
            }
        });
        btnMostrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaClientes.setText(gestionClientes.toString());
            }
        });
        btnBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtBusquedaCedula.getText();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cédula para buscar");
                    return;
                }

                int indice = gestionClientes.buscarCliente(cedula);

                if (indice != -1) {
                    try {
                        clienteSeleccionado = gestionClientes.getValor(indice);
                        txtCedula.setText(clienteSeleccionado.getCedula());
                        txtNombre.setText(clienteSeleccionado.getNombre());
                        txtApellido.setText(clienteSeleccionado.getApellido());
                        txtTelefono.setText(clienteSeleccionado.getTelefono());
                        txtDireccion.setText(clienteSeleccionado.getDireccion());
                        txtCorreo.setText(clienteSeleccionado.getCorreo());
                        txtPlaca.setText(clienteSeleccionado.getPlaca());
                        txtModelo.setText(clienteSeleccionado.getModeloVehiculo());
                        txtAnio.setText(String.valueOf(clienteSeleccionado.getAnioVehiculo()));
                        areaClientes.setText(clienteSeleccionado.toString());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al buscar: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                }
            }
        });

        btnEditarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtBusquedaCedula.getText();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero busque un cliente");
                    return;
                }

                int indice = gestionClientes.buscarCliente(cedula);

                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                    return;
                }

                String[] opciones = {
                        "Teléfono",
                        "Correo Electrónico",
                        "Dirección",
                        "Placa del Vehículo",
                        "Modelo del Vehículo",
                        "Año del Vehículo",
                        "Cancelar"
                };

                String seleccion = (String) JOptionPane.showInputDialog(
                        null,
                        "SELECCIONE QUÉ DESEA EDITAR:",
                        "Editar Cliente",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (seleccion == null || seleccion.equals("Cancelar")) {
                    return;
                }

                String nuevoValor = JOptionPane.showInputDialog(
                        null,
                        "Ingrese el NUEVO valor para: " + seleccion,
                        "Editar " + seleccion,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (nuevoValor == null || nuevoValor.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se realizaron cambios");
                    return;
                }

                boolean editado = false;

                if (seleccion.equals("Teléfono")) {
                    editado = gestionClientes.editarTelefono(cedula, nuevoValor);
                } else if (seleccion.equals("Correo Electrónico")) {
                    editado = gestionClientes.editarCorreo(cedula, nuevoValor);
                } else if (seleccion.equals("Dirección")) {
                    editado = gestionClientes.editarDireccion(cedula, nuevoValor);
                } else if (seleccion.equals("Placa del Vehículo")) {
                    editado = gestionClientes.editarPlaca(cedula, nuevoValor);
                } else if (seleccion.equals("Modelo del Vehículo")) {
                    editado = gestionClientes.editarModeloVehiculo(cedula, nuevoValor);
                } else if (seleccion.equals("Año del Vehículo")) {
                    try {
                        int anio = Integer.parseInt(nuevoValor);
                        editado = gestionClientes.editarAnioVehiculo(cedula, anio);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error: El año debe ser un número");
                        return;
                    }
                }

                if (editado) {
                    JOptionPane.showMessageDialog(null, "" + seleccion + " actualizado correctamente");

                    int nuevoIndice = gestionClientes.buscarCliente(cedula);
                    if (nuevoIndice != -1) {
                        try {
                            Cliente clienteActualizado = gestionClientes.getValor(nuevoIndice);
                            areaClientes.setText(clienteActualizado.toString());
                            txtTelefono.setText(clienteActualizado.getTelefono());
                            txtCorreo.setText(clienteActualizado.getCorreo());
                            txtDireccion.setText(clienteActualizado.getDireccion());
                            txtPlaca.setText(clienteActualizado.getPlaca());
                            txtModelo.setText(clienteActualizado.getModeloVehiculo());
                            txtAnio.setText(String.valueOf(clienteActualizado.getAnioVehiculo()));
                        } catch (Exception ex) {
                            areaClientes.setText(gestionClientes.toString());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar");
                }
            }
        });

        btnEliminarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtBusquedaCedula.getText();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero busque un cliente");
                    return;
                }

                int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro de anular este cliente?");
                if (confirmar == JOptionPane.YES_OPTION) {
                    boolean eliminado = gestionClientes.anularCliente(cedula, "admin", "2026");
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Cliente anulado correctamente");
                        limpiarClientes();
                        areaClientes.setText("");
                        clienteSeleccionado = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al anular cliente");
                    }
                }
            }
        });

        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Producto producto = new Producto(
                            Integer.parseInt(txtCodigo.getText()),
                            txtNombreProducto.getText(),
                            Double.parseDouble(txtPrecio.getText()),
                            Integer.parseInt(txtCantidad.getText()),
                            txtCategoria.getText(),
                            txtProveedor.getText(),
                            txtFechaCaducidad.getText()
                    );
                    gestionInventario.agregarProducto(producto);
                    gestionInventario.ordenarPorInsercion();
                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                    limpiarProductos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Verifique que los números sean válidos");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en los datos: " + ex.getMessage());
                }
            }
        });

        btnMostrarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaInventario.setText(gestionInventario.toString());
            }
        });

        btnBuscarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoTexto = txtBusquedaCodigo.getText();

                    if (codigoTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese un código para buscar");
                        return;
                    }

                    int codigo = Integer.parseInt(codigoTexto);
                    int indice = gestionInventario.buscarCodigo(codigo);

                    if (indice != -1) {
                        Producto p = gestionInventario.getValor(indice);
                        txtCodigo.setText(String.valueOf(p.getCodigo()));
                        txtNombreProducto.setText(p.getNombre());
                        txtPrecio.setText(String.valueOf(p.getPrecio()));
                        txtCantidad.setText(String.valueOf(p.getCantidad()));
                        txtCategoria.setText(p.getCategoria());
                        txtProveedor.setText(p.getProveedor());
                        txtFechaCaducidad.setText(p.getFechaCaducidad());
                        areaInventario.setText(p.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El código debe ser un número válido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnEditarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoTexto = txtBusquedaCodigo.getText();

                    if (codigoTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Primero busque un producto");
                        return;
                    }

                    int codigo = Integer.parseInt(codigoTexto);
                    int indice = gestionInventario.buscarCodigo(codigo);

                    if (indice == -1) {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado");
                        return;
                    }

                    Producto productoActual = gestionInventario.getValor(indice);

                    String[] opciones = {
                            "Nombre",
                            "Precio",
                            "Cantidad",
                            "Categoría",
                            "Proveedor",
                            "Fecha de Caducidad",
                            "Cancelar"
                    };

                    String seleccion = (String) JOptionPane.showInputDialog(
                            null,
                            "SELECCIONE QUÉ DESEA EDITAR del producto:\n" + productoActual.getNombre(),
                            "Editar Producto",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opciones,
                            opciones[0]
                    );

                    if (seleccion == null || seleccion.equals("Cancelar")) {
                        return;
                    }

                    String nuevoValor = JOptionPane.showInputDialog(
                            null,
                            "Ingrese el NUEVO valor para: " + seleccion,
                            "Editar " + seleccion,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (nuevoValor == null || nuevoValor.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No se realizaron cambios");
                        return;
                    }

                    boolean editado = false;

                    if (seleccion.equals("Nombre")) {
                        editado = gestionInventario.editarNombre(codigo, nuevoValor);
                    } else if (seleccion.equals("Precio")) {
                        try {
                            double precio = Double.parseDouble(nuevoValor);
                            editado = gestionInventario.editarCosto(codigo, precio);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error: Precio inválido");
                            return;
                        }
                    } else if (seleccion.equals("Cantidad")) {
                        try {
                            int cantidad = Integer.parseInt(nuevoValor);
                            editado = gestionInventario.editarCantidad(codigo, cantidad);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error: Cantidad inválida");
                            return;
                        }
                    } else if (seleccion.equals("Categoría")) {
                        editado = gestionInventario.editarCategoria(codigo, nuevoValor);
                    } else if (seleccion.equals("Proveedor")) {
                        editado = gestionInventario.editarProveedor(codigo, nuevoValor);
                    } else if (seleccion.equals("Fecha de Caducidad")) {
                        editado = gestionInventario.editarFechaCaducidad(codigo, nuevoValor);
                    }

                    if (editado) {
                        JOptionPane.showMessageDialog(null, "" + seleccion + " actualizado correctamente");
                        areaInventario.setText(gestionInventario.toString());

                        Producto p = gestionInventario.getValor(indice);
                        txtNombreProducto.setText(p.getNombre());
                        txtPrecio.setText(String.valueOf(p.getPrecio()));
                        txtCantidad.setText(String.valueOf(p.getCantidad()));
                        txtCategoria.setText(p.getCategoria());
                        txtProveedor.setText(p.getProveedor());
                        txtFechaCaducidad.setText(p.getFechaCaducidad());
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Código inválido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoTexto = txtBusquedaCodigo.getText();

                    if (codigoTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese un código para eliminar");
                        return;
                    }

                    int codigo = Integer.parseInt(codigoTexto);
                    int confirmar = JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar este producto?");

                    if (confirmar == JOptionPane.YES_OPTION) {
                        gestionInventario.eliminarProducto(codigo);
                        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                        limpiarProductos();
                        areaInventario.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El código debe ser un número válido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnReporteClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaReportes.setText(gestionClientes.generarReporteClientes());
            }
        });

        btnReporteInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaReportes.setText(gestionInventario.toString());
            }
        });

        btnStockMinimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reporteStock = gestionInventario.generarReporteStockMinimo();
                areaReportes.setText(reporteStock);
            }
        });
    }

    private void limpiarClientes() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtPlaca.setText("");
        txtModelo.setText("");
        txtAnio.setText("");
        txtBusquedaCedula.setText("");
        clienteSeleccionado = null;
    }

    private void limpiarProductos() {
        txtCodigo.setText("");
        txtNombreProducto.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtCategoria.setText("");
        txtProveedor.setText("");
        txtFechaCaducidad.setText("");
        txtBusquedaCodigo.setText("");
    }

    private void $$$setupUI$$$() {
        //Método automáticamente
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema Taller Automotriz");
        frame.setContentPane(new Ventana().ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}