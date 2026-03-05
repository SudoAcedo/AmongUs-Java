package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TareaDAOImpl implements TareaDAO {

    private Connection connection;

    public TareaDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertar(Tarea tarea) {
        /**todo dejarlo igual que en la BBDD*/
        String sql = "INSERT INTO tarea (descripcion, completada, id_tripulante, id_sala) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, tarea.getDescripcion());
            ps.setBoolean(2, tarea.isCompletada());
            ps.setInt(3, tarea.getTripulanteAsignado().getId());
            ps.setInt(4, tarea.getSala().getId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Esto crea una tarea nueva
    public Tarea obtener(int id) {

        /**todo return*/
        return null;
    }

    public ArrayList<Tarea> obtenerTodos() {


        /**todo return*/
        return null;
    }
    public ArrayList<Tarea> obtenerPorTripulante(int idTrip){


        /**todo return*/
        return null;
    }
    public void actualizar(Tarea tarea){

    }
    public void eliminar(int id){

    }
}
