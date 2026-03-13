package org.example;

public class Medico extends Tripulante {

    public Medico(String nombre) {
        super(nombre, "Medico");
    }

    public void habilidadEspecial(){
        System.out.println("Tu habilidad especial es examinar a los tripulantes");
    }

    public void examinar(Tripulante tripulante){

        if (tripulante.getRol() == "Impostor") {
            System.out.println("El tripulante: "+ tripulante.getNombre() +" ES el impostor");
        } else {
            System.out.println("El tripulante: "+ tripulante.getNombre()+ " NO es el impostor");
        }

    }
}
