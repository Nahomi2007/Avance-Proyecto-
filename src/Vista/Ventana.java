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
    private JButton btanGenerarReportes;
    private JTextArea txtReportes;

    GestionOrdenesServicio gestionOrdenes = new GestionOrdenesServicio();
    GestionClientes gestionClientes = new GestionClientes();
    GestionInventario gestionInventario = new GestionInventario();
    private GestionFinanciera gestionFinanciera = new GestionFinanciera();
    private Cliente clienteSeleccionado = null;

    private GestionCitas gestionCitas;
    private DefaultTableModel modeloTablaCitas;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Ventana() {
        $$$setupUI$$$();

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

        btnFactCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cedula = txt_cedula_fac.getText().trim();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Ingrese la cédula del cliente.",
                            "Campo vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int indiceCliente = gestionClientes.buscarCliente(cedula);
                if (indiceCliente == -1) {
                    JOptionPane.showMessageDialog(null,
                            "No se encontró ningún cliente con la cédula: " + cedula,
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
                            "El cliente no tiene órdenes FINALIZADAS disponibles para facturar.",
                            "Sin órdenes", JOptionPane.WARNING_MESSAGE);
                    txtADetallesOrden.setText("");
                    txtSubtotal.setText("");
                    txtIVA.setText("");
                    txtTotal.setText("");
                    return;
                }

                double subtotal = gestionFinanciera.calcularSubtotalMateriales(orden);
                double iva      = gestionFinanciera.calcularIvaAutomatizado(subtotal);
                double total    = subtotal + iva;

                StringBuilder detalles = new StringBuilder();
                detalles.append("=== ORDEN DE TRABAJO ===\n");
                detalles.append("Código:    ").append(orden.getCodigoOrden()).append("\n");
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
                txtSubtotal.setText(String.format("%.2f", subtotal));
                txtIVA.setText(String.format("%.2f", iva));
                txtTotal.setText(String.format("%.2f", total));
            }
        });

        generarFacturaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clienteSeleccionado == null) {
                    JOptionPane.showMessageDialog(null,
                            "Primero busque al cliente con el botón 'Buscar Cliente'.",
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
                            "No se encontró la orden finalizada del cliente.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String metodoPago = (String) cbMetedoPago.getSelectedItem();
                if (metodoPago == null || metodoPago.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Seleccione un método de pago.",
                            "Campo vacío", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                double subtotal = Double.parseDouble(subtotalTexto);

                // Generación automática del número de comprobante y clave SRI
                String numeroComprobante = gestionFinanciera.generarSiguienteNumeroComprobante();
                String fechaHoy = LocalDate.now().toString(); // ej: 2026-06-25

                // Clave de acceso SRI de 49 dígitos: fecha(8) + tipo(2) + RUC(13) + ambiente(1) +
                // serie(6) + secuencial(9) + codigo(8) + emision(1) + digito verificador(1)
                String rucTaller  = "1792345678001";
                String serie      = numeroComprobante.replace("-", "").substring(0, 6);
                String secuencial = String.format("%09d",
                        Integer.parseInt(numeroComprobante.replace("001-001-", "")));
                String fechaSRI   = fechaHoy.replace("-", "").substring(6)
                        + fechaHoy.replace("-", "").substring(4, 6)
                        + fechaHoy.replace("-", "").substring(0, 4);
                String claveSRI   = fechaSRI + "01" + rucTaller + "1" + serie + secuencial + "12345678" + "1" + "5";

                Factura nuevaFactura = new Factura(
                        numeroComprobante,
                        fechaHoy,
                        metodoPago,
                        subtotal,
                        0.0,       // descuento
                        true,      // activo
                        "",        // motivoAnulacion
                        "Sistema", // usuarioModificacion
                        fechaHoy,  // fechaModificacion
                        claveSRI,
                        clienteSeleccionado.getCedula(),
                        orden.getCodigoOrden()
                );

                boolean exito = gestionFinanciera.emitirFactura(nuevaFactura);

                if (exito) {
                    txtNoComprobante.setText(numeroComprobante);
                    JOptionPane.showMessageDialog(null,
                            "✅ Factura emitida correctamente.\n\n"
                                    + "Comprobante: " + numeroComprobante + "\n"
                                    + "Cliente:     " + clienteSeleccionado.getNombre() + "\n"
                                    + "Total:       $" + txtTotal.getText(),
                            "Factura generada", JOptionPane.INFORMATION_MESSAGE);

                    // Limpiar formulario para siguiente factura
                    clienteSeleccionado = null;
                    txtCodigoOrden.setText("");
                    txtADetallesOrden.setText("");
                    txtSubtotal.setText("");
                    txtIVA.setText("");
                    txtTotal.setText("");
                    txtNoComprobante.setText("");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Ya existe una factura con ese número de comprobante.",
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
                            "Ingrese la cédula del cliente para buscar sus facturas.",
                            "Campo vacío", JOptionPane.WARNING_MESSAGE);
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
                    txtAFacturas.setText("No se encontraron facturas para la cédula: " + cedula);
                } else {
                    resultado.insert(0, "=== FACTURAS DEL CLIENTE: " + cedula
                            + " (" + contador + " encontrada(s)) ===\n\n");
                    txtAFacturas.setText(resultado.toString());
                }
            }
        });

        btanGenerarReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputEfectivo = JOptionPane.showInputDialog(null,
                        "Ingrese el monto total de Efectivo Físico Contado en Caja (Arqueo Manual):",
                        "Auditoría y Cierre de Caja", JOptionPane.QUESTION_MESSAGE);

                if (inputEfectivo == null) {
                    return;
                }

                double efectivoFisicoContado = 0.0;

                if (!inputEfectivo.trim().isEmpty()) {
                    try {
                        // Reemplazamos comas por puntos para evitar errores de formato regional
                        efectivoFisicoContado = Double.parseDouble(inputEfectivo.trim().replace(",", "."));

                        if (efectivoFisicoContado < 0) {
                            JOptionPane.showMessageDialog(null,
                                    "El dinero físico en caja no puede ser un valor negativo.",
                                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,
                                "Por favor, ingrese un monto numérico válido (Ejemplo: 150.50).",
                                "Formato Incorrecto", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                String reporteFinal = gestionFinanciera.generarCierreCajaYRentabilidad(efectivoFisicoContado);

                txtReportes.setText(reporteFinal);

                JOptionPane.showMessageDialog(null,
                        "Cierre de jornada y conciliación completada con éxito.",
                        "Auditoría Finalizada", JOptionPane.INFORMATION_MESSAGE);
            }

        });
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
                        "Administrador"
                );

                if (i == 1) {
                    nuevaCita.reprogramar(fechaCita.plusDays(1), "Administrador");
                }
            }

            if (gestionClientes.getSize() > 1) {
                Cliente cliente2 = gestionClientes.getValor(1);
                gestionCitas.registrarCita(
                        cliente2.getCedula(),
                        "Cambio de frenos",
                        hoy.plusDays(4),
                        "Administrador"
                );
            }

            if (gestionClientes.getSize() > 2) {
                Cliente cliente3 = gestionClientes.getValor(2);
                Cita citaFinalizada = gestionCitas.registrarCita(
                        cliente3.getCedula(),
                        "Lavado completo",
                        hoy.plusDays(5),
                        "Administrador"
                );
                citaFinalizada.finalizar();
            }

            actualizarTablaCitas();

        } catch (Exception e) {
            // Silencioso
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
                        LocalDate nuevaFecha = LocalDate.parse(nuevaFechaStr, formatter);
                        gestionCitas.reprogramarCita(id, nuevaFecha, "Administrador");
                        actualizarTablaCitas();
                        JOptionPane.showMessageDialog(null, "Cita reprogramada exitosamente",
                                "Exito", JOptionPane.INFORMATION_MESSAGE);
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
                        gestionCitas.cancelarCita(id, motivo, "Administrador");
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
                    String servicio = txtServicio.getText().trim();
                    String fechaCreacion = txtFechaActual.getText().trim();
                    String fechaEntrega = yyyyMmDdTextField.getText().trim();
                    String observaciones = txtObservaciones.getText().trim();
                    EstadoOrden estado = EstadoOrden.valueOf(cmbEstado.getSelectedItem().toString());

                    if (servicio.isEmpty() || fechaEntrega.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Complete todos los campos.");
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
                                    materialesOrden.add(partes[0].trim());
                                } catch (NumberFormatException ex) {
                                    materialesOrden.add(material);
                                }
                            }
                        }
                    }

                    OrdenServicio orden = new OrdenServicio(
                            codigoOrden, cedulaCliente, cedulaTecnico, servicio,
                            materialesOrden, estado, observaciones, "", "",
                            fechaCreacion, fechaEntrega, "", true, "",
                            "Administrador", LocalDate.now().toString()
                    );

                    boolean registrado = gestionOrdenes.agregarOrden(orden);
                    if (registrado) {
                        for (String material : materialesOrden) {
                            try {
                                int codigo = Integer.parseInt(material.trim());
                                gestionInventario.descontarMaterial(codigo, 1);
                            } catch (NumberFormatException ex) {
                                // Ignorar si no es un numero
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
                                String fechaEntregaLimpia = fechaEntrega.replace("/", "-");
                                LocalDate fechaCita = LocalDate.parse(fechaEntregaLimpia);

                                String[] horas = {"08:00", "09:00", "10:00", "11:00", "12:00",
                                        "13:00", "14:00", "15:00", "16:00", "17:00"};
                                String horaCita = horas[Integer.parseInt(codigoOrden) % horas.length];

                                Cita nuevaCita = gestionCitas.registrarCita(
                                        cedulaCliente,
                                        servicioCita,
                                        fechaCita,
                                        horaCita,
                                        "Administrador"
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
                boolean actualizado = gestionOrdenes.actualizarEstado(
                        codigo, nuevoEstado, "Administrador", LocalDate.now().toString()
                );
                if (actualizado) {
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
                boolean actualizado = gestionOrdenes.actualizarObservaciones(
                        codigo, observacion, "Administrador", LocalDate.now().toString()
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
                boolean anulada = gestionOrdenes.anularOrden(
                        codigo, motivo, "Administrador", LocalDate.now().toString()
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

                if (cedula.isEmpty() || nombre.isEmpty() || placa.isEmpty() || modelo.isEmpty() || anioStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, complete los campos obligatorios:\nCedula, Nombre, Placa, Modelo y Año.",
                            "Campos Vacios", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int anio;
                try {
                    anio = Integer.parseInt(anioStr);
                    int anioActual = 2026;
                    if (anio < 1950 || anio > anioActual + 1) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Por favor, ingrese un año de vehiculo valido.",
                            "Error de Formato", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Cliente nuevoCliente = new Cliente(
                        cedula, nombre, telefono, direccion, correo,
                        placa, modelo, marca, anio, "Sin registros previos.",
                        true, "Administrador", LocalDate.now().toString()
                );

                if (gestionClientes.agregarCliente(nuevoCliente)) {
                    JOptionPane.showMessageDialog(null,
                            "Cliente y Vehiculo registrados con exito.",
                            "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                    limpiarClientes();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Error: Ya existe un cliente registrado con la cedula Nro: " + cedula,
                            "Registro Duplicado", JOptionPane.ERROR_MESSAGE);
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
                String cedula = txtBusquedaCedula.getText().trim();
                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cedula para buscar");
                    return;
                }
                int indice = gestionClientes.buscarCliente(cedula);
                if (indice != -1) {
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
                String cedula = txtBusquedaCedula.getText().trim();
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
                    JOptionPane.showMessageDialog(null, "No se realizaron cambios");
                    return;
                }
                nuevoValor = nuevoValor.trim();
                boolean editado = false;
                if (seleccion.equals("Telefono")) {
                    editado = gestionClientes.editarTelefono(cedula, nuevoValor);
                } else if (seleccion.equals("Correo Electronico")) {
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
                        editado = gestionClientes.editarAnioVehiculo(cedula, anio);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Error: El año debe ser un numero");
                        return;
                    }
                }
                if (editado) {
                    JOptionPane.showMessageDialog(null, seleccion + " actualizado correctamente");
                    int nuevoIndice = gestionClientes.buscarCliente(cedula);
                    if (nuevoIndice != -1) {
                        try {
                            Cliente clienteActualizado = gestionClientes.getValor(nuevoIndice);
                            areaClientes.setText(clienteActualizado.toString());
                            txtTelefono.setText(clienteActualizado.getTelefono());
                            txtCorreo.setText(clienteActualizado.getCorreo());
                            txtDireccion.setText(clienteActualizado.getDireccion());
                            txtPlacaVehiculo.setText(clienteActualizado.getPlaca());
                            txtModeloVehiculo.setText(clienteActualizado.getModeloVehiculo());
                            txtAnioVehiculo.setText(String.valueOf(clienteActualizado.getAnioVehiculo()));
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
                String cedula = txtBusquedaCedula.getText().trim();

                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero busque un cliente");
                    return;
                }

                int indice = gestionClientes.buscarCliente(cedula);
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                    return;
                }

                int confirmar = JOptionPane.showConfirmDialog(null,
                        "Esta seguro de eliminar este cliente?",
                        "Confirmar eliminacion",
                        JOptionPane.YES_NO_OPTION);

                if (confirmar == JOptionPane.YES_OPTION) {
                    boolean eliminado = gestionClientes.eliminarCliente(cedula);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
                        limpiarClientes();
                        areaClientes.setText("");
                        clienteSeleccionado = null;
                        areaClientes.setText(gestionClientes.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar cliente");
                    }
                }
            }
        });

        btnAgregarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Producto producto = new Producto(
                            Integer.parseInt(txtCodigo.getText().trim()),
                            txtNombreProducto.getText().trim(),
                            Double.parseDouble(txtPrecio.getText().trim()),
                            Integer.parseInt(txtCantidad.getText().trim()),
                            txtCategoria.getText().trim(),
                            txtProveedor.getText().trim(),
                            txtFechaCaducidad.getText().trim()
                    );
                    gestionInventario.agregarProducto(producto);
                    cmbMateriales.addItem(producto.getCodigo() + " - " + producto.getNombre());
                    gestionInventario.ordenarPorInsercion();
                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                    limpiarProductos();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Verifique que los numeros sean validos");
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
                    String codigoTexto = txtBusquedaCodigo.getText().trim();
                    if (codigoTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese un codigo para buscar");
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
                    JOptionPane.showMessageDialog(null, "Error: El codigo debe ser un numero valido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnEditarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoTexto = txtBusquedaCodigo.getText().trim();
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
                            JOptionPane.showMessageDialog(null, "Error: Precio invalido");
                            return;
                        }
                    } else if (seleccion.equals("Cantidad")) {
                        try {
                            int cantidad = Integer.parseInt(nuevoValor);
                            editado = gestionInventario.editarCantidad(codigo, cantidad);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Error: Cantidad invalida");
                            return;
                        }
                    } else if (seleccion.equals("Categoria")) {
                        editado = gestionInventario.editarCategoria(codigo, nuevoValor);
                    } else if (seleccion.equals("Proveedor")) {
                        editado = gestionInventario.editarProveedor(codigo, nuevoValor);
                    } else if (seleccion.equals("Fecha de Caducidad")) {
                        editado = gestionInventario.editarFechaCaducidad(codigo, nuevoValor);
                    }
                    if (editado) {
                        JOptionPane.showMessageDialog(null, seleccion + " actualizado correctamente");
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
                    JOptionPane.showMessageDialog(null, "Error: Codigo invalido");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String codigoTexto = txtBusquedaCodigo.getText().trim();
                    if (codigoTexto.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ingrese un codigo para eliminar");
                        return;
                    }
                    int codigo = Integer.parseInt(codigoTexto);
                    int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro de eliminar este producto?");
                    if (confirmar == JOptionPane.YES_OPTION) {
                        gestionInventario.eliminarProducto(codigo);
                        actualizarComboMateriales();
                        JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                        limpiarProductos();
                        areaInventario.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: El codigo debe ser un numero valido");
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
        // Generado automaticamente por el diseñador GUI de IntelliJ
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