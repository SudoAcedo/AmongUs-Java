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

        ArrayList<Tripulante> tripulantesVivos = new ArrayList<>();
        ArrayList<Tripulante> tripulantesMueltos = new ArrayList<>();

        ArrayList<Tarea> TareasCompletadas = new ArrayList<>();

        ArrayList<Salas> salasSaboteadas = new ArrayList<>();

        for (Tripulante tripulante : this.tripulantes) {
            if (tripulante.isVivo()) {
                tripulantesVivos.add(tripulante);
            } else {
                tripulantesMueltos.add(tripulante);
            }
        }

        System.out.print("Tripulantes vivos: ");
        for (Tripulante tripulante : tripulantesVivos) {
            int i = 1;
            i++;
            if (i < tripulantesVivos.size()) {
            System.out.print(tripulante + ", ");
            } else {
                System.out.println(tripulante + ".");
            }
        }

        System.out.print("Tripulantes eliminados: ");
        if (tripulantesMueltos.isEmpty()) {
            System.out.println("ninguno.");
        } else {
            for (Tripulante tripulante : tripulantesMueltos) {
                int i = 1;
                i++;
                if (i < tripulantesMueltos.size()) {
                    System.out.print(tripulante + ", ");
                } else {
                    System.out.println(tripulante + ".");
                }
            }
        }

        System.out.print("Salas saboteadas: ");
        for (Sala sala : this.salas) {
            if (sala.isSaboteada()) {
                System.out.print(sala.getNombre() + " ");
            }
        }

        for (Tarea tarea : this.tareas) {
            if (tarea.isCompletada()) {
                TareasCompletadas.add(tarea);
            }
        }
        System.out.print("Tareas completas: ");
        int contador = 0;
        for (Tarea tarea : this.tareas) {
            if (tarea.isCompletada()) {
                contador++;
            }
        }
        System.out.println(contador + "/" + this.tareas.size());
    }

    public void iniciarVotacion() {

    }

    public boolean verificarVictoriaTripulantes() {

        boolean todosImpostoresEliminados = false;
        boolean todasTareasCompletas = false;

        int contadorDeImpostores = 0;
        int impostoresEliminados = 0;

        for (Tripulante tripulante : this.tripulantes) {
            if (tripulante.getRol().equals("Impostor")) {
                contadorDeImpostores++;
                if (!tripulante.isVivo()) {
                    impostoresEliminados++;
                }
            }
        }
        if (contadorDeImpostores == impostoresEliminados) {
            todosImpostoresEliminados = true;
        }

        int contadorTareas = 0;

        for (Tarea tarea : this.tareas) {
            if (tarea.isCompletada()) {
                contadorTareas++;
            }
        }

        if (contadorTareas == this.tareas.size()) {
            todasTareasCompletas = true;
        }

        return (todosImpostoresEliminados || todasTareasCompletas);
    }

    public boolean verificarVictoriaImpostor() {

        /**todo todo*/
        return Boolean.parseBoolean(null);
    }

    public void turno() {

    }
}
