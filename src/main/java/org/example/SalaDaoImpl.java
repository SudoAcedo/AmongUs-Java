package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaDaoImpl implements SalaDAO {

    @Override
    public void insertar(Sala sala) {
        String sql = "INSERT INTO sala (nombre, saboteada) VALUES (?, ?)";

        Connection conn = DBUtil.getInstance().getConexion();

        try (PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, sala.getNombre());
            pst.setBoolean(2, sala.isSaboteada());
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        @Override
        public Sala obtener(int id) {
            String sql = "SELECT * FROM sala WHERE id = ?";
            Sala sala = null;

            Connection conn = DBUtil.getInstance().getConexion();

            try (PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, id);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        sala = new Sala(rs.getString("nombre"));
                        sala.setId(rs.getInt("id"));
                        sala.setSaboteada(rs.getBoolean("saboteada"));
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return sala;
        }

        @Override
        public ArrayList<Sala> obtenerTodos() {
            String sql = "SELECT * FROM sala";
            ArrayList<Sala> salas = new ArrayList<>();

            Connection conn = DBUtil.getInstance().getConexion();

            try (PreparedStatement pst = conn.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    Sala sala = new Sala(rs.getString("nombre"));
                    sala.setId(rs.getInt("id"));
                    sala.setSaboteada(rs.getBoolean("saboteada"));
                    salas.add(sala);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return salas;
        }

        @Override
        public void actualizar(Sala sala) {
            String sql = "UPDATE sala SET nombre = ?, saboteada = ? WHERE id = ?";

            Connection conn = DBUtil.getInstance().getConexion();

            try (PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, sala.getNombre());
                pst.setBoolean(2, sala.isSaboteada());
                pst.setInt(3, sala.getId());
                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void eliminar(int id) {
            String sql = "DELETE FROM sala WHERE id = ?";

            Connection conn = DBUtil.getInstance().getConexion();

            try (PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setInt(1, id);
                pst.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
