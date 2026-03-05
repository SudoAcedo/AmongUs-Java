package org.example;

import java.sql.*;
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
                "WHERE ta.id = ?;";

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

        String sql = "SELECT *, ta.id AS id_tarea, tr.id AS id_tripulante, s.id AS id_sala, tr.nombre AS nombre_tripulante, s.nombre AS nombre_sala " +
                "FROM tarea AS ta INNER JOIN tripulante AS tr ON ta.id_tripulante = tr.id " +
                "INNER JOIN sala AS s ON ta.id_sala = s.id;";

        ArrayList<Tarea> tablaTarea = new ArrayList<>();

        try (Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Tripulante tripulante = new Tripulante("", "");
                Sala sala = new Sala("") ;
                Tarea tarea = new Tarea("", tripulante, sala);

                tarea.setId(resultSet.getInt("id_tarea"));
                tarea.setDescripcion(resultSet.getString("descripcion"));
                tarea.setCompletada(resultSet.getBoolean("completada"));

                tripulante.setId(resultSet.getInt("id_tripulante"));
                tripulante.setNombre(resultSet.getString("nombre_tripulante"));
                tripulante.setRol(resultSet.getString("rol"));
                tripulante.setVivo(resultSet.getBoolean("vivo"));

                sala.setId(resultSet.getInt("id_sala"));
                sala.setNombre(resultSet.getString("nombre_sala"));
                sala.setSaboteada(resultSet.getBoolean("saboteada"));


                tarea.setSala(sala);
                tarea.setTripulanteAsignado(tripulante);

                tablaTarea.add(tarea);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tablaTarea;
    }

    public ArrayList<Tarea> obtenerPorTripulante(int idTrip) {

        String sql = "SELECT *, ta.id AS id_tarea, tr.id AS id_tripulante, s.id AS id_sala, tr.nombre AS nombre_tripulante, s.nombre AS nombre_sala " +
                "FROM tarea AS ta INNER JOIN tripulante AS tr ON ta.id_tripulante = tr.id " +
                "INNER JOIN sala AS s ON ta.id_sala = s.id " +
                "WHERE tr.id = ?;";

        ArrayList<Tarea> tablaTarea = new ArrayList<>();

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, idTrip);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                Tripulante tripulante = new Tripulante("", "");
                Sala sala = new Sala("");
                Tarea tarea = new Tarea("", tripulante, sala);

                tarea.setId(resultSet.getInt("id_tarea"));
                tarea.setDescripcion(resultSet.getString("descripcion"));
                tarea.setCompletada(resultSet.getBoolean("completada"));

                tripulante.setId(resultSet.getInt("id_tripulante"));
                tripulante.setNombre(resultSet.getString("nombre_tripulante"));
                tripulante.setRol(resultSet.getString("rol"));
                tripulante.setVivo(resultSet.getBoolean("vivo"));

                sala.setId(resultSet.getInt("id_sala"));
                sala.setNombre(resultSet.getString("nombre_sala"));
                sala.setSaboteada(resultSet.getBoolean("saboteada"));


                tarea.setSala(sala);
                tarea.setTripulanteAsignado(tripulante);

                tablaTarea.add(tarea);
            }
        }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tablaTarea;
    }

    public void actualizar(Tarea tarea){
        String sql = "UPDATE tarea SET descripcion = ?, completada = ?, id_tripulante = ?, id_sala = ? WHERE id = ?;";

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, tarea.getDescripcion());
            ps.setBoolean(2, tarea.isCompletada());
            ps.setInt(3, tarea.getTripulanteAsignado().getId());
            ps.setInt(4, tarea.getSala().getId());
            ps.setInt(5, tarea.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminar(int id){

        String sql = " DELETE FROM tarea WHERE id = ?;";

        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
