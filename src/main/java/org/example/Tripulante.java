package org.example;

import java.sql.PreparedStatement;

public abstract class Tripulante implements Votable, Trabajable{

    private int id;
    private String nombre;
    private String rol;
    private boolean vivo;

    public Tripulante(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
        this.vivo = true;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void realizarTarea(Tarea tarea) {

        if (vivo == true && tarea.getTripulanteAsignado().getId() == this.id) {

            tarea.setCompletada(true);
        } else {
            System.out.println("Esta tarea ha sido asignada a otro tripulante");
        }


    }

    public void votar(Tripulante sospechoso) {

        if (this.isVivo() && sospechoso.isVivo()) {
            System.out.println(this.nombre + " vota a " + sospechoso.getNombre() + ".");
        } else {
            System.out.println("El voto no es valido");
        }

    }

    public abstract void habilidadEspecial();

    @Override
    public String toString() {
        return super.toString();
    }
}
