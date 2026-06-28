package Clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita implements Comparable<Cita> {
    /**Declaración de atributos*/
    private int id;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private String servicio;
    private LocalDate fecha;
    private String hora;
    private EstadoCita estado;
    private LocalDateTime fechaCreacion;
    private String motivoCancelacion;
    private String usuarioCreacion;

    /**Creacion del constructor*/
    public Cita(int id, Cliente cliente, Vehiculo vehiculo, String servicio, LocalDate fecha, String usuario) {
        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = "08:00";
        this.estado = EstadoCita.ACTIVA;
        this.fechaCreacion = LocalDateTime.now();
        this.motivoCancelacion = "";
        this.usuarioCreacion = usuario;
    }

    /**Creacion de getters y setters*/
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public String getServicio() { return servicio; }
    public LocalDate getFecha() { return fecha; }
    public String getHora() { return hora; }
    public EstadoCita getEstado() { return estado; }
    public String getEstadoString() { return estado.toString(); }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public String getMotivoCancelacion() { return motivoCancelacion; }
    public String getUsuarioCreacion() { return usuarioCreacion; }
    public void setHora(String hora) { this.hora = hora; }

    /**Metodos propios*/
    public void cancelar(String motivo, String usuario) {
        this.estado = EstadoCita.CANCELADA;
        this.motivoCancelacion = motivo + " (por: " + usuario + ")";
    }

    public void reprogramar(LocalDate nuevaFecha, String usuario) {
        this.fecha = nuevaFecha;
        this.estado = EstadoCita.REPROGRAMADA;
    }

    public void reprogramar(LocalDate nuevaFecha, String nuevaHora, String usuario) {
        this.fecha = nuevaFecha;
        this.hora = nuevaHora;
        this.estado = EstadoCita.REPROGRAMADA;
    }

    public void finalizar() {
        this.estado = EstadoCita.FINALIZADA;
    }

    public boolean isActiva() { return estado == EstadoCita.ACTIVA; }
    public boolean isCancelada() { return estado == EstadoCita.CANCELADA; }

    @Override
    public int compareTo(Cita otra) {
        return this.fecha.compareTo(otra.fecha);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Cita #%d - %s - %s - %s",
                id, cliente.getNombre(), servicio, fecha.format(formatter));
    }
}