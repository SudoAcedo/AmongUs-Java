package org.example;
import java.awt.image.DataBufferUShort;
import java.sql.*;

public class DBUtil {

    private static DBUtil instancia;

    private Connection conexion;

    private DBUtil() {

        String DB_URL = "jdbc:mysql://localhost:3306/nave_espacial";
        String USER = "root";
        String PASS = "";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (Statement st = conn.createStatement()){

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            this.conexion = conn;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBUtil getInstance(){
        if (instancia == null) {
            instancia = new DBUtil();
        }
        return instancia;
    }

    public Connection getConexion() {
        return this.conexion;
    }


}



