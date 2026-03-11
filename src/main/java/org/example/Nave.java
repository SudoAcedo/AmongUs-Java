package org.example;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void agregarTarea(Tarea tarea) {
        TareaDAOImpl tareaDAO = new TareaDAOImpl();

        tareaDAO.insertar(tarea);
    }

    public void limpiarPantalla() {
        /** No funciona
        System.out.print("\033[H\033[2J");
        System.out.flush(); */

        for (int i = 0; i < 150; i++) {
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
