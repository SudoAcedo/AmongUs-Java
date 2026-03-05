package org.example;

import java.util.ArrayList;

public interface TareaDAO {

    public void insertar(Tarea tarea);
    public Tarea obtener(int id);
    public ArrayList<Tarea> obtenerTodos();
    public ArrayList<Tarea> obtenerPorTripulante(int idTrip);
    public void actualizar(Tarea tarea);
    public void eliminar(int id);
}
