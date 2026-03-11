package org.example;

import java.util.ArrayList;

public interface SalaDAO {

    public void insertar(Sala sala);
    public Sala obtener(int id);
    public ArrayList<Sala> obtenerTodos();
    public void actualizar(Sala sala);
    public void eliminar(int id);

}

