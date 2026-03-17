package org.example;

public class Sala {

    private int id;
    private String nombre;
    private Boolean saboteada;

    private static int contadorIdsTarea = 1;

    public Sala(String nombre) {
        this.id = contadorIdsTarea;
        this.nombre = nombre;
        this.saboteada = false;
        contadorIdsTarea++;
    }

        public int getId() {
            return id;
        }

        public Boolean isSaboteada() {
            return saboteada;
        }

        public String getNombre() {
            return nombre;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSaboteada(Boolean saboteada) {
            this.saboteada = saboteada;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return "Sala{" +
                    "id=" + id +
                    ", nombre='" + nombre + '\'' +
                    ", saboteada=" + saboteada +
                    '}';
        }
}
