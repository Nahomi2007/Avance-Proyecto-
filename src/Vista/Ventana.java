package Vista;

import Clases.*;
import Gestion.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ventana {

    private JPanel ventana;
    private JTabbedPane tabbedPane1;
    private JTextField txtCedula;
    private JTextField txtNombre;
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
    private JTabbedPane tabbedPane4;
    private JTextField txt_cedula_fac;
    private JButton btnRegistrar;
    private JTextField txtMarcaVehiculo;
    private JTextField txtModeloVehiculo;
    private JTextField txtPlacaVehiculo;
    private JTextField txtAnioVehiculo;
    private JTabbedPane tabbedPane5;
    private JSpinner spnCodigoOrden;
    private JTextField txtCedulaCliente;
    private JButton btnBuscarCliente1;
    private JLabel lblNombreCliente;
    private JLabel lblPlacaVehiculo;
    private JComboBox cmbTecnico;
    private JComboBox cmbMateriales;
    private JButton btnAgregarMaterial;
    private JTextArea areaMateriales;
    private JTextField txtServicio;
    private JTextField txtFechaActual;
    private JLabel lblFechaActual;
    private JTextField yyyyMmDdTextField;
    private JLabel lblFechaEntrega;
    private JComboBox cmbEstado;
    private JTextField txtObservaciones;
    private JButton btnRegistrarOrden;
    private JSpinner spnCodigoOrden1;
    private JButton btnBuscarOrden2;
    private JLabel lblClienteOrden;
    private JLabel lblServicioOrden;
    private JLabel lblEstadoActual;
    private JLabel lblTecnicoOrden;
    private JComboBox cmbNuevoEstado;
    private JButton btnActualizarEstado;
    private JTextField txtNuevaObservacion;
    private JButton btnGuardarObservacion;
    private JTextField txtMotivoAnulacion;
    private JButton btnAnularOrden;
    private JTextArea areaOrdenesServicio;
    private JButton btnReporteOrdenes;
    private JButton btnReporteTecnico;
    private JButton btnKPICumplimiento;
    private JTextArea areaReporteOrdenes;
    private JComboBox cmbTecnicoReporte;

    private JPanel panelCitas;
    private JPanel panelTituloCitas;
    private JLabel lblTituloCitas;
    private JLabel lblSubtituloCitas;
    private JPanel panelBotonesCitas;
    private JPanel filaBotonesCitas;
    private JButton btnBuscarCita;
    private JButton btnReprogramarCita;
    private JButton btnCancelarCita;
    private JButton btnDisponibilidadCita;
    private JButton btnReporteCita;
    private JButton btnRendimientoCita;
    private JPanel filaBusquedaCitas;
    private JLabel lblBuscarCita;
    private JTextField txtBuscarCita;
    private JButton btnRefrescarCita;
    private JLabel lblFiltrarCita;
    private JComboBox<String> cbFiltroCita;
    private JPanel panelTablaCitas;
    private JScrollPane scrollCitas;
    private JTable tablaCitas;
    private JPanel panelEstadoCitas;
    private JLabel lblTotalCitas;
    private JLabel lblEstadoCitas;
    private JLabel lblVersionCitas;
    private JTextField txtNoComprobante;
    private JTextField txtCodigoOrden;
    private JButton btnFactCliente;
    private JTextArea txtADetallesOrden;
    private JTextField txtSubtotal;
    private JTextField txtIVA;
    private JTextField txtTotal;
    private JButton generarFacturaButton;
    private JComboBox cbMetedoPago;
    private JTextField txtBuscarCIfac;
    private JTextArea txtAFacturas;
    private JButton btnBuscarFactura;

    GestionOrdenesServicio gestionOrdenes = new GestionOrdenesServicio();
    GestionClientes gestionClientes = new GestionClientes();
    GestionInventario gestionInventario = new GestionInventario();
    private GestionFinanciera gestionFinanciera = new GestionFinanciera();
    private Cliente clienteSeleccionado = null;

    private GestionCitas gestionCitas;
    private DefaultTableModel modeloTablaCitas;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private GestionHistorial gestionHistorial = new GestionHistorial();
    private DefaultTableModel modeloTablaHistorial;
    private ArrayList<Historial> ultimosResultados = new ArrayList<>();
    private JTextField txtBuscarHistorial;
    private JComboBox<String> cmbCriterioHistorial;
    private JButton btnBuscarHistorial;
    private JLabel lblFichaVehiculo;
    private JPanel panelBusqueda;
    private JPanel panelFicha;
    private JLabel lblFichaCliente;
    private JLabel lblVisitas;
    private JLabel lblTotalGastado;
    private JLabel lblUltimoServicio;
    private JTable tablaHistorial;
    private JButton btnExportarHistorial;

    private JTextField txtUsuarioNombre;
    private JTextField txtUsuarioCorreo;
    private JPasswordField txtUsuarioContrasenia;
    private JComboBox<String> cmbUsuarioRol;
    private JTextField txtBuscarUsuarioCorreo;
    private JButton btnAgregarUsuario;
    private JButton btnBuscarUsuario;
    private JButton btnEditarUsuario;
    private JButton btnDesactivarUsuario;
    private JButton btnResetContrasenia;
    private JButton btnMostrarUsuarios;
    private JButton btnKPIUsuarios;
    private JButton btnMostrarAuditoriaUsuarios;
    private JButton btnLimpiarUsuario;
    private JLabel lblUsuarioActivo;
    private JTextArea areaUsuarios;
    private JTextArea areaAuditoriaUsuarios;
    private JButton btnGenerarReporte;
    private JTextArea txtReportes;
    private JScrollBar scrollBar1;

    private GestionUsuarios gestionUsuarios = new GestionUsuarios();
    private Usuario usuarioActual = null;

    public Ventana() {
        $$$setupUI$$$();

        prepararModuloUsuarios();
        if (!mostrarLoginInicial()) {
            System.exit(0);
        }

        gestionCitas = new GestionCitas(gestionClientes);

        configurarTablaCitas();
        cargarCitasDeEjemplo();
        actualizarTablaCitas();

        cmbTecnico.removeAllItems();
        cmbTecnicoReporte.removeAllItems();

        cmbTecnico.addItem("Renato Imbaquingo");
        cmbTecnico.addItem("Juan Perez");
        cmbTecnico.addItem("Carlos Lopez");

        cmbTecnicoReporte.addItem("Renato Imbaquingo");
        cmbTecnicoReporte.addItem("Juan Perez");
        cmbTecnicoReporte.addItem("Carlos Lopez");

        for (EstadoOrden estado : EstadoOrden.values()) {
            cmbEstado.addItem(estado);
            cmbNuevoEstado.addItem(estado);
        }

        actualizarComboMateriales();

        agregarEventos();
        configurarEventosCitas();
        configurarEventosOrdenes();
        configurarTablaHistorial();
        configurarEventosHistorial();

        btnFactCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txt_cedula_fac.getText().trim();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Ingrese la cedula del cliente.",
                            "Campo vacio", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int indiceCliente = gestionClientes.buscarCliente(cedula);
                if (indiceCliente == -1) {
                    JOptionPane.showMessageDialog(null,
                            "No se encontro ningun cliente con la cedula: " + cedula,
                            "Cliente no encontrado", JOptionPane.ERROR_MESSAGE);
                    txtADetallesOrden.setText("");
                    txtSubtotal.setText("");
                    txtIVA.setText("");
                    txtTotal.setText("");
                    return;
                }

                try {
                    clienteSeleccionado = gestionClientes.getValor(indiceCliente);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,
                            "Error al recuperar el cliente: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                OrdenServicio orden = gestionFinanciera.buscarOrdenParaFacturar(cedula, gestionOrdenes);
                if (orden == null) {
                    JOptionPane.showMessageDialog(null,
                            "El cliente no tiene ordenes FINALIZADAS disponibles para facturar.",
                            "Sin ordenes", JOptionPane.WARNING_MESSAGE);
                    txtADetallesOrden.setText("");
                    txtSubtotal.setText("");
                    txtIVA.setText("");
                    txtTotal.setText("");
                    return;
                }

                if (orden.getEstado() != EstadoOrden.FINALIZADO) {
                    JOptionPane.showMessageDialog(null,
                            "La orden debe estar FINALIZADA para facturar.",
                            "Orden no finalizada", JOptionPane.WARNING_MESSAGE);
                    txtADetallesOrden.setText("");
                    txtSubtotal.setText("");
                    txtIVA.setText("");
                    txtTotal.setText("");
                    return;
                }

                double subtotal = gestionFinanciera.calcularSubtotalMateriales(orden, gestionInventario);
                double iva = gestionFinanciera.calcularIvaAutomatizado(subtotal);
                double total = subtotal + iva;

                StringBuilder detalles = new StringBuilder();
                detalles.append("=== ORDEN DE TRABAJO ===\n");
                detalles.append("Codigo:    ").append(orden.getCodigoOrden()).append("\n");
                detalles.append("Cliente:   ").append(clienteSeleccionado.getNombre())
                        .append(" (CI: ").append(cedula).append(")\n");
                detalles.append("Servicio:  ").append(orden.getDescripcionServicio()).append("\n");
                detalles.append("Materiales:\n");
                if (orden.getMateriales() != null && !orden.getMateriales().isEmpty()) {
                    for (String mat : orden.getMateriales()) {
                        detalles.append("  - ").append(mat).append("\n");
                    }
                } else {
                    detalles.append("  (sin materiales registrados)\n");
                }

                txtADetallesOrden.setText(detalles.toString());
                txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", subtotal));
                txtIVA.setText(String.format(java.util.Locale.US, "%.2f", iva));
                txtTotal.setText(String.format(java.util.Locale.US, "%.2f", total));
            }
        });

        generarFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clienteSeleccionado == null) {
                    JOptionPane.showMessageDialog(null,
                            "Primero busque al cliente con el boton 'Buscar Cliente'.",
                            "Sin cliente", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String subtotalTexto = txtSubtotal.getText().trim();
                if (subtotalTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "No hay datos de orden cargados. Use 'Buscar Cliente' primero.",
                            "Sin datos", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                OrdenServicio orden = gestionFinanciera.buscarOrdenParaFacturar(
                        clienteSeleccionado.getCedula(), gestionOrdenes);
                if (orden == null) {
                    JOptionPane.showMessageDialog(null,
                            "No se encontro la orden finalizada del cliente.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (orden.getEstado() != EstadoOrden.FINALIZADO) {
                    JOptionPane.showMessageDialog(null,
                            "La orden debe estar FINALIZADA para facturar.",
                            "Orden no finalizada", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String metodoPago = (String) cbMetedoPago.getSelectedItem();
                if (metodoPago == null || metodoPago.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Seleccione un metodo de pago.",
                            "Campo vacio", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double subtotal = Double.parseDouble(subtotalTexto);

                if (subtotal <= 0) {
                    JOptionPane.showMessageDialog(null,
                            "El subtotal debe ser mayor a 0.",
                            "Subtotal invalido", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String numeroComprobante = gestionFinanciera.generarSiguienteNumeroComprobante();
                String fechaHoy = LocalDate.now().toString();
                String rucTaller = "1792345678001";
                String serie = numeroComprobante.replace("-", "").substring(0, 6);
                String secuencial = String.format("%09d",
                        Integer.parseInt(numeroComprobante.replace("001-001-", "")));
                String fechaSRI = fechaHoy.replace("-", "").substring(6)
                        + fechaHoy.replace("-", "").substring(4, 6)
                        + fechaHoy.replace("-", "").substring(0, 4);
                String claveSRI = fechaSRI + "01" + rucTaller + "1" + serie + secuencial + "12345678" + "1" + "5";

                Factura nuevaFactura = new Factura(
                        numeroComprobante,
                        fechaHoy,
                        metodoPago,
                        subtotal,
                        0.0,
                        true,
                        "",
                        nombreUsuarioActual(),
                        fechaHoy,
                        claveSRI,
                        clienteSeleccionado.getCedula(),
                        orden.getCodigoOrden()
                );

                boolean exito = gestionFinanciera.emitirFactura(nuevaFactura);

                if (exito) {
                    txtNoComprobante.setText(numeroComprobante);
                    JOptionPane.showMessageDialog(null,
                            "Factura emitida correctamente.\n\n"
                                    + "Comprobante: " + numeroComprobante + "\n"
                                    + "Cliente:     " + clienteSeleccionado.getNombre() + "\n"
                                    + "Total:       $" + txtTotal.getText(),
                            "Factura generada", JOptionPane.INFORMATION_MESSAGE);

                    clienteSeleccionado = null;
                    txtCodigoOrden.setText("");
                    txtADetallesOrden.setText("");
                    txtSubtotal.setText("");
                    txtIVA.setText("");
                    txtTotal.setText("");
                    txtNoComprobante.setText("");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Ya existe una factura con ese numero de comprobante.",
                            "Duplicado", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnBuscarFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtBuscarCIfac.getText().trim();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Ingrese la cedula del cliente para buscar sus facturas.",
                            "Campo vacio", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                ArrayList<Factura> todasLasFacturas = gestionFinanciera.obtenerFacturasOrdenadas();
                StringBuilder resultado = new StringBuilder();
                int contador = 0;

                for (Factura f : todasLasFacturas) {
                    if (f.getCi_cliente().trim().equals(cedula)) {
                        contador++;
                        resultado.append("──────────────────────────────────────────\n");
                        resultado.append("FACTURA #").append(contador).append("\n");
                        resultado.append(f.toString());
                        resultado.append("\n");
                    }
                }

                if (contador == 0) {
                    txtAFacturas.setText("No se encontraron facturas para la cedula: " + cedula);
                } else {
                    resultado.insert(0, "=== FACTURAS DEL CLIENTE: " + cedula
                            + " (" + contador + " encontrada(s)) ===\n\n");
                    txtAFacturas.setText(resultado.toString());
                }
            }
        });

        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputEfectivo = JOptionPane.showInputDialog(null,
                        "Ingrese el monto total de Efectivo Fisico Contado en Caja (Arqueo Manual):",
                        "Auditoria y Cierre de Caja", JOptionPane.QUESTION_MESSAGE);

                if (inputEfectivo == null) {
                    return;
                }

                double efectivoFisicoContado = 0.0;

                if (!inputEfectivo.trim().isEmpty()) {
                    try {
                        efectivoFisicoContado = Double.parseDouble(inputEfectivo.trim().replace(",", "."));

                        if (efectivoFisicoContado < 0) {
                            JOptionPane.showMessageDialog(null,
                                    "El dinero fisico en caja no puede ser un valor negativo.",
                                    "Error de Validacion", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Por favor, ingrese un monto numerico valido (Ejemplo: 150.50).",
                                "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String reporteFinal = gestionFinanciera.generarCierreCajaYRentabilidad(efectivoFisicoContado);

                txtReportes.setText(reporteFinal);
            }
        });
    }

    private void prepararModuloUsuarios() {
        if (cmbUsuarioRol != null) {
            cmbUsuarioRol.removeAllItems();
            cmbUsuarioRol.addItem(RolUsuario.ADMINISTRADOR.getNombreVisible());
            cmbUsuarioRol.addItem(RolUsuario.RECEPCIONISTA.getNombreVisible());
            cmbUsuarioRol.addItem(RolUsuario.TECNICO.getNombreVisible());
        }

        configurarEventosUsuarios();
        actualizarVistaUsuarios();
    }

    private boolean mostrarLoginInicial() {
        JTextField txtLoginCorreo = new JTextField(20);
        JPasswordField txtLoginContrasenia = new JPasswordField(20);

        JPanel panelLogin = new JPanel(new GridLayout(0, 1, 5, 5));
        panelLogin.add(new JLabel("Correo:"));
        panelLogin.add(txtLoginCorreo);
        panelLogin.add(new JLabel("Contrasenia:"));
        panelLogin.add(txtLoginContrasenia);

        while (true) {
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    panelLogin,
                    "Inicio de sesion - Sistema Taller Automotriz",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (opcion != JOptionPane.OK_OPTION) {
                return false;
            }

            String correo = txtLoginCorreo.getText().trim();
            String contrasenia = new String(txtLoginContrasenia.getPassword());

            usuarioActual = gestionUsuarios.iniciarSesion(correo, contrasenia);

            if (usuarioActual != null) {
                JOptionPane.showMessageDialog(
                        null,
                        "Bienvenido/a: " + usuarioActual.getNombre() + "\nRol: " + usuarioActual.getRol().getNombreVisible(),
                        "Acceso correcto",
                        JOptionPane.INFORMATION_MESSAGE
                );

                aplicarRestriccionesMenuPorRol();
                actualizarVistaUsuarios();
                return true;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Correo o contrasenia incorrectos, o usuario inactivo.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE
            );

            txtLoginContrasenia.setText("");
        }
    }

    private void aplicarRestriccionesMenuPorRol() {
        if (usuarioActual == null || tabbedPane1 == null) {
            return;
        }

        RolUsuario rol = usuarioActual.getRol();

        for (int i = tabbedPane1.getTabCount() - 1; i >= 0; i--) {
            String titulo = tabbedPane1.getTitleAt(i);

            if (!rol.puedeVerMenu(titulo)) {
                tabbedPane1.removeTabAt(i);
            }
        }
    }

    private void configurarEventosUsuarios() {
        if (btnAgregarUsuario != null) {
            btnAgregarUsuario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    agregarUsuarioDesdeVentana();
                }
            });
        }

        if (btnBuscarUsuario != null) {
            btnBuscarUsuario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buscarUsuarioDesdeVentana();
                }
            });
        }

        if (btnEditarUsuario != null) {
            btnEditarUsuario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    editarUsuarioDesdeVentana();
                }
            });
        }

        if (btnDesactivarUsuario != null) {
            btnDesactivarUsuario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    desactivarUsuarioDesdeVentana();
                }
            });
        }

        if (btnResetContrasenia != null) {
            btnResetContrasenia.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetearContraseniaDesdeVentana();
                }
            });
        }

        if (btnMostrarUsuarios != null) {
            btnMostrarUsuarios.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actualizarVistaUsuarios();
                }
            });
        }

        if (btnKPIUsuarios != null) {
            btnKPIUsuarios.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (areaUsuarios != null) {
                        areaUsuarios.setText(gestionUsuarios.generarKPIUsuariosActivos());
                    }
                }
            });
        }

        if (btnMostrarAuditoriaUsuarios != null) {
            btnMostrarAuditoriaUsuarios.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (areaAuditoriaUsuarios != null) {
                        areaAuditoriaUsuarios.setText(gestionUsuarios.listarAuditoria());
                    }
                }
            });
        }

        if (btnLimpiarUsuario != null) {
            btnLimpiarUsuario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    limpiarCamposUsuario();
                }
            });
        }
    }

    private void agregarUsuarioDesdeVentana() {
        if (!validarCamposModuloUsuarios()) return;

        String nombre = txtUsuarioNombre.getText().trim();
        String correo = txtUsuarioCorreo.getText().trim();
        String contrasenia = new String(txtUsuarioContrasenia.getPassword()).trim();
        RolUsuario rol = RolUsuario.desdeTexto(String.valueOf(cmbUsuarioRol.getSelectedItem()));

        if (contrasenia.length() < 6) {
            JOptionPane.showMessageDialog(null, "La contrasenia debe tener al menos 6 caracteres.");
            return;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo valido.");
            return;
        }

        Usuario existente = gestionUsuarios.buscarUsuarioPorCorreo(correo);
        if (existente != null) {
            JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese correo.");
            return;
        }

        boolean agregado = gestionUsuarios.agregarUsuario(
                nombre,
                correo,
                contrasenia,
                rol,
                nombreUsuarioActual()
        );

        if (agregado) {
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
            limpiarCamposUsuario();
            actualizarVistaUsuarios();
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo registrar. Verifique campos vacios o correo repetido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void buscarUsuarioDesdeVentana() {
        String correo = txtBuscarUsuarioCorreo.getText().trim();

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el correo del usuario para buscar.");
            return;
        }

        Usuario usuario = gestionUsuarios.buscarUsuarioPorCorreo(correo);

        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            return;
        }

        txtUsuarioNombre.setText(usuario.getNombre());
        txtUsuarioCorreo.setText(usuario.getCorreo());
        txtUsuarioContrasenia.setText(usuario.getContrasenia());
        cmbUsuarioRol.setSelectedItem(usuario.getRol().getNombreVisible());

        if (lblUsuarioActivo != null) {
            lblUsuarioActivo.setText(usuario.isActivo() ? "ACTIVO" : "INACTIVO");
        }

        if (areaUsuarios != null) {
            areaUsuarios.setText(usuario.toString());
        }
    }

    private void editarUsuarioDesdeVentana() {
        String correoOriginal = txtBuscarUsuarioCorreo.getText().trim();

        if (correoOriginal.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero busque un usuario por correo.");
            return;
        }

        Usuario encontrado = gestionUsuarios.buscarUsuarioPorCorreo(correoOriginal);
        if (encontrado == null) {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            return;
        }

        String nuevoNombre = txtUsuarioNombre.getText().trim();
        String nuevoCorreo = txtUsuarioCorreo.getText().trim();
        RolUsuario nuevoRol = RolUsuario.desdeTexto(String.valueOf(cmbUsuarioRol.getSelectedItem()));

        if (!nuevoCorreo.contains("@") || !nuevoCorreo.contains(".")) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo valido.");
            return;
        }

        if (!nuevoCorreo.equals(correoOriginal)) {
            Usuario existente = gestionUsuarios.buscarUsuarioPorCorreo(nuevoCorreo);
            if (existente != null) {
                JOptionPane.showMessageDialog(null, "Ya existe un usuario con ese correo.");
                return;
            }
        }

        boolean editado = gestionUsuarios.editarUsuario(
                correoOriginal,
                nuevoNombre,
                nuevoCorreo,
                nuevoRol,
                nombreUsuarioActual()
        );

        if (editado) {
            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente.");
            txtBuscarUsuarioCorreo.setText(nuevoCorreo);
            actualizarVistaUsuarios();
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se pudo editar. Revise que el nuevo correo no este repetido.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void desactivarUsuarioDesdeVentana() {
        String correo = txtBuscarUsuarioCorreo.getText().trim();

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese o busque el correo del usuario a desactivar.");
            return;
        }

        if (usuarioActual != null && usuarioActual.getCorreo().equalsIgnoreCase(correo)) {
            JOptionPane.showMessageDialog(null,
                    "No puede desactivar el usuario con el que inicio sesion.",
                    "Operacion no permitida", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmar = JOptionPane.showConfirmDialog(
                null,
                "Seguro que desea desactivar este usuario?\n" + correo,
                "Confirmar desactivacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmar != JOptionPane.YES_OPTION) {
            return;
        }

        boolean desactivado = gestionUsuarios.desactivarUsuario(correo, nombreUsuarioActual());

        if (desactivado) {
            JOptionPane.showMessageDialog(null, "Usuario desactivado correctamente.");
            buscarUsuarioDesdeVentana();
            actualizarVistaUsuarios();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo desactivar. Usuario inexistente o ya inactivo.");
        }
    }

    private void resetearContraseniaDesdeVentana() {
        String correo = txtBuscarUsuarioCorreo.getText().trim();

        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero busque el usuario por correo.");
            return;
        }

        JPasswordField nuevaPass = new JPasswordField(20);

        int opcion = JOptionPane.showConfirmDialog(
                null,
                nuevaPass,
                "Nueva contrasenia para: " + correo,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (opcion != JOptionPane.OK_OPTION) {
            return;
        }

        String nuevaContrasenia = new String(nuevaPass.getPassword()).trim();

        if (nuevaContrasenia.length() < 6) {
            JOptionPane.showMessageDialog(null, "La contrasenia debe tener al menos 6 caracteres.");
            return;
        }

        boolean reseteado = gestionUsuarios.resetearContrasenia(
                correo,
                nuevaContrasenia,
                nombreUsuarioActual()
        );

        if (reseteado) {
            JOptionPane.showMessageDialog(null, "Contrasenia reseteada correctamente.");
            txtUsuarioContrasenia.setText(nuevaContrasenia);
            actualizarVistaUsuarios();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo resetear la contrasenia.");
        }
    }

    private boolean validarCamposModuloUsuarios() {
        if (txtUsuarioNombre == null || txtUsuarioCorreo == null || txtUsuarioContrasenia == null || cmbUsuarioRol == null) {
            JOptionPane.showMessageDialog(null,
                    "Faltan componentes del formulario de Usuarios. Revise los bindings del .form.",
                    "Error de interfaz", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String nombre = txtUsuarioNombre.getText().trim();
        String correo = txtUsuarioCorreo.getText().trim();
        String contrasenia = new String(txtUsuarioContrasenia.getPassword()).trim();

        if (nombre.isEmpty() || correo.isEmpty() || contrasenia.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Complete nombre, correo y contrasenia.",
                    "Campos vacios", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!correo.contains("@") || !correo.contains(".")) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese un correo valido.",
                    "Correo invalido", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void actualizarVistaUsuarios() {
        if (areaUsuarios != null) {
            areaUsuarios.setText(gestionUsuarios.listarUsuarios());
        }

        if (areaAuditoriaUsuarios != null) {
            areaAuditoriaUsuarios.setText(gestionUsuarios.listarAuditoria());
        }

        if (lblUsuarioActivo != null) {
            if (usuarioActual != null) {
                lblUsuarioActivo.setText("Sesion: " + usuarioActual.getNombre() + " - " + usuarioActual.getRol().getNombreVisible());
            } else {
                lblUsuarioActivo.setText("Sin sesion iniciada");
            }
        }
    }

    private void limpiarCamposUsuario() {
        if (txtUsuarioNombre != null) txtUsuarioNombre.setText("");
        if (txtUsuarioCorreo != null) txtUsuarioCorreo.setText("");
        if (txtUsuarioContrasenia != null) txtUsuarioContrasenia.setText("");
        if (txtBuscarUsuarioCorreo != null) txtBuscarUsuarioCorreo.setText("");
        if (cmbUsuarioRol != null && cmbUsuarioRol.getItemCount() > 0) cmbUsuarioRol.setSelectedIndex(0);
        if (lblUsuarioActivo != null && usuarioActual != null) {
            lblUsuarioActivo.setText("Sesion: " + usuarioActual.getNombre() + " - " + usuarioActual.getRol().getNombreVisible());
        }
    }

    private String nombreUsuarioActual() {
        if (usuarioActual == null) {
            return "Sistema";
        }
        return usuarioActual.getNombre();
    }

    private void actualizarComboMateriales() {
        cmbMateriales.removeAllItems();
        try {
            for (int i = 0; i < gestionInventario.getSize(); i++) {
                Producto p = gestionInventario.getValor(i);
                if (p.getCantidad() > 0) {
                    cmbMateriales.addItem(p.getCodigo() + " - " + p.getNombre());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarCitasDeEjemplo() {
        try {
            if (gestionClientes.getSize() == 0) {
                return;
            }

            Cliente cliente = gestionClientes.getValor(0);

            String[] servicios = {
                    "Cambio de aceite",
                    "Alineacion y balanceo",
                    "Revision general",
                    "Cambio de frenos",
                    "Lavado completo"
            };

            LocalDate hoy = LocalDate.of(2024, 1, 15);

            for (int i = 0; i < 3; i++) {
                String servicio = servicios[i % servicios.length];
                LocalDate fechaCita = hoy.plusDays(i);

                Cita nuevaCita = gestionCitas.registrarCita(
                        cliente.getCedula(),
                        servicio,
                        fechaCita,
                        nombreUsuarioActual()
                );

                if (i == 1) {
                    nuevaCita.reprogramar(fechaCita.plusDays(1), nombreUsuarioActual());
                }
            }

            if (gestionClientes.getSize() > 1) {
                Cliente cliente2 = gestionClientes.getValor(1);
                gestionCitas.registrarCita(
                        cliente2.getCedula(),
                        "Cambio de frenos",
                        hoy.plusDays(4),
                        nombreUsuarioActual()
                );
            }

            if (gestionClientes.getSize() > 2) {
                Cliente cliente3 = gestionClientes.getValor(2);
                Cita citaFinalizada = gestionCitas.registrarCita(
                        cliente3.getCedula(),
                        "Lavado completo",
                        hoy.plusDays(5),
                        nombreUsuarioActual()
                );
                citaFinalizada.finalizar();
            }

            actualizarTablaCitas();

        } catch (Exception e) {

        }
    }

    private void configurarTablaCitas() {
        modeloTablaCitas = new DefaultTableModel(
                new Object[]{"ID", "Cliente", "Vehiculo", "Servicio", "Fecha", "Hora", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaCitas.setModel(modeloTablaCitas);
        tablaCitas.setRowHeight(30);
        tablaCitas.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaCitas.setShowGrid(true);
        tablaCitas.setGridColor(new Color(230, 230, 230));

        tablaCitas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaCitas.getTableHeader().setBackground(new Color(44, 62, 80));
        tablaCitas.getTableHeader().setForeground(Color.WHITE);
        tablaCitas.getTableHeader().setPreferredSize(new Dimension(0, 35));

        tablaCitas.getColumnModel().getColumn(0).setPreferredWidth(50);
        tablaCitas.getColumnModel().getColumn(1).setPreferredWidth(180);
        tablaCitas.getColumnModel().getColumn(2).setPreferredWidth(140);
        tablaCitas.getColumnModel().getColumn(3).setPreferredWidth(160);
        tablaCitas.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaCitas.getColumnModel().getColumn(5).setPreferredWidth(80);
        tablaCitas.getColumnModel().getColumn(6).setPreferredWidth(120);
    }

    private void actualizarTablaCitas() {
        modeloTablaCitas.setRowCount(0);

        String filtro = (String) cbFiltroCita.getSelectedItem();
        List<Cita> citas;

        if (filtro == null || filtro.equals("Todas")) {
            citas = gestionCitas.obtenerTodasCitas();
        } else if (filtro.equals("Activas")) {
            citas = gestionCitas.obtenerCitasActivas();
        } else if (filtro.equals("Canceladas")) {
            citas = gestionCitas.obtenerCitasCanceladas();
        } else if (filtro.equals("Finalizadas")) {
            citas = gestionCitas.obtenerTodasCitas().stream()
                    .filter(c -> c.getEstado() == EstadoCita.FINALIZADA)
                    .collect(java.util.stream.Collectors.toList());
        } else if (filtro.equals("Reprogramadas")) {
            citas = gestionCitas.obtenerTodasCitas().stream()
                    .filter(c -> c.getEstado() == EstadoCita.REPROGRAMADA)
                    .collect(java.util.stream.Collectors.toList());
        } else {
            citas = gestionCitas.obtenerTodasCitas();
        }

        for (Cita cita : citas) {
            modeloTablaCitas.addRow(new Object[]{
                    cita.getId(),
                    cita.getCliente().getNombre(),
                    cita.getVehiculo().getPlaca() + " - " + cita.getVehiculo().getModelo(),
                    cita.getServicio(),
                    cita.getFecha().format(formatter),
                    cita.getHora(),
                    cita.getEstadoString()
            });
        }

        lblTotalCitas.setText("Total: " + gestionCitas.getTotalCitas());
        lblEstadoCitas.setText(gestionCitas.obtenerCitasActivas().size() + " activas");
    }

    private void configurarEventosCitas() {
        btnBuscarCita.addActionListener(e -> {
            String busqueda = txtBuscarCita.getText().trim();
            if (busqueda.isEmpty()) {
                actualizarTablaCitas();
                return;
            }

            List<Cita> resultados = new ArrayList<>();

            try {
                int id = Integer.parseInt(busqueda);
                Cita cita = gestionCitas.buscarPorId(id);
                if (cita != null) {
                    resultados.add(cita);
                }
            } catch (NumberFormatException ex) {
                for (Cita c : gestionCitas.obtenerTodasCitas()) {
                    String nombreCliente = c.getCliente().getNombre().toLowerCase();
                    String cedulaCliente = c.getCliente().getCedula();

                    if (nombreCliente.contains(busqueda.toLowerCase()) ||
                            cedulaCliente.equals(busqueda)) {
                        resultados.add(c);
                    }
                }
            }

            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No se encontro ninguna cita para: " + busqueda,
                        "No Encontrado",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            modeloTablaCitas.setRowCount(0);
            for (Cita c : resultados) {
                modeloTablaCitas.addRow(new Object[]{
                        c.getId(),
                        c.getCliente().getNombre(),
                        c.getVehiculo().getPlaca() + " - " + c.getVehiculo().getModelo(),
                        c.getServicio(),
                        c.getFecha().format(formatter),
                        c.getHora(),
                        c.getEstadoString()
                });
            }
            lblTotalCitas.setText("Resultados: " + resultados.size());
        });

        btnReprogramarCita.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(null,
                    "Ingrese el ID de la cita a reprogramar:",
                    "Reprogramar Cita", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    Cita cita = gestionCitas.buscarPorId(id);

                    if (cita == null) {
                        JOptionPane.showMessageDialog(null, "Cita no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (cita.isCancelada()) {
                        JOptionPane.showMessageDialog(null, "No se puede reprogramar una cita cancelada",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String nuevaFechaStr = JOptionPane.showInputDialog(null,
                            "Fecha actual: " + cita.getFecha().format(formatter) + "\nIngrese la nueva fecha (dd/MM/yyyy):",
                            "Nueva Fecha", JOptionPane.QUESTION_MESSAGE);

                    if (nuevaFechaStr != null && !nuevaFechaStr.isEmpty()) {
                        try {
                            LocalDate nuevaFecha = LocalDate.parse(nuevaFechaStr, formatter);

                            if (nuevaFecha.isBefore(LocalDate.now())) {
                                JOptionPane.showMessageDialog(null,
                                        "La nueva fecha debe ser hoy o posterior.",
                                        "Fecha invalida", JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                            gestionCitas.reprogramarCita(id, nuevaFecha, nombreUsuarioActual());
                            actualizarTablaCitas();
                            JOptionPane.showMessageDialog(null, "Cita reprogramada exitosamente",
                                    "Exito", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Formato de fecha invalido. Use dd/MM/yyyy",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancelarCita.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog(null,
                    "Ingrese el ID de la cita a cancelar:",
                    "Cancelar Cita", JOptionPane.QUESTION_MESSAGE);

            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    Cita cita = gestionCitas.buscarPorId(id);

                    if (cita == null) {
                        JOptionPane.showMessageDialog(null, "Cita no encontrada",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (cita.isCancelada()) {
                        JOptionPane.showMessageDialog(null, "La cita ya esta cancelada",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String[] motivos = {"Cliente no asistio", "Cliente cancelo", "Error en agendamiento", "Otro"};
                    String motivo = (String) JOptionPane.showInputDialog(null,
                            "Seleccione el motivo de cancelacion:",
                            "Motivo", JOptionPane.QUESTION_MESSAGE, null, motivos, motivos[0]);

                    if (motivo == null) return;

                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Esta seguro de cancelar la cita?\nCliente: " + cita.getCliente().getNombre() +
                                    "\nFecha: " + cita.getFecha().format(formatter),
                            "Confirmar cancelacion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (confirm == JOptionPane.YES_OPTION) {
                        gestionCitas.cancelarCita(id, motivo, nombreUsuarioActual());
                        actualizarTablaCitas();
                        JOptionPane.showMessageDialog(null, "Cita cancelada exitosamente",
                                "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDisponibilidadCita.addActionListener(e -> {
            String disponibilidad = gestionCitas.mostrarDisponibilidadSemana(LocalDate.now());
            JTextArea textArea = new JTextArea(disponibilidad);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
            textArea.setEditable(false);
            JScrollPane scroll = new JScrollPane(textArea);
            scroll.setPreferredSize(new Dimension(450, 300));
            JOptionPane.showMessageDialog(null, scroll, "Disponibilidad de Citas",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        btnReporteCita.addActionListener(e -> {
            String reporte = gestionCitas.generarReporteDiario(LocalDate.now());
            JTextArea textArea = new JTextArea(reporte);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            textArea.setEditable(false);
            JScrollPane scroll = new JScrollPane(textArea);
            scroll.setPreferredSize(new Dimension(600, 400));
            JOptionPane.showMessageDialog(null, scroll, "Reporte Diario",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        btnRendimientoCita.addActionListener(e -> {
            String kpis = gestionCitas.generarKPIs();
            JTextArea textArea = new JTextArea(kpis);
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
            textArea.setEditable(false);
            JScrollPane scroll = new JScrollPane(textArea);
            scroll.setPreferredSize(new Dimension(500, 400));
            JOptionPane.showMessageDialog(null, scroll, "Indicadores de Rendimiento",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        btnRefrescarCita.addActionListener(e -> {
            actualizarTablaCitas();
            JOptionPane.showMessageDialog(null, "Tabla actualizada",
                    "Actualizado", JOptionPane.INFORMATION_MESSAGE);
        });

        cbFiltroCita.addActionListener(e -> actualizarTablaCitas());

        txtBuscarCita.addActionListener(e -> btnBuscarCita.doClick());
    }

    private void configurarEventosOrdenes() {
        btnBuscarCliente1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedulaCliente.getText().trim();
                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cedula.");
                    return;
                }
                int indice = gestionClientes.buscarCliente(cedula);
                if (indice != -1) {
                    try {
                        Cliente cliente = gestionClientes.getValor(indice);
                        lblNombreCliente.setText(cliente.getNombre());
                        lblPlacaVehiculo.setText(cliente.getPlaca());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    lblNombreCliente.setText("");
                    lblPlacaVehiculo.setText("");
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                }
            }
        });

        btnAgregarMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbMateriales.getSelectedItem() == null) return;
                String material = cmbMateriales.getSelectedItem().toString();
                areaMateriales.append(material + "\n");
            }
        });

        btnRegistrarOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoOrden = spnCodigoOrden.getValue().toString();
                    String cedulaCliente = txtCedulaCliente.getText().trim();
                    int indiceCliente = gestionClientes.buscarCliente(cedulaCliente);
                    if (indiceCliente == -1) {
                        JOptionPane.showMessageDialog(null, "Debe buscar un cliente valido.");
                        return;
                    }
                    String cedulaTecnico = cmbTecnico.getSelectedItem().toString();

                    if (cedulaTecnico == null || cedulaTecnico.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Seleccione un tecnico responsable.");
                        return;
                    }

                    String servicio = txtServicio.getText().trim();

                    if (servicio.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El servicio es obligatorio.");
                        return;
                    }

                    String fechaCreacion = txtFechaActual.getText().trim();
                    String fechaEntrega = yyyyMmDdTextField.getText().trim();

                    if (fechaEntrega.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La fecha de entrega es obligatoria.");
                        return;
                    }

                    String fechaEntregaLimpia = fechaEntrega.replace("/", "-");
                    try {
                        LocalDate fechaEntregaDate = LocalDate.parse(fechaEntregaLimpia);
                        if (fechaEntregaDate.isBefore(LocalDate.now())) {
                            JOptionPane.showMessageDialog(null, "La fecha de entrega debe ser hoy o posterior.");
                            return;
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Formato de fecha invalido. Use dd/MM/yyyy");
                        return;
                    }

                    String observaciones = txtObservaciones.getText().trim();
                    EstadoOrden estado = EstadoOrden.valueOf(cmbEstado.getSelectedItem().toString());

                    if (estado == null) {
                        JOptionPane.showMessageDialog(null, "Seleccione un estado valido.");
                        return;
                    }

                    ArrayList<String> materialesOrden = new ArrayList<>();
                    String[] materiales = areaMateriales.getText().split("\n");
                    for (String material : materiales) {
                        if (!material.trim().isEmpty()) {
                            String[] partes = material.split(" - ");
                            if (partes.length >= 1) {
                                try {
                                    int codigo = Integer.parseInt(partes[0].trim());
                                    int indice = gestionInventario.buscarCodigo(codigo);
                                    if (indice == -1) {
                                        JOptionPane.showMessageDialog(null, "El material con codigo " + codigo + " no existe en inventario.");
                                        return;
                                    }
                                    Producto p = gestionInventario.getValor(indice);
                                    if (p.getCantidad() < 1) {
                                        JOptionPane.showMessageDialog(null, "No hay stock suficiente del material: " + p.getNombre());
                                        return;
                                    }
                                    materialesOrden.add(partes[0].trim());
                                } catch (Exception ex) {
                                    materialesOrden.add(material);
                                }
                            }
                        }
                    }

                    OrdenServicio orden = new OrdenServicio(
                            codigoOrden, cedulaCliente, cedulaTecnico, servicio,
                            materialesOrden, estado, observaciones, "", "",
                            fechaCreacion, fechaEntrega, "", true, "",
                            nombreUsuarioActual(), LocalDate.now().toString()
                    );

                    boolean registrado = gestionOrdenes.agregarOrden(orden);
                    if (registrado) {
                        for (String material : materialesOrden) {
                            try {
                                int codigo = Integer.parseInt(material.trim());
                                gestionInventario.descontarMaterial(codigo, 1);
                            } catch (NumberFormatException ex) {
                            }
                        }
                        actualizarComboMateriales();

                        JOptionPane.showMessageDialog(null, "Orden registrada correctamente.");

                        try {
                            Cliente cliente = gestionClientes.getValor(indiceCliente);

                            boolean yaTieneCita = false;
                            for (Cita c : gestionCitas.obtenerTodasCitas()) {
                                if (c.getCliente().getCedula().equals(cedulaCliente) &&
                                        c.isActiva()) {
                                    yaTieneCita = true;
                                    break;
                                }
                            }

                            if (!yaTieneCita) {
                                String servicioCita = servicio;
                                LocalDate fechaCita = LocalDate.parse(fechaEntregaLimpia);

                                String[] horas = {"08:00", "09:00", "10:00", "11:00", "12:00",
                                        "13:00", "14:00", "15:00", "16:00", "17:00"};
                                String horaCita = horas[Integer.parseInt(codigoOrden) % horas.length];

                                Cita nuevaCita = gestionCitas.registrarCita(
                                        cedulaCliente,
                                        servicioCita,
                                        fechaCita,
                                        horaCita,
                                        nombreUsuarioActual()
                                );
                                actualizarTablaCitas();

                                JOptionPane.showMessageDialog(null,
                                        "Cita creada automaticamente:\n" +
                                                "Cliente: " + cliente.getNombre() + "\n" +
                                                "Servicio: " + servicioCita + "\n" +
                                                "Fecha: " + fechaCita + "\n" +
                                                "Hora: " + horaCita,
                                        "Cita creada",
                                        JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "El cliente ya tiene una cita activa.\n" +
                                                "No se creara una cita duplicada.",
                                        "Cita existente",
                                        JOptionPane.INFORMATION_MESSAGE);
                            }

                        } catch (Exception ex) {
                            System.out.println("Error al crear cita: " + ex.getMessage());
                        }

                        spnCodigoOrden.setValue(Integer.parseInt(codigoOrden) + 1);
                        txtCedulaCliente.setText("");
                        lblNombreCliente.setText("----------");
                        lblPlacaVehiculo.setText("----------");
                        txtServicio.setText("");
                        txtObservaciones.setText("");
                        areaMateriales.setText("");
                        cmbEstado.setSelectedItem("PENDIENTE");
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya existe una orden con ese codigo.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnBuscarOrden2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = spnCodigoOrden1.getValue().toString();
                OrdenServicio orden = gestionOrdenes.buscarOrdenObjeto(codigo);
                if (orden == null) {
                    JOptionPane.showMessageDialog(null, "Orden no encontrada");
                    return;
                }
                lblClienteOrden.setText(orden.getCedulaCliente());
                lblTecnicoOrden.setText(orden.getCedulaTecnico());
                lblServicioOrden.setText(orden.getDescripcionServicio());
                lblEstadoActual.setText(orden.getEstado().toString());
                areaOrdenesServicio.setText(orden.toString());
            }
        });

        btnActualizarEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = spnCodigoOrden1.getValue().toString();
                EstadoOrden nuevoEstado = (EstadoOrden) cmbNuevoEstado.getSelectedItem();

                if (nuevoEstado == null) {
                    JOptionPane.showMessageDialog(null, "Seleccione un estado valido.");
                    return;
                }

                boolean actualizado = gestionOrdenes.actualizarEstado(
                        codigo, nuevoEstado, nombreUsuarioActual(), LocalDate.now().toString()
                );

                if (actualizado) {
                    if (nuevoEstado == EstadoOrden.FINALIZADO) {
                        registrarEnHistorial(codigo);
                        OrdenServicio ord = gestionOrdenes.buscarOrdenObjeto(codigo);
                        if (ord != null) {
                            gestionCitas.finalizarCitasDeCliente(ord.getCedulaCliente());
                            actualizarTablaCitas();
                            JOptionPane.showMessageDialog(null,
                                    "Orden finalizada. La cita del cliente ha sido marcada como FINALIZADA.",
                                    "Sincronizacion completada",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Estado actualizado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro la orden");
                }
            }
        });

        btnGuardarObservacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = spnCodigoOrden1.getValue().toString();
                String observacion = txtNuevaObservacion.getText();

                if (observacion.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese una observacion.");
                    return;
                }

                boolean actualizado = gestionOrdenes.actualizarObservaciones(
                        codigo, observacion, nombreUsuarioActual(), LocalDate.now().toString()
                );
                if (actualizado) {
                    JOptionPane.showMessageDialog(null, "Observacion guardada");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro la orden");
                }
            }
        });

        btnAnularOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = spnCodigoOrden1.getValue().toString();
                String motivo = txtMotivoAnulacion.getText();

                if (motivo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese un motivo de anulacion.");
                    return;
                }

                boolean anulada = gestionOrdenes.anularOrden(
                        codigo, motivo, nombreUsuarioActual(), LocalDate.now().toString()
                );
                if (anulada) {
                    JOptionPane.showMessageDialog(null, "Orden anulada correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontro la orden");
                }
            }
        });

        btnReporteOrdenes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaReporteOrdenes.setText(gestionOrdenes.toString());
            }
        });

        btnReporteTecnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tecnico = cmbTecnicoReporte.getSelectedItem().toString();
                areaReporteOrdenes.setText(gestionOrdenes.listarOrdenesPorTecnico(tecnico));
            }
        });

        btnKPICumplimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaReporteOrdenes.setText(gestionOrdenes.generarReporteCumplimiento());
            }
        });
    }

    private void agregarEventos() {
        txtFechaActual.setText(LocalDate.now().toString());

        cmbEstado.removeAllItems();
        cmbEstado.addItem("PENDIENTE");
        cmbEstado.addItem("EN_PROCESO");
        cmbEstado.addItem("FINALIZADO");

        lblNombreCliente.setText("----------");
        lblPlacaVehiculo.setText("----------");

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText().trim();
                String nombre = txtNombre.getText().trim();
                String telefono = txtTelefono.getText().trim();
                String correo = txtCorreo.getText().trim();
                String direccion = txtDireccion.getText().trim();

                String marca = txtMarcaVehiculo.getText().trim();
                String modelo = txtModeloVehiculo.getText().trim();
                String placa = txtPlacaVehiculo.getText().trim();
                String anioStr = txtAnioVehiculo.getText().trim();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La cedula es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtCedula.requestFocus();
                    return;
                }

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtNombre.requestFocus();
                    return;
                }

                if (telefono.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El telefono es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtTelefono.requestFocus();
                    return;
                }

                if (marca.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La marca es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtMarcaVehiculo.requestFocus();
                    return;
                }

                if (modelo.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El modelo es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtModeloVehiculo.requestFocus();
                    return;
                }

                if (placa.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "La placa es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtPlacaVehiculo.requestFocus();
                    return;
                }

                if (anioStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El año es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtAnioVehiculo.requestFocus();
                    return;
                }

                if (!correo.isEmpty() && (!correo.contains("@") || !correo.contains("."))) {
                    JOptionPane.showMessageDialog(null, "Ingrese un correo valido.", "Error", JOptionPane.ERROR_MESSAGE);
                    txtCorreo.requestFocus();
                    return;
                }

                int anio;
                try {
                    anio = Integer.parseInt(anioStr);
                    int anioActual = LocalDate.now().getYear();
                    if (anio < 1950 || anio > anioActual + 1) {
                        JOptionPane.showMessageDialog(null, "El año debe estar entre 1950 y " + (anioActual + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                        txtAnioVehiculo.requestFocus();
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un año valido (solo numeros).", "Error", JOptionPane.ERROR_MESSAGE);
                    txtAnioVehiculo.requestFocus();
                    return;
                }

                int indiceExistente = gestionClientes.buscarClienteSimple(cedula);
                if (indiceExistente != -1) {
                    JOptionPane.showMessageDialog(null, "Ya existe un cliente registrado con la cedula: " + cedula, "Registro Duplicado", JOptionPane.ERROR_MESSAGE);
                    txtCedula.requestFocus();
                    return;
                }

                Cliente nuevoCliente = new Cliente(
                        cedula, nombre, telefono, direccion, correo,
                        placa, modelo, marca, anio, "Sin registros previos.",
                        true, nombreUsuarioActual(), LocalDate.now().toString()
                );

                if (gestionClientes.agregarCliente(nuevoCliente)) {
                    JOptionPane.showMessageDialog(null, "Cliente y Vehiculo registrados con exito.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                    limpiarClientes();
                    areaClientes.setText(gestionClientes.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnMostrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestionClientes.getSize() == 0) {
                    JOptionPane.showMessageDialog(null, "No hay clientes registrados.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    areaClientes.setText("No hay clientes registrados.");
                    return;
                }
                areaClientes.setText(gestionClientes.toString());
            }
        });

        btnBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtBusquedaCedula.getText().trim();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cedula para buscar.", "Error", JOptionPane.WARNING_MESSAGE);
                    txtBusquedaCedula.requestFocus();
                    return;
                }

                int indice = gestionClientes.buscarCliente(cedula);

                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "No se encontro cliente con cedula: " + cedula, "No Encontrado", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    clienteSeleccionado = gestionClientes.getValor(indice);
                    txtCedula.setText(clienteSeleccionado.getCedula());
                    txtNombre.setText(clienteSeleccionado.getNombre());
                    txtTelefono.setText(clienteSeleccionado.getTelefono());
                    txtDireccion.setText(clienteSeleccionado.getDireccion());
                    txtCorreo.setText(clienteSeleccionado.getCorreo());
                    txtPlacaVehiculo.setText(clienteSeleccionado.getPlaca());
                    txtModeloVehiculo.setText(clienteSeleccionado.getModeloVehiculo());
                    txtMarcaVehiculo.setText(clienteSeleccionado.getMarcaVehiculo());
                    txtAnioVehiculo.setText(String.valueOf(clienteSeleccionado.getAnioVehiculo()));
                    areaClientes.setText(clienteSeleccionado.toString());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEditarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtBusquedaCedula.getText().trim();
                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero busque un cliente.");
                    return;
                }

                int indice = gestionClientes.buscarCliente(cedula);
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    return;
                }

                String[] opciones = {
                        "Telefono", "Correo Electronico", "Direccion",
                        "Placa del Vehiculo", "Modelo del Vehiculo", "Año del Vehiculo",
                        "Cancelar"
                };
                String seleccion = (String) JOptionPane.showInputDialog(
                        null, "SELECCIONE QUE DESEA EDITAR:",
                        "Editar Cliente", JOptionPane.QUESTION_MESSAGE,
                        null, opciones, opciones[0]);

                if (seleccion == null || seleccion.equals("Cancelar")) return;

                String nuevoValor = JOptionPane.showInputDialog(
                        null, "Ingrese el NUEVO valor para: " + seleccion,
                        "Editar " + seleccion, JOptionPane.QUESTION_MESSAGE);

                if (nuevoValor == null || nuevoValor.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se realizaron cambios.");
                    return;
                }

                nuevoValor = nuevoValor.trim();
                boolean editado = false;

                if (seleccion.equals("Telefono")) {
                    editado = gestionClientes.editarTelefono(cedula, nuevoValor);
                } else if (seleccion.equals("Correo Electronico")) {
                    if (!nuevoValor.isEmpty() && (!nuevoValor.contains("@") || !nuevoValor.contains("."))) {
                        JOptionPane.showMessageDialog(null, "Ingrese un correo valido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    editado = gestionClientes.editarCorreo(cedula, nuevoValor);
                } else if (seleccion.equals("Direccion")) {
                    editado = gestionClientes.editarDireccion(cedula, nuevoValor);
                } else if (seleccion.equals("Placa del Vehiculo")) {
                    editado = gestionClientes.editarPlaca(cedula, nuevoValor);
                } else if (seleccion.equals("Modelo del Vehiculo")) {
                    editado = gestionClientes.editarModeloVehiculo(cedula, nuevoValor);
                } else if (seleccion.equals("Año del Vehiculo")) {
                    try {
                        int anio = Integer.parseInt(nuevoValor);
                        int anioActual = LocalDate.now().getYear();
                        if (anio < 1950 || anio > anioActual + 1) {
                            JOptionPane.showMessageDialog(null, "El año debe estar entre 1950 y " + (anioActual + 1) + ".", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        editado = gestionClientes.editarAnioVehiculo(cedula, anio);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Ingrese un año valido (solo numeros).", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (editado) {
                    JOptionPane.showMessageDialog(null, seleccion + " actualizado correctamente.");
                    actualizarClienteEnVista(cedula);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar.");
                }
            }
        });

        btnEliminarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txtBusquedaCedula.getText().trim();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero busque un cliente para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int indice = gestionClientes.buscarCliente(cedula);
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "No se encontro el cliente.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirmar = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de eliminar este cliente?\nCedula: " + cedula,
                        "Confirmar eliminacion",
                        JOptionPane.YES_NO_OPTION);

                if (confirmar == JOptionPane.YES_OPTION) {
                    boolean eliminado = gestionClientes.eliminarCliente(cedula);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        limpiarClientes();
                        areaClientes.setText(gestionClientes.toString());
                        clienteSeleccionado = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoStr = txtCodigo.getText().trim();

                    if (codigoStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El codigo es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                        txtCodigo.requestFocus();
                        return;
                    }

                    int codigo = Integer.parseInt(codigoStr);

                    int indiceExistente = gestionInventario.buscarCodigo(codigo);
                    if (indiceExistente != -1) {
                        JOptionPane.showMessageDialog(null, "Ya existe un producto con el codigo: " + codigo, "Error", JOptionPane.ERROR_MESSAGE);
                        txtCodigo.requestFocus();
                        return;
                    }

                    String nombre = txtNombreProducto.getText().trim();
                    if (nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                        txtNombreProducto.requestFocus();
                        return;
                    }

                    String precioStr = txtPrecio.getText().trim();
                    if (precioStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El precio es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                        txtPrecio.requestFocus();
                        return;
                    }

                    double precio = Double.parseDouble(precioStr);
                    if (precio <= 0) {
                        JOptionPane.showMessageDialog(null, "El precio debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                        txtPrecio.requestFocus();
                        return;
                    }

                    String cantidadStr = txtCantidad.getText().trim();
                    if (cantidadStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La cantidad es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
                        txtCantidad.requestFocus();
                        return;
                    }

                    int cantidad = Integer.parseInt(cantidadStr);
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                        txtCantidad.requestFocus();
                        return;
                    }

                    String categoria = txtCategoria.getText().trim();
                    if (categoria.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La categoria es obligatoria.", "Error", JOptionPane.ERROR_MESSAGE);
                        txtCategoria.requestFocus();
                        return;
                    }

                    String proveedor = txtProveedor.getText().trim();
                    if (proveedor.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El proveedor es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
                        txtProveedor.requestFocus();
                        return;
                    }

                    String fechaCaducidad = txtFechaCaducidad.getText().trim();
                    if (!fechaCaducidad.isEmpty()) {
                        try {
                            LocalDate.parse(fechaCaducidad);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Formato de fecha invalido. Use YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
                            txtFechaCaducidad.requestFocus();
                            return;
                        }
                    }

                    Producto producto = new Producto(
                            codigo,
                            nombre,
                            precio,
                            cantidad,
                            categoria,
                            proveedor,
                            fechaCaducidad
                    );

                    gestionInventario.agregarProducto(producto);
                    cmbMateriales.addItem(producto.getCodigo() + " - " + producto.getNombre());
                    gestionInventario.ordenarPorInsercion();

                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    limpiarProductos();
                    areaInventario.setText(gestionInventario.toString());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Verifique que los numeros sean validos.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnMostrarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gestionInventario.getSize() == 0) {
                    JOptionPane.showMessageDialog(null, "No hay productos registrados.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    areaInventario.setText("No hay productos registrados.");
                    return;
                }
                areaInventario.setText(gestionInventario.toString());
            }
        });

        btnBuscarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoTexto = txtBusquedaCodigo.getText().trim();

                    if (codigoTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese un codigo para buscar.", "Error", JOptionPane.WARNING_MESSAGE);
                        txtBusquedaCodigo.requestFocus();
                        return;
                    }

                    int codigo = Integer.parseInt(codigoTexto);
                    int indice = gestionInventario.buscarCodigo(codigo);

                    if (indice == -1) {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.", "No Encontrado", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Producto p = gestionInventario.getValor(indice);
                    txtCodigo.setText(String.valueOf(p.getCodigo()));
                    txtNombreProducto.setText(p.getNombre());
                    txtPrecio.setText(String.valueOf(p.getPrecio()));
                    txtCantidad.setText(String.valueOf(p.getCantidad()));
                    txtCategoria.setText(p.getCategoria());
                    txtProveedor.setText(p.getProveedor());
                    txtFechaCaducidad.setText(p.getFechaCaducidad());
                    areaInventario.setText(p.toString());

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El codigo debe ser un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEditarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoTexto = txtBusquedaCodigo.getText().trim();

                    if (codigoTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Primero busque un producto.", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int codigo = Integer.parseInt(codigoTexto);
                    int indice = gestionInventario.buscarCodigo(codigo);

                    if (indice == -1) {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Producto productoActual = gestionInventario.getValor(indice);
                    String[] opciones = {
                            "Nombre", "Precio", "Cantidad", "Categoria",
                            "Proveedor", "Fecha de Caducidad", "Cancelar"
                    };

                    String seleccion = (String) JOptionPane.showInputDialog(
                            null, "SELECCIONE QUE DESEA EDITAR del producto:\n" + productoActual.getNombre(),
                            "Editar Producto", JOptionPane.QUESTION_MESSAGE,
                            null, opciones, opciones[0]);

                    if (seleccion == null || seleccion.equals("Cancelar")) return;

                    String nuevoValor = JOptionPane.showInputDialog(
                            null, "Ingrese el NUEVO valor para: " + seleccion,
                            "Editar " + seleccion, JOptionPane.QUESTION_MESSAGE);

                    if (nuevoValor == null || nuevoValor.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No se realizaron cambios.");
                        return;
                    }

                    boolean editado = false;

                    if (seleccion.equals("Nombre")) {
                        if (nuevoValor.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        editado = gestionInventario.editarNombre(codigo, nuevoValor);
                    } else if (seleccion.equals("Precio")) {
                        try {
                            double precio = Double.parseDouble(nuevoValor);
                            if (precio <= 0) {
                                JOptionPane.showMessageDialog(null, "El precio debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            editado = gestionInventario.editarCosto(codigo, precio);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error: Precio invalido.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else if (seleccion.equals("Cantidad")) {
                        try {
                            int cantidad = Integer.parseInt(nuevoValor);
                            if (cantidad < 0) {
                                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor o igual a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            editado = gestionInventario.editarCantidad(codigo, cantidad);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error: Cantidad invalida.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } else if (seleccion.equals("Categoria")) {
                        if (nuevoValor.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "La categoria no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        editado = gestionInventario.editarCategoria(codigo, nuevoValor);
                    } else if (seleccion.equals("Proveedor")) {
                        if (nuevoValor.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "El proveedor no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        editado = gestionInventario.editarProveedor(codigo, nuevoValor);
                    } else if (seleccion.equals("Fecha de Caducidad")) {
                        if (!nuevoValor.isEmpty()) {
                            try {
                                LocalDate.parse(nuevoValor);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Formato de fecha invalido. Use YYYY-MM-DD", "Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        }
                        editado = gestionInventario.editarFechaCaducidad(codigo, nuevoValor);
                    }

                    if (editado) {
                        JOptionPane.showMessageDialog(null, seleccion + " actualizado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        areaInventario.setText(gestionInventario.toString());
                        Producto p = gestionInventario.getValor(indice);
                        txtNombreProducto.setText(p.getNombre());
                        txtPrecio.setText(String.valueOf(p.getPrecio()));
                        txtCantidad.setText(String.valueOf(p.getCantidad()));
                        txtCategoria.setText(p.getCategoria());
                        txtProveedor.setText(p.getProveedor());
                        txtFechaCaducidad.setText(p.getFechaCaducidad());
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Codigo invalido.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoTexto = txtBusquedaCodigo.getText().trim();

                    if (codigoTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese un codigo para eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int codigo = Integer.parseInt(codigoTexto);
                    int indice = gestionInventario.buscarCodigo(codigo);

                    if (indice == -1) {
                        JOptionPane.showMessageDialog(null, "Producto no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    int confirmar = JOptionPane.showConfirmDialog(null,
                            "Esta seguro de eliminar este producto?\nCodigo: " + codigo,
                            "Confirmar eliminacion",
                            JOptionPane.YES_NO_OPTION);

                    if (confirmar == JOptionPane.YES_OPTION) {
                        gestionInventario.eliminarProducto(codigo);
                        actualizarComboMateriales();
                        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        limpiarProductos();
                        areaInventario.setText(gestionInventario.toString());
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El codigo debe ser un numero valido.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    private void actualizarClienteEnVista(String cedula) {
        int indice = gestionClientes.buscarCliente(cedula);
        if (indice != -1) {
            try {
                Cliente c = gestionClientes.getValor(indice);
                areaClientes.setText(c.toString());
            } catch (Exception ex) {
                areaClientes.setText(gestionClientes.toString());
            }
        }
    }

    private void configurarTablaHistorial() {
        modeloTablaHistorial = new DefaultTableModel(
                new Object[]{"Fecha", "Orden", "Servicio", "Tecnico", "Costo"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaHistorial.setModel(modeloTablaHistorial);
        tablaHistorial.setRowHeight(30);
        tablaHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tablaHistorial.setShowGrid(true);
        tablaHistorial.setGridColor(new Color(230, 230, 230));

        tablaHistorial.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaHistorial.getTableHeader().setBackground(new Color(44, 62, 80));
        tablaHistorial.getTableHeader().setForeground(Color.WHITE);
        tablaHistorial.getTableHeader().setPreferredSize(new Dimension(0, 35));

        tablaHistorial.getColumnModel().getColumn(0).setPreferredWidth(100);
        tablaHistorial.getColumnModel().getColumn(1).setPreferredWidth(70);
        tablaHistorial.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablaHistorial.getColumnModel().getColumn(3).setPreferredWidth(150);
        tablaHistorial.getColumnModel().getColumn(4).setPreferredWidth(90);
    }

    private void configurarEventosHistorial() {
        cmbCriterioHistorial.removeAllItems();
        cmbCriterioHistorial.addItem("Por placa");
        cmbCriterioHistorial.addItem("Por cedula");
        cmbCriterioHistorial.addItem("Por fecha");
        cmbCriterioHistorial.addItem("Por codigo orden");

        btnBuscarHistorial.addActionListener(e -> {
            String texto = txtBuscarHistorial.getText().trim();
            String criterio = (String) cmbCriterioHistorial.getSelectedItem();

            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor para buscar.");
                return;
            }

            ArrayList<Historial> resultados;
            if ("Por cedula".equals(criterio)) {
                resultados = gestionHistorial.buscarPorCedula(texto);
            } else if ("Por fecha".equals(criterio)) {
                resultados = gestionHistorial.buscarPorFecha(texto);
            } else if ("Por codigo orden".equals(criterio)) {
                resultados = new ArrayList<>();
                for (Historial h : gestionHistorial.obtenerTodos()) {
                    if (h.getCodigoOrden().equalsIgnoreCase(texto)) {
                        resultados.add(h);
                    }
                }
            } else {
                resultados = gestionHistorial.buscarPorPlaca(texto);
            }

            modeloTablaHistorial.setRowCount(0);
            for (Historial h : resultados) {
                modeloTablaHistorial.addRow(new Object[]{
                        h.getFecha(),
                        h.getCodigoOrden(),
                        h.getServicio(),
                        h.getTecnico(),
                        String.format(java.util.Locale.US, "$%.2f", h.getCosto())
                });
            }

            ultimosResultados = resultados;

            if (!resultados.isEmpty()) {
                String placaFicha = resultados.get(0).getPlaca();
                lblVisitas.setText(String.valueOf(gestionHistorial.contarVisitas(placaFicha)));
                lblTotalGastado.setText(String.format(java.util.Locale.US, "$%.2f", gestionHistorial.totalGastado(placaFicha)));
                lblUltimoServicio.setText(gestionHistorial.ultimoServicio(placaFicha));

                String cedula = resultados.get(0).getCedulaCliente();
                int idx = gestionClientes.buscarCliente(cedula);
                if (idx != -1) {
                    try {
                        Cliente c = gestionClientes.getValor(idx);
                        lblFichaVehiculo.setText(c.getPlaca() + " - " + c.getMarcaVehiculo()
                                + " " + c.getModeloVehiculo() + " (" + c.getAnioVehiculo() + ")");
                        lblFichaCliente.setText(c.getNombre() + " - CI " + c.getCedula());
                    } catch (Exception ex) {
                    }
                }
            } else {
                lblFichaVehiculo.setText("----------");
                lblFichaCliente.setText("----------");
                lblVisitas.setText("0");
                lblTotalGastado.setText("$0.00");
                lblUltimoServicio.setText("-");
            }
        });

        btnExportarHistorial.addActionListener(e -> {
            String reporte = gestionHistorial.formatearResultados(ultimosResultados);
            JTextArea area = new JTextArea(reporte);
            area.setFont(new Font("Monospaced", Font.PLAIN, 12));
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setPreferredSize(new Dimension(500, 400));
            JOptionPane.showMessageDialog(null, scroll, "Ficha del vehiculo",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void registrarEnHistorial(String codigoOrden) {
        OrdenServicio orden = gestionOrdenes.buscarOrdenObjeto(codigoOrden);
        if (orden == null) return;

        for (Historial h : gestionHistorial.obtenerTodos()) {
            if (h.getCodigoOrden().equalsIgnoreCase(codigoOrden)) return;
        }

        String placa = "SIN PLACA";
        int idx = gestionClientes.buscarCliente(orden.getCedulaCliente());
        if (idx != -1) {
            try {
                placa = gestionClientes.getValor(idx).getPlaca();
            } catch (Exception ex) {
            }
        }

        double subtotal = gestionFinanciera.calcularSubtotalMateriales(orden, gestionInventario);
        double costo = subtotal + gestionFinanciera.calcularIvaAutomatizado(subtotal);

        if (costo <= 0) {
            costo = 0.01;
        }

        gestionHistorial.agregarRegistro(
                orden.getCodigoOrden(),
                LocalDate.now().toString(),
                placa,
                orden.getCedulaCliente(),
                orden.getDescripcionServicio(),
                orden.getCedulaTecnico(),
                costo
        );
    }

    private void limpiarClientes() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtCorreo.setText("");
        txtMarcaVehiculo.setText("");
        txtModeloVehiculo.setText("");
        txtPlacaVehiculo.setText("");
        txtAnioVehiculo.setText("");
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