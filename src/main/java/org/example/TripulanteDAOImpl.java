package org.example;

import java.sql.*;
import java.util.ArrayList;

public class TripulanteDAOImpl implements TripulanteDAO {

    private Connection conexion;

    public TripulanteDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }


    @Override
    public void insertar(Tripulante tripulante) {

        String sqlInsert = "INSERT INTO tripulante(nombre, rol, vivo) VALUES(?,?,?)";

        try(PreparedStatement pst = this.conexion.prepareStatement(sqlInsert)){
            pst.setString(1, tripulante.getNombre());
            pst.setString(2, tripulante.getRol() );
            pst.setBoolean(3, tripulante.isVivo() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Tripulante obtener(int id) {

        String sql = "SELECT id, nombre, rol, vivo FROM tripulante WHERE id = ?;";

        try(Connection connection = DBUtil.getInstance().getConexion(); PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setInt(1, id);


            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {

                    String rol = resultSet.getString("rol");
                    Tripulante tripulante = null;

                    switch (rol) {
                        case "Impostor":
                            tripulante = new Impostor("");
                            break;
                        case "Capitan":
                            tripulante = new Capitan("");
                            break;
                        case "Ingeniero":
                            tripulante = new Ingeniero("");
                            break;
                        case "Medico":
                            tripulante = new Medico("");
                            break;
                    }

                    tripulante.setId(resultSet.getInt("id"));
                    tripulante.setNombre(resultSet.getString("nombre"));
                    tripulante.setRol(resultSet.getString("rol"));
                    tripulante.setVivo(resultSet.getBoolean("vivo"));



                    return tripulante;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public ArrayList<Tripulante> obtenerTodos() {

        String sql = "SELECT id, nombre, rol, vivo FROM tripulante;";

        ArrayList<Tripulante> tablaTripulante = new ArrayList<>();

        try (Connection connection = DBUtil.getInstance().getConexion(); Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                String rol = resultSet.getString("rol");
                Tripulante tripulante = null;

                switch (rol) {
                    case "Impostor":
                        tripulante = new Impostor("");
                        break;
                    case "Capitan":
                        tripulante = new Capitan("");
                        break;
                    case "Ingeniero":
                        tripulante = new Ingeniero("");
                        break;
                    case "Medico":
                        tripulante = new Medico("");
                        break;
                }

                tripulante.setId(resultSet.getInt("id"));
                tripulante.setNombre(resultSet.getString("nombre"));
                tripulante.setRol(resultSet.getString("rol"));
                tripulante.setVivo(resultSet.getBoolean("vivo"));

                tablaTripulante.add(tripulante);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tablaTripulante;
    }

    @Override
    public void actualizar(Tripulante tripulante) {

        String sql = "UPDATE tripulante SET nombre = ?, rol = ?, vivo = ? WHERE id = ?;";

        try (Connection connection = DBUtil.getInstance().getConexion(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tripulante.getNombre());
            ps.setString(2, tripulante.getRol());
            ps.setBoolean(3, tripulante.isVivo());
            ps.setInt(4, tripulante.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = " DELETE FROM tripulante WHERE id = ?;";


        try (Connection connection = DBUtil.getInstance().getConexion(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
