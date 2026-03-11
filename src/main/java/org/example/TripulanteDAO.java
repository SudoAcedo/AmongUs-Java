package org.example;

import java.util.ArrayList;

public interface TripulanteDAO {

    public void insertar(Tripulante tripulante);
    public Tripulante obtener(int id);
    public ArrayList<Tripulante> obtenerTodos();
    public void actualizar(Tripulante tripulante);
    public void eliminar(int id);
}
