package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=============================================");
        System.out.println("      AMONG US TERMINAL - NAVE ESPACIAL      ");
        System.out.println("=============================================");

        System.out.print("Cuántos tripulantes van a jugar? (4-10): ");

        ArrayList<String> tripulantesNombres = new ArrayList<>();
        ArrayList<Tripulante> tripulacion = new ArrayList<>();

        boolean b = true;
        while (b) {
            String numero = scanner.nextLine().trim();
            int numTrip = 0;
            if (numero.isEmpty()) {
                continue;
            } else {
                try {
                    numTrip = Integer.parseInt(numero);
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número.");
                    continue;
                }
            }
            if ( numTrip > 10 || numTrip < 4) {
                System.out.println("Introduce un número dentro del rango [4,10].");
            } else {
                for (int i = 0; i < numTrip; i++) {
                    System.out.println("Introduce el nombre del tripulante " + (i+1));
                    String jugador = scanner.nextLine();

                    while (true) {
                        if (jugador.equals("X")) {
                            System.out.println("Nombre no disponible. Prueba con otro.");
                        } else {
                            tripulantesNombres.add(jugador);
                            break;
                        }
                    }
                }
                b = false;
            }
        }


        System.out.println();


        System.out.println("¿Cuantos impostores? (Max 1 para 5 jugadores, max 2 para 6 - 8 jugadores, max 3 para 9 - 10 jugadores)");
        boolean c = true;

        int impostor1 = 0;
        Tripulante tripulante1 = null;
        int impostor2 = 0;
        Tripulante tripulante2 = null;
        int impostor3 = 0;
        Tripulante tripulante3 = null;

        while (c) {
            String numero = scanner.nextLine().trim();
            int numImpo = 0;
            if (numero.isEmpty()) {
                continue;
            } else {
                try {
                    numImpo = Integer.parseInt(numero);
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número.");
                    continue;
                }
            }

            if (tripulantesNombres.size() <= 5) {
                if (numImpo != 1) {
                    System.out.println("Número de impostores inválido para ese número de jugadores.");
                    continue;
                }
            }
            if (tripulantesNombres.size() <= 8) {
                if (numImpo != 1 && numImpo != 2) {
                    System.out.println("Número de impostores inválido para ese número de jugadores.");
                    continue;
                }
            }
            if (tripulantesNombres.size() <= 10) {
                if (numImpo != 1 && numImpo != 2 && numImpo != 3) {
                    System.out.println("Número de impostores inválido para ese número de jugadores.");
                    continue;
                }
            }
            if (numImpo == 1) {
                impostor1 = random.nextInt(tripulantesNombres.size());
                tripulante1 = new Impostor(tripulantesNombres.get(impostor1));

                tripulacion.add(tripulante1);

                tripulantesNombres.set(impostor1, "X");

                c = false;

            } else if (numImpo == 2) {
                impostor1 = random.nextInt(tripulantesNombres.size());
                tripulante1 = new Impostor(tripulantesNombres.get(impostor1));

                tripulacion.add(tripulante1);

                tripulantesNombres.set(impostor1, "X");

                do {
                    impostor2 = random.nextInt(tripulantesNombres.size());
                } while (impostor2 == impostor1);

                tripulante2 = new Impostor(tripulantesNombres.get(impostor2));

                tripulacion.add(tripulante2);

                tripulantesNombres.set(impostor2, "X");

                c = false;

            } else if (numImpo == 3) {
                impostor1 = random.nextInt(tripulantesNombres.size());
                tripulante1 = new Impostor(tripulantesNombres.get(impostor1));

                tripulacion.add(tripulante1);

                tripulantesNombres.set(impostor1, "X");

                do {
                    impostor2 = random.nextInt(tripulantesNombres.size());
                } while (impostor2 == impostor1);
                tripulante2 = new Impostor(tripulantesNombres.get(impostor2));

                tripulacion.add(tripulante2);

                tripulantesNombres.set(impostor2, "X");


                do {
                    impostor3 = random.nextInt(tripulantesNombres.size());
                } while (impostor3 == impostor1 || impostor3 == impostor2);
                tripulante3 = new Impostor(tripulantesNombres.get(impostor3));

                tripulacion.add(tripulante3);

                tripulantesNombres.set(impostor3, "X");

                c = false;

            }

        }

        Collections.shuffle(tripulantesNombres);

        int contador = 0;

        for (int i = 0; i < tripulantesNombres.size(); i++) {
            if (!tripulantesNombres.get(i).equals("X")) {

                Tripulante tripulante = null;

                if (contador == 0) {
                    tripulante = new Capitan(tripulantesNombres.get(i));
                    contador++;
                } else if (contador == 1) {
                    tripulante = new Medico(tripulantesNombres.get(i));
                    contador++;
                } else if (contador == 2) {
                    tripulante = new Ingeniero(tripulantesNombres.get(i));
                    contador++;
                } else {

                    int r = random.nextInt(3);

                    switch (r) {
                        case 0:
                            tripulante = new Capitan(tripulantesNombres.get(i));
                            break;
                        case 1:
                            tripulante = new Medico(tripulantesNombres.get(i));
                            break;
                        case 2:
                            tripulante = new Ingeniero(tripulantesNombres.get(i));
                            break;
                    }
                }

                tripulacion.add(tripulante);
                
                tripulantesNombres.set(i, "X");

            }
        }

        int turno = 0;

        ArrayList<Sala> salas = new ArrayList<>();
        ArrayList<Tarea> tareas = new ArrayList<>();

        HashSet<String> salasNombreHashSet = new HashSet<>();
        HashMap<Integer, String> salasNombreHashMap = new HashMap<>();
        ArrayList<String> tareasDesc = new ArrayList<>();

        int cont = 0;

        File tareaTXT = new File("src/main/java/org/example/tareas.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(tareaTXT))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String descripcion = linea;
                String nombreSala = br.readLine();

                salasNombreHashSet.add(nombreSala);
                salasNombreHashMap.put(cont, nombreSala);
                tareasDesc.add(descripcion);

                cont++;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        for (String nombreT : salasNombreHashSet) {
            Sala sala = new Sala(nombreT);
            salas.add(sala);
        }

        for (int i = 0; i < tareasDesc.size(); i++) {
            for (Sala sala : salas) {
                if (sala.getNombre().equals(salasNombreHashMap.get(i))) {
                    Tarea tarea = new Tarea(tareasDesc.get(i), null, sala);
                    tareas.add(tarea);
                }
            }
        }

        int contadorTarea = 0;
        for (int i = 0; i < tripulacion.size(); i++) {
            if (!tripulacion.get(i).getRol().equals("Impostor")) {

                tareas.get(contadorTarea).setTripulanteAsignado(tripulacion.get(i));
                contadorTarea++;

                tareas.get(contadorTarea).setTripulanteAsignado(tripulacion.get(i));
                contadorTarea++;
            }
        }


        Nave nave = new Nave(tripulacion, salas, tareas);

        while (!nave.verificarVictoriaImpostor() && !nave.verificarVictoriaTripulantes()) {
            int tripulanteTurno = turno%tripulacion.size();
            nave.turno(tripulacion.get(tripulanteTurno));
            turno++;
        }
        if (nave.verificarVictoriaImpostor()) {
            System.out.println("Ganaron los impostores");
            //todo mas texto
        } else if (nave.verificarVictoriaTripulantes()) {
            System.out.println("Ganaron los tripulantes");
            //todo mas texto
        }

    }
}
