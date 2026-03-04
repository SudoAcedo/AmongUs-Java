package org.example;

import java.util.ArrayList;

public class Nave {
    private ArrayList<Tripulante> tripulantes;
    private ArrayList<Sala> salas;
    private ArrayList<Tarea> tareas;

    public Nave(ArrayList<Tripulante> tripulantes, ArrayList<Sala> salas) {
        this.tripulantes = tripulantes;
        this.salas = salas;
    }

    public ArrayList<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public ArrayList<Sala> getSalas() {
        return salas;
    }

    public ArrayList<Tarea> getTareas() {
        return tareas;
    }

    public Tarea agregarTarea() {

        /**todo todo*/
        return null;
    }

    public void limpiarPantalla() {
        /** No funciona
        System.out.print("\033[H\033[2J");
        System.out.flush(); */

        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public void mostrarEstadoNave() {

    }

    public void iniciarVotacion() {

    }

    public boolean verificarVictoriaTripulantes() {

        /**todo todo*/
        return Boolean.parseBoolean(null);
    }

    public boolean verificarVictoriaImpostor() {

        /**todo todo*/
        return Boolean.parseBoolean(null);
    }

    public void turno() {

    }
}
