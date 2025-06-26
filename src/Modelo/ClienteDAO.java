package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class ClienteDAO {
    ConexionMySQL cn = new ConexionMySQL(); // Se crea una instancia de la clase ConexionMySQL, que se utilizará para obtener la conexión a la base de datos.
    Connection con; // Variable de tipo Connection que se utilizará para establecer la conexión a la base de datos.
    PreparedStatement ps; // Variable de tipo PreparedStatementque se utilizará para ejecutar consultas SQL.
    ResultSet rs; // Variable que almacena los datos de la variable como si fuera una tabla.
    
    public boolean RegistrarCliente (Cliente cl) {
        String sql = "INSERT INTO clientes (cedula, nombre, telefono, direccion) VALUES (?,?,?,?)"; // Variable de tipo PreparedStatementque se utilizará para ejecutar consultas SQL.
        try { // Manejar algun tipo de excepcion al intentar la conexion con la base de datos.
            
            // Haciendo la conexion a la base de datos sobre el cliente.
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getCedula());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.execute();
        } catch (SQLException e) {
            // El metodo devuelve un error para indicar que el registro del cliente fallo.
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                // Haciendo un cierre a la conexion independientemente de si fallo o no fallo el registro.
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        // Finalmente, si el registro se realizó correctamente, el método devuelve true para indicar que el registro fue exitoso.
        return true;
    }
    
    public List ListarCliente () {
        List<Cliente> ListaCl = new ArrayList();
        String sql = "SELECT * FROM clientes";
        try {
            
            // Conexion para la base de datos clientes.
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                // Ciclo para recorrer la lista de clientes
                
                Cliente cl = new Cliente();
                cl.setId(rs.getInt("id"));
                cl.setCedula(rs.getInt("cedula"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getString("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                ListaCl.add(cl); // Agregando todos los datos a clientes
            }
        } catch (SQLException e) {
            System.out.println(e.toString()); // Imprimir un error si falla el registro.
        }
        return ListaCl; // Retornando la lista clientes
    }
    
    public boolean EliminarCliente(int id) { // Metodo booleano para eliminar cliente con el parametro id del cliente.
        String sql = "DELETE FROM clientes WHERE id = ?"; // Secuencia SQL para eliminar el id del cliente.
        try { // Manejo de excepciones.
            ps = con.prepareStatement(sql); // Objeto con para la consulta SQL.
            ps.setInt(1, id); // Se establece el valor del primer marcador de posición en la consulta SQL con el ID del cliente.
            ps.execute(); // Ejecuta la consulta SQL.
            return true; // Retornando true para indicar que la operacion es exitosa.
        } catch (SQLException e) {
            
            // Captura  el error y lo imprime en la consola.
            
            System.out.println(e.toString());
            return false; // Retornando false por si la consulta sale erronea.
        } finally { // Bloque de codigo que se ejecute asi sea que sea exitosa la consulta o no.
            try {
                con.close(); // Cierre de conexion.
            } catch(SQLException ex) {
                
                // Captura  el error y lo imprime en la consola.
                
                System.out.println(ex.toString());
            }
        }
    }
    
    public boolean ModificarCliente (Cliente cl) { // Metodo booleano con el parametro de los clientes.
        String sql = "UPDATE clientes SET cedula=?, nombre=?, telefono=?, direccion=? WHERE id=?"; // Consulta SQL de los datos sobre los clientes.
        try { // Manejo de excepciones
            ps = con.prepareStatement(sql); // Conexion con la base de datos.
            
            // Metodo para actualizar  los datos del cliente.
            
            ps.setInt(1, cl.getCedula());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setInt(5, cl.getId());
            ps.execute(); // Ejecutando la consulta SQL.
            return true; // Retornar true si la operacion es exitosa.
        } catch(SQLException e) {
            
            // Manejo de excepciones por si la conexion es erronea.
            
            System.out.println(e.toString());
            return false; // Retornar false cuando sea erronea la conexion.
        } finally { // Finally que se ejecuta sin importar si es true o false.
            try {
                
                // Cierre de la conexion
                
                con.close();
            } catch(SQLException ex) {
                // Excepcion que imprime el error en la consola.
                System.out.println(ex.toString());
            }
        }
    }
    
}
