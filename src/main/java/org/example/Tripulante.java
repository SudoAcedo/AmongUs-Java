package org.example;

import java.sql.PreparedStatement;

public abstract class Tripulante {

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

    public boolean isVivo() {
        return vivo;
    }

    public void realizarTarea(Tarea tarea) {


    }

    public void votar(Tripulante tripulante) {
        /*
        haz "shift" + "shift" y "ctrl + c + v" de "TODO(Pablo):"
         */
    }

    public abstract void habilidadEspecial();

    @Override
    public String toString() {
        return super.toString();
    }
}
