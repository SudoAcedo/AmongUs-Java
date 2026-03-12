package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Tripulantes vivos que votan:");
        ArrayList<Tripulante> tripulantesVotan = new ArrayList<>();

        HashMap<Tripulante, Integer> votos = new HashMap<>();
        int zzz = 0;

        for (Tripulante tripulante : this.tripulantes) {
            if (tripulante.isVivo()) {
                zzz++;
                System.out.print(zzz + ") " + tripulante.getNombre() + " ".repeat(5));
                tripulantesVotan.add(tripulante);
                votos.put(tripulante, 0);
            }
        }


        for (Tripulante tripulante : tripulantesVotan) {

            limpiarPantalla();
            System.out.println("Turno de " + tripulante.getNombre());


            System.out.println("?A quien votas¿ (1-" + tripulantesVotan.size() + ", 0 para skip)");
            boolean c = true;
            while (c) {

                String resp = scanner.nextLine().trim();
                int indiceTripulante = 0;
                if (resp.isEmpty()) {
                    continue;
                } else {
                    try {
                        indiceTripulante = indiceTripulante + Integer.parseInt(resp);
                    } catch (NumberFormatException e) {
                        System.out.println("Introduce un número.");
                        continue;
                    }
                }

                if (indiceTripulante < 0 || indiceTripulante > tripulantesVotan.size()) {
                    System.out.println("Introduce una respuesta válida.");
                } else {
                    if (indiceTripulante == 0) {
                        System.out.println("Saltando votación...");
                    } else {

                        Tripulante tripulanteVotado = tripulantesVotan.get(indiceTripulante - 1);

                        System.out.println(tripulante.getNombre() + " vota a " + tripulanteVotado.getNombre());

                        /** */
                        tripulante.votar(tripulanteVotado);
                        /** */
                        /* TODO(Pablo): (id_TODO_1) no se si va en votar()
                        votos.put(tripulanteVotado, votos.get(tripulanteVotado) + 1);
                        */
                    }
                    c = false;
                }
            }

        }

        /* TODO(Pablo): (id_TODO_2) no se si va en votar() con lo anterior todo(id_TODO_1)
        int maxVotos = Collections.max(votos.values());
         String nombreTripulante = "nadie";
        int idTripulante = 0;

        if (!(Collections.frequency(votos.values(), maxVotos) > 1) && maxVotos != 0) {
            for (Tripulante tripulante : votos.keySet()) {
                if (votos.get(tripulante) == maxVotos) {
                    idTripulante = tripulante.getId();
                    nombreTripulante = tripulante.getNombre();
                }
            }
        }
        */

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

        int contadorTripulantes = 0;
        int contadorImpostores = 0;

        for (Tripulante tripulante : this.tripulantes) {
            if (tripulante.getRol().equals("Impostor")) {
                if (tripulante.isVivo()) {
                    contadorImpostores++;
                }
            } else {
                if (tripulante.isVivo()) {
                    contadorTripulantes++;
                }
            }
        }

        return (contadorImpostores >= contadorTripulantes);
    }

    public void turno() {
        /* TODO(Javi): (id_TODO_3) hacer esto al final */
    }
}
