
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class ProveedorDAO {
    Connection con; // Variable de tipo Connection que se utilizará para establecer la conexión a la base de datos.
    ConexionMySQL cn = new ConexionMySQL(); // Se crea una instancia de la clase ConexionMySQL, que se utilizará para obtener la conexión a la base de datos.
    PreparedStatement ps; // Variable de tipo PreparedStatementque se utilizará para ejecutar consultas SQL.
    ResultSet rs; 
    
    public boolean RegistrarProveedor (Proveedor pr) {
        String sql = "INSERT INTO proveedor (cedula, nombre, telefono, direccion) VALUES (?,?,?,?)"; // La línea de código define una consulta SQL para insertar un nuevo registro en la tabla proveedor.
        try { // Manejo de excepciones.
            
            // Haciendo la conexion a la base de datos sobre el proveedor.
            
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getCedula());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.execute();
            
        } catch(SQLException e) {
            // Excepcion que imprime error en la consola si no es posible la conexion.
            System.out.println(e.toString());
            return false; // Retornando false si la conexion no es exitosa.
        } finally {
            // Codigo que se ejecutatara sin importar si es exitosa la conexion o no.
            try {
                con.close(); // Ciiere de conexion.
            } catch (SQLException e) {
                // Imprime el error en la consola si hay un error.
                System.out.println(e.toString());
            }
        }
        return true; // Retornando true si es exitosa la conexion.
    }
    
    public List ListarProveedor() { // Metodo publico para listar proveedores.
        List<Proveedor> ListaPr = new ArrayList(); // Permite almacenar objetos de forma dinamica.
        String sql = "SELECT * FROM proveedor"; // Selecciona la tabla proveedor.
        try { // Metodo para acceder a la base de datos.
            con = cn.getConnection(); // Conexion a la base de datos.
            ps = con.prepareStatement(sql); // Para hacer consultas SQL de forma eficiente y segura.
            rs = ps.executeQuery(); // Ejecuta la consulta SQL de la base de datos.
            while (rs.next()) { // Ciclo que itera todos los registros y retorna true o false si no los hay.
                Proveedor pr = new Proveedor(); // Instancia que crea un nuevo proveedor.
                // Se asigna el valor de la columna  del registro actual al atributo del objeto Proveedor.
                pr.setId(rs.getInt("id"));
                pr.setCedula(rs.getInt("cedula"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                ListaPr.add(pr); // Finalmente se agrega a la lista pr.
            }
        } catch (SQLException e) { // Excepcion por si la consulta sale mal y que imprima el error en la consola.
            System.out.println(e.toString());
        }
        return ListaPr; // Retorna la ListaPr.
    }
    
    public boolean EliminarProveedor(int id) { // Metodo booleano para eliminar proveedor.
        String sql = "DELETE FROM proveedor WHERE id = ?"; // Consulta que permite eliminar el proveedor de la tabla proveedor.
        try { // Bloque try.
            con = cn.getConnection(); // Obtiene una conexion a la base de datos.
            ps = con.prepareStatement(sql); // Prepara la consulta SQL.
            ps.setInt(1, id); // Establece el valor del marcador ID del proveedor.
            ps.execute(); // Ejecuta la consulta SQL.
            return true; // Retorna true.
        }catch(SQLException e) {
            System.out.println(e.toString()); // Imprime el error en la consola.
            return false; // Retorna falso.
        } finally { // Asegura que se cierre la base de datos independientemente que ocurra el try o el catch.
            try {
                con.close(); // Intenta cerrar la conexion.
            } catch (SQLException e) { // 
                System.out.println(e.toString()); // Imprime el error en la consola.
            }
        }
    }
    
    public boolean ModificarProveedor(Proveedor pr){ // Metodo booleano para modificar proveedor
        String sql = "UPDATE proveedor SET cedula=?, nombre=?, telefono=?, direccion=? WHERE id=?"; // Consulta que permite modificar la tabla del proveedor.
        try { // Bloque try catch
            con = cn.getConnection(); // Obtiene una conexion a la base de datos.
            ps = con.prepareStatement(sql); // Prepara la consulta SQL.
            
            // Insertando los datos para la consulta SQL.
            
            ps.setInt(1, pr.getCedula()); 
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setInt(5, pr.getId());
            ps.execute();
            return true; // Retorna true.
        } catch(SQLException e) {
            System.out.println(e.toString()); // Imprime el error.
            return false; // Retorna falso.
        } finally { // Metodo final
            try {
                // Cierre de conexion
                con.close();
            }catch(SQLException e) {
                System.out.println(e.toString()); // Imprime el error en la consola.
            }
        }
    }
}
