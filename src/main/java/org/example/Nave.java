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

        ArrayList<String> vivos = new ArrayList<>();
        ArrayList<Tripulante> tripulantesVivos = new ArrayList<>();
        ArrayList<String> muertos = new ArrayList<>();

        for (Tripulante tripulante : this.tripulantes) {
            if (tripulante.isVivo()) {
                vivos.add(tripulante.getNombre());
                tripulantesVivos.add(tripulante);
            } else {
                vivos.add(tripulante.getNombre());
            }
        }

        System.out.println("Tripulantes vivos: " + (vivos.toString()).replace("[", "").replace("]", ""));
        if (muertos.isEmpty()) {
            System.out.println("Tripulantes eliminados: Ninguno");
        } else {
            System.out.println("Tripulantes eliminados: " + (muertos.toString()).replace("[", "").replace("]", ""));
        }

        ArrayList<String> saboteadas = new ArrayList<>();
        ArrayList<Sala> salasSaboteadas = new ArrayList<>();

        for (Sala sala : this.salas) {
            if (sala.isSaboteada()) {
                saboteadas.add(sala.getNombre());
                salasSaboteadas.add(sala);
            }
        }
        if (saboteadas.isEmpty()) {
            System.out.println("Salas saboteadas: Ninguno");
        } else {
            System.out.println("Salas saboteadas: " + (saboteadas.toString()).replace("[", "").replace("]", ""));
        }


        int contadorCompletadas = 0;
        ArrayList<Tarea> tripulanteTareas = new ArrayList<>();

        System.out.println("Tareas completadas: " + contadorCompletadas + "/" + this.tareas.size());
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
                System.out.print(zzz + ") " + " ".repeat(5) + tripulante.getNombre());
                tripulantesVotan.add(tripulante);
                votos.put(tripulante, 0);
            }
        }


        for (Tripulante tripulante : tripulantesVotan) {

            limpiarPantalla();
            System.out.println("Turno de votar de " + tripulante.getNombre());


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
                        tripulante.votar(tripulanteVotado);
                        votos.put(tripulanteVotado, votos.get(tripulanteVotado) + 1);

                    }
                    c = false;
                }
            }

        }

        int maxVotos = Collections.max(votos.values());
        String nombreTripulante = "Nadie";
        int idTripulante = 0;

        if (!(Collections.frequency(votos.values(), maxVotos) > 1) && maxVotos != 0) {
            for (Tripulante tripulante : votos.keySet()) {
                if (votos.get(tripulante) == maxVotos) {
                    idTripulante = tripulante.getId();
                    nombreTripulante = tripulante.getNombre();
                }
            }
        }

        System.out.println("¡Se expulsó a " + idTripulante + ") " + nombreTripulante + "!");

        for (Tripulante tripulante : this.tripulantes) {
            if (tripulante.getId() == idTripulante) {
                tripulante.setVivo(false);
            }
        }

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

    // Se requiere Tripulante tripulanteTurno para saber de quien es el turno.
    public void turno(Tripulante tripulanteTurno) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Turno de " + tripulanteTurno.getNombre());

        System.out.println("Pasale el ordenador a " + tripulanteTurno.getNombre() + " (Enter)");

        scanner.nextLine();

        System.out.println(                "Tu rol secreto: " + tripulanteTurno.getRol() + ".");

        System.out.println("=== Estado de la nave. ===");
        this.mostrarEstadoNave();


        ArrayList<String> vivos = new ArrayList<>();
        ArrayList<Tripulante> tripulantesVivos = new ArrayList<>();

        for (Tripulante tripulante : this.tripulantes) {
            if (tripulante.isVivo()) {
                vivos.add(tripulante.getNombre());
                tripulantesVivos.add(tripulante);
            } else {
                vivos.add(tripulante.getNombre());
            }
        }


        if (tripulanteTurno.getRol().equals("Impostor")) {

            System.out.println("¿Que quieres hacer?: ");
            System.out.println("   1) Simular tarea.");
            System.out.print("   2) Sabotear una sala: ");
            System.out.println("   3) Eliminar a un tripulante");
            System.out.println("   4) Convocar votacion");
            System.out.println("   5) Pasar turno");
            System.out.println("Elige una opción: ");

            boolean b = true;

            while (b) {
                String respuesta = scanner.nextLine().trim();

                Impostor impostor = new Impostor(tripulanteTurno.getNombre());

                switch (respuesta.charAt(0)) {

                    case '1':
                        System.out.println("Simulaste una tarea.");
                        break;

                    case '2':

                        ArrayList<Sala> salasNoSaboteadas = new ArrayList<>();
                        for (Sala sala : this.salas) {
                            if (sala.isSaboteada()) {
                                salasNoSaboteadas.add(sala);
                            }
                        }

                        System.out.println("¿Que sala quieres sabotear?");
                        for (int i = 1; i <= salasNoSaboteadas.size(); i++) {
                            System.out.println("    [" + i + "] " + salasNoSaboteadas.get(i - 1).getNombre());
                        }

                        boolean f = true;

                        while (f) {
                            String r4 = scanner.nextLine().trim();
                            int rI4 = 0;
                            try {
                                rI4 = Integer.parseInt(r4);
                            } catch (NumberFormatException e) {
                                System.out.println("Introduce una respuesta válida.");
                            }
                            if (rI4> vivos.size()) {
                                System.out.println("Introduce una respuesta válida.");
                            } else {
                                impostor.sabotear(salasNoSaboteadas.get(rI4-1));
                                f = false;
                            }
                        }
                        break;

                    case '3':
                        System.out.println("¿A quien quieres eliminar?");

                        tripulantesVivos.remove(tripulanteTurno);

                        for (int i = 1; i <= vivos.size(); i++) {
                            System.out.println("    [" + i + "] " + vivos.get(i - 1) + ".");
                        }

                        boolean c = true;

                        while (c) {
                            String r5 = scanner.nextLine().trim();
                            int rI5 = 0;
                            try {
                                rI5 = Integer.parseInt(r5);
                            } catch (NumberFormatException e) {
                                System.out.println("Introduce una respuesta válida.");
                            }
                            if (rI5 > vivos.size()) {
                                System.out.println("Introduce una respuesta válida.");
                            } else {
                                impostor.eliminar(tripulantesVivos.get(rI5 - 1));
                                c = false;
                            }
                        }
                        b = false;
                        break;
                    case '4':
                        iniciarVotacion();
                        b = false;
                        break;
                    case '5':
                        b = false;
                        break;
                    default:
                        System.out.println("Introduce una respuesta válida.");

                }
            }

        } else {

            ArrayList<Tarea> tripulanteTareas = new ArrayList<>();
            for (Tarea tarea : this.tareas) {
                if (tarea.getTripulanteAsignado().getId() == tripulanteTurno.getId()) {
                    tripulanteTareas.add(tarea);
                }
            }

            System.out.println("Tus tareas pendientes :");

            for (int i = 1; i <= tripulanteTareas.size(); i++) {
                System.out.println("    [" + i + "] " + tripulanteTareas.get(i - 1).getDescripcion() + " - " + tripulanteTareas.get(i - 1).getSala().getNombre());
            }

            System.out.println("¿Que quieres hacer?: ");
            System.out.println("   1) Realizar tarea.");
            System.out.print("   2) Usar habilidad especial: ");
            tripulanteTurno.habilidadEspecial();
            System.out.println("   3) Convocar votación de emergencia");
            System.out.println("   4) Pasar turno");
            System.out.println("Elige una opción: ");

            boolean b = true;

            while (b) {
                String respuesta = scanner.nextLine().trim();

                switch (respuesta.charAt(0)) {

                    case '1':
                        if (!tripulanteTareas.isEmpty()) {
                            System.out.println("¿Que tarea quieres realizar? ");
                            for (int i = 1; i <= tripulanteTareas.size(); i++) {
                                System.out.println("    [" + i + "] " + tripulanteTareas.get(i - 1).getDescripcion() + " - " + tripulanteTareas.get(i - 1).getSala().getNombre());
                            }
                            boolean a = true;
                            while (a) {
                                String r1 = scanner.nextLine().trim();
                                int rI1 = 0;
                                try {
                                    rI1 = Integer.parseInt(r1);
                                } catch (NumberFormatException e) {
                                    System.out.println("Introduce una respuesta válida.");
                                }

                                if (rI1 > tareas.size()) {
                                    System.out.println("Introduce una respuesta válida.");
                                } else {
                                    System.out.println("Tarea completada.");
                                    tripulanteTareas.get(rI1 - 1).setCompletada(true);
                                    a = false;
                                }
                            }
                            b = false;
                            break;
                        } else {
                            System.out.println("Todas tus tereas están completas");
                            break;
                        }
                    case '2':

                        String rol = tripulanteTurno.getRol();

                        switch (rol) {
                            case "Medico":
                                System.out.println("¿A quien quieres examinar?");

                                for (int i = 1; i <= vivos.size(); i++) {
                                    System.out.println("    [" + i + "] " + vivos.get(i- 1) + ".");
                                }

                                boolean c = true;

                                while (c) {
                                    String r2 = scanner.nextLine().trim();
                                    int rI2 = 0;
                                    try {
                                        rI2 = Integer.parseInt(r2);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Introduce una respuesta válida.");
                                    }
                                    if (rI2 > vivos.size()) {
                                        System.out.println("Introduce una respuesta válida.");
                                    } else {
                                        Medico medico = new Medico(tripulanteTurno.getNombre());
                                        medico.examinar(tripulantesVivos.get(rI2 - 1));
                                        c = false;
                                    }
                                }
                                break;
                            case "Capitan":
                                Capitan capitan = new Capitan(tripulanteTurno.getNombre());
                                capitan.convocarVotacion(this);
                                break;
                            case "Ingeniero":

                                ArrayList<Sala> salasSaboteadas = new ArrayList<>();
                                for (Sala sala : this.salas) {
                                    if (sala.isSaboteada()) {
                                        salasSaboteadas.add(sala);
                                    }
                                }

                                System.out.println("¿Que sala quieres reparar?");
                                for (int i = 1; i <= salasSaboteadas.size(); i++) {
                                    System.out.println("    [" + i + "] " + salasSaboteadas.get(i - 1).getNombre());
                                }

                                boolean d = true;

                                while (d) {
                                    String r3 = scanner.nextLine().trim();
                                    int rI3 = 0;
                                    try {
                                        rI3 = Integer.parseInt(r3);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Introduce una respuesta válida.");
                                    }
                                    if (rI3 > vivos.size()) {
                                        System.out.println("Introduce una respuesta válida.");
                                    } else {
                                        Ingeniero ingeniero = new Ingeniero(tripulanteTurno.getNombre());
                                        ingeniero.repararSala(salasSaboteadas.get(rI3 - 1));
                                        d = false;
                                    }
                                }
                                break;
                        }

                        b = false;
                        break;
                    case '3':
                        iniciarVotacion();
                        b = false;
                        break;
                    case '4':
                        b = false;
                        break;
                    default:
                        System.out.println("Introduce una respuesta válida.");

                }
            }
        }
    }
}
