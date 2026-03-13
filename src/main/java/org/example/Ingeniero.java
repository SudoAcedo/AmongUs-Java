package org.example;

public class Ingeniero extends Tripulante{

    public Ingeniero(String nombre) {
        super(nombre, "Ingeniero");
    }

    public void habilidadEspecial(){
        System.out.println("Tu habilidad especial es reparar salas");
    }

    public void repararSala(Sala sala){

        if (sala.isSaboteada()) {

            sala.setSaboteada(false);
        } else {
            System.out.println("No hay ninguna sala que necesite reparacion");
        }

    }
}
