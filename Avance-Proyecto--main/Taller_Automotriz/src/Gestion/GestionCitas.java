package Gestion;

import Clases.Cita;
import Clases.Cliente;
import Clases.Vehiculo;
import Clases.EstadoCita;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class GestionCitas {

    private List<Cita> citas;
    private Stack<Cita> historialCanceladas;
    private TreeMap<String, Integer> capacidadPorDia;
    private HashMap<String, Integer> conteoServicios;
    private int contadorId;
    private GestionClientes gestionClientes;

    public GestionCitas(GestionClientes gestionClientes) {
        this.citas = new ArrayList<>();
        this.historialCanceladas = new Stack<>();
        this.capacidadPorDia = new TreeMap<>();
        this.conteoServicios = new HashMap<>();
        this.contadorId = 1;
        this.gestionClientes = gestionClientes;

        capacidadPorDia.put("LUNES", 50);
        capacidadPorDia.put("MARTES", 50);
        capacidadPorDia.put("MIERCOLES", 50);
        capacidadPorDia.put("JUEVES", 50);
        capacidadPorDia.put("VIERNES", 50);
        capacidadPorDia.put("SABADO", 30);
        capacidadPorDia.put("DOMINGO", 0);
    }

    // ========== REGISTRAR CITA ==========
    public Cita registrarCita(String cedulaCliente, String servicio, LocalDate fecha, String hora, String usuario) {
        int indice = gestionClientes.buscarClienteSimple(cedulaCliente);
        if (indice == -1) {
            throw new IllegalArgumentException("Cliente no encontrado: " + cedulaCliente);
        }

        Cliente cliente;
        try {
            cliente = gestionClientes.getValor(indice);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al obtener cliente: " + e.getMessage());
        }

        Vehiculo vehiculo = new Vehiculo(
                cliente.getPlaca(),
                cliente.getMarcaVehiculo(),
                cliente.getModeloVehiculo(),
                cliente.getAnioVehiculo()
        );

        Cita cita = new Cita(contadorId++, cliente, vehiculo, servicio, fecha, usuario);
        cita.setHora(hora);
        citas.add(cita);

        conteoServicios.put(servicio, conteoServicios.getOrDefault(servicio, 0) + 1);
        return cita;
    }

    public Cita registrarCita(String cedulaCliente, String servicio, LocalDate fecha, String usuario) {
        return registrarCita(cedulaCliente, servicio, fecha, "08:00", usuario);
    }

    // ========== BUSCAR ==========
    public Cita buscarPorId(int id) {
        for (Cita c : citas) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public List<Cita> buscarPorCedulaCliente(String cedula) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getCliente().getCedula().equals(cedula)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Cita> buscarPorNombreCliente(String nombre) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getCliente().getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public List<Cita> buscarPorEstado(EstadoCita estado) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getEstado() == estado) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    // ========== CANCELAR ==========
    public boolean cancelarCita(int id, String motivo, String usuario) {
        Cita cita = buscarPorId(id);
        if (cita != null && !cita.isCancelada()) {
            cita.cancelar(motivo, usuario);
            historialCanceladas.push(cita);
            return true;
        }
        return false;
    }

    // ========== REPROGRAMAR ==========
    public boolean reprogramarCita(int id, LocalDate nuevaFecha, String usuario) {
        Cita cita = buscarPorId(id);
        if (cita != null && !cita.isCancelada()) {
            cita.reprogramar(nuevaFecha, usuario);
            return true;
        }
        return false;
    }

    public boolean reprogramarCita(int id, LocalDate nuevaFecha, String nuevaHora, String usuario) {
        Cita cita = buscarPorId(id);
        if (cita != null && !cita.isCancelada()) {
            cita.reprogramar(nuevaFecha, nuevaHora, usuario);
            return true;
        }
        return false;
    }

    // ========== DISPONIBILIDAD ==========
    public String mostrarDisponibilidadSemana(LocalDate inicio) {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════\n");
        sb.append("  DISPONIBILIDAD SEMANAL\n");
        sb.append("═══════════════════════════════════════════\n\n");

        for (int i = 0; i < 7; i++) {
            LocalDate fecha = inicio.plusDays(i);
            String dia = fecha.getDayOfWeek().toString();
            sb.append(String.format("%s %s: 🟢 100 cupos\n", dia.substring(0, 3), fecha));
        }
        return sb.toString();
    }

    // ========== REPORTE DIARIO ==========
    public String generarReporteDiario(LocalDate fecha) {
        StringBuilder reporte = new StringBuilder();
        reporte.append("═══════════════════════════════════════════\n");
        reporte.append("  REPORTE DIARIO DE CITAS\n");
        reporte.append("═══════════════════════════════════════════\n");
        reporte.append("Fecha: ").append(fecha).append("\n\n");

        List<Cita> citasDelDia = new ArrayList<>();
        for (Cita c : citas) {
            if (c.getFecha().equals(fecha) && !c.isCancelada()) {
                citasDelDia.add(c);
            }
        }

        if (citasDelDia.isEmpty()) {
            reporte.append("No hay citas programadas para hoy.\n");
        } else {
            reporte.append("  VEHÍCULOS ESPERADOS:\n");
            reporte.append("───────────────────────────\n");
            for (Cita c : citasDelDia) {
                reporte.append(String.format("  • %s - %s\n",
                        c.getVehiculo().getPlaca(),
                        c.getCliente().getNombre()));
                reporte.append("    Servicio: ").append(c.getServicio()).append("\n");
                reporte.append("    Teléfono: ").append(c.getCliente().getTelefono()).append("\n");
            }
        }
        return reporte.toString();
    }

    // ========== KPIS ==========
    public String generarKPIs() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════════\n");
        sb.append("  INDICADORES \n");
        sb.append("═══════════════════════════════════════════\n\n");

        int total = citas.size();
        int activas = 0;
        int canceladas = historialCanceladas.size();
        int finalizadas = 0;

        for (Cita c : citas) {
            if (c.isActiva()) activas++;
            if (c.getEstado() == EstadoCita.FINALIZADA) finalizadas++;
        }

        sb.append("  ESTADÍSTICAS GENERALES:\n");
        sb.append("───────────────────────────\n");
        sb.append("Total citas: ").append(total).append("\n");
        sb.append("Citas activas: ").append(activas).append("\n");
        sb.append("Citas canceladas: ").append(canceladas).append("\n");
        sb.append("Citas finalizadas: ").append(finalizadas).append("\n");

        double indiceAusencia = total > 0 ? (double) canceladas / total * 100 : 0;
        sb.append("Índice de ausencia: ").append(String.format("%.2f%%", indiceAusencia)).append("\n\n");

        sb.append("  SERVICIOS MÁS POPULARES:\n");
        sb.append("─────────────────────────────────────\n");
        for (Map.Entry<String, Integer> entry : conteoServicios.entrySet()) {
            sb.append("  • ").append(entry.getKey()).append(": ").append(entry.getValue()).append(" citas\n");
        }

        return sb.toString();
    }

    // ========== GETTERS ==========
    public List<Cita> obtenerTodasCitas() {
        return new ArrayList<>(citas);
    }

    public List<Cita> obtenerCitasActivas() {
        List<Cita> activas = new ArrayList<>();
        for (Cita c : citas) {
            if (c.isActiva()) {
                activas.add(c);
            }
        }
        return activas;
    }

    public List<Cita> obtenerCitasCanceladas() {
        return new ArrayList<>(historialCanceladas);
    }

    public Stack<Cita> getHistorialCanceladas() {
        return historialCanceladas;
    }

    public int getTotalCitas() {
        return citas.size();
    }

}