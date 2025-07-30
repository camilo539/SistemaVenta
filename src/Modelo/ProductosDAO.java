
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JComboBox;

public class ProductosDAO {
    Connection con; // Variable de tipo Connection que se utilizará para establecer la conexión a la base de datos.
    ConexionMySQL cn = new ConexionMySQL(); // Se crea una instancia de la clase ConexionMySQL, que se utilizará para obtener la conexión a la base de datos.
    PreparedStatement ps; // Variable de tipo PreparedStatementque se utilizará para ejecutar consultas SQL.
    ResultSet rs;
    
    public boolean RegistrarProductos(Productos pro) {
        String sql = "INSERT INTO productos (codigo, nombre, proveedor, stock, precio) VALUES (?,?,?,?,?)";
        try {
            
            // Haciendo la conexion a la base de datos.
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getProveedor());
            ps.setInt(4, pro.getStock());
            ps.setDouble(5, pro.getPrecio());
            ps.execute(); // Ejecutando la consulta SQL.
            return true; // Retornando verdadero
        } catch (SQLException e) {
            // Excepcion de errores.
            System.out.println();
            return false;
        }
    }
    
    public void ConsultarProveedor(JComboBox proveedor) {
        String sql = "SELECT nombre FROM proveedor";
        try {
            // Conexion a la base de datos.
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // Ciclo para recorrer los proveedores.
            while (rs.next()) {
                proveedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            // Excepcion por si sale mal.
            System.out.println(e.toString());
        }
    }
}
