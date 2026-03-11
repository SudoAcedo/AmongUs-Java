package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TripulanteDAOImpl implements TripulanteDAO {

    private Connection conexion;

    public TripulanteDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }


    @Override
    public void insertar(Tripulante tripulante) {

        String sqlInsert = "INSERT INTO Tripulante(id, nombre, rol, vivo) VALUES(?,?,?,?)";

        try(PreparedStatement pst = this.conexion.prepareStatement(sqlInsert)){
            pst.setInt(1,tripulante.getId());
            pst.setString(2, tripulante.getNombre());
            pst.setString(3, tripulante.getRol() );
            pst.setBoolean(4, tripulante.isVivo() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Tripulante obtener(int id) {
        return null;
    }

    @Override
    public ArrayList<Tripulante> obtenerTodos() {
        return null;
    }

    @Override
    public void actualizar(Tripulante tripulante) {

    }

    @Override
    public void eliminar(int id) {

    }
}
