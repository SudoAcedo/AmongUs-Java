package org.example;

public class Tarea {

    private int id;
    private String descripcion;
    private boolean completada;
    private Tripulante tripulanteAsignado;
    private Sala sala;

    private static int contadorIdsTarea = 1;

    public Tarea(String descripcion, Tripulante tripulante, Sala sala) {
        this.id = contadorIdsTarea;
        this.descripcion = descripcion;
        this.tripulanteAsignado = tripulante;
        this.sala = sala;
        contadorIdsTarea++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public Tripulante getTripulanteAsignado() {
        return tripulanteAsignado;
    }

    public void setTripulanteAsignado(Tripulante tripulante) {
        this.tripulanteAsignado = tripulante;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", descripcion='" + descripcion +
                ", completada=" + completada +
                ", tripulanteAsignado=" + tripulanteAsignado +
                ", sala=" + sala +
                '}';
    }
}
