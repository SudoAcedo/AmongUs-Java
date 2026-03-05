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
            ps.setInt(4, tarea.getSala().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Esto crea una tarea nueva
    public Tarea obtener(int id) {
        String sql = "SELECT ta.id, ta.descripcion, ta.completada, ta.id_tripulante, tr.nombre, tr.rol, tr.vivo, ta.id_sala, s.nombre AS nombre_sala, s.saboteada " +
                "FROM tarea AS ta INNER JOIN tripulante AS tr ON ta.id_tripulante = tr.id " +
                "INNER JOIN sala AS s ON ta.id_sala = s.id " +
                "WHERE ta.id = ?";

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    Tripulante tripulante = new Tripulante("", "");
                    Sala sala = new Sala("") ;
                    Tarea tarea = new Tarea("", tripulante, sala);

                    tarea.setId(resultSet.getInt("id"));
                    tarea.setDescripcion(resultSet.getString("descripcion"));
                    tarea.setCompletada(resultSet.getBoolean("completada"));

                    tripulante.setId(resultSet.getInt("id_tripulante"));
                    tripulante.setNombre(resultSet.getString("nombre"));
                    tripulante.setRol(resultSet.getString("rol"));
                    tripulante.setVivo(resultSet.getBoolean("vivo"));

                    sala.setId(resultSet.getInt("id_sala"));
                    sala.setNombre(resultSet.getString("nombre_sala"));
                    sala.setSaboteada(resultSet.getBoolean("saboteada"));


                    tarea.setSala(sala);
                    tarea.setTripulanteAsignado(tripulante);
                    return tarea;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
