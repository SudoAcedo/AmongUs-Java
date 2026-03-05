package org.example;

public class Tarea {

    private int id;
    private String descripcion;
    private boolean completada;
    /**todo falta que se creen las clases*/
     private Tripulante tripulanteAsignado;
     private Sala sala;

    public Tarea(String descripcion, Tripulante tripulante, Sala sala) {
        this.descripcion = descripcion;
        this.tripulanteAsignado = tripulante;
        this.sala = sala;
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

    @Override
    public String toString() {
        /**todo falta definirlo*/
        return super.toString();
    }
}
