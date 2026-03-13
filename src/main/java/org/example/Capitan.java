package org.example;

public class Capitan extends Tripulante {

public Capitan(String nombre){
    super(nombre, "Capitan");
}

public void habilidadEspecial(){
    System.out.println("Tu habilidad especial es el poder de convocar las votaciones");
}

public void convocarVotacion(Nave nave) {
    nave.iniciarVotacion();
}
}
