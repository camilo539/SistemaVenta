
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Conexion del software y la base de datos

public class ConexionMySQL {
    Connection con;
    public Connection getConnection() {
        try {
            String myBD = "jdbc:mysql://localhost:3306/sistemaventa?serverTimezone=UTC";
            con = DriverManager.getConnection(myBD, "root", "");
            return con;
        } catch(SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }
}
