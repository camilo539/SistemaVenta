package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ClienteDAO {
    ConexionMySQL cn = new ConexionMySQL();
    Connection con;
    PreparedStatement ps;
    
    public boolean RegistrarCliente (Cliente cl) {
        String sql = "INSERT INTO clientes (cedula, nombre, telefono, direccion) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getCedula());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return false;
    }
}
