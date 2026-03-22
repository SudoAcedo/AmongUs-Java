package org.example;

public class Impostor extends Tripulante {

    public Impostor(String nombre) {
        super(nombre, "Impostor");
    }

    public void habilidadEspecial(){

        System.out.println("Tu habilidad especial es eliminar jugadores y sabotear salas");
    }

    public void sabotear(Sala sala) {

        sala.setSaboteada(true);
    }

    public void eliminar(Tripulante tripulante) {

        if (tripulante.getRol().equals("Impostor")) {
            System.out.println("No puedes eliminarte a ti mismo");
        } else {
            tripulante.setVivo(false);
        }
    }

    @Override
    public void realizarTarea(Tarea tarea){
        System.out.println("Estas simulando que haces una tarea: "+ tarea.getDescripcion());
    }
}
