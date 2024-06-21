package BBDD;
import org.postgresql.util.PSQLException;

import javax.swing.*;
import java.sql.*;

public class Consulta {
    public SQL admin;

    public Consulta(SQL admin) {
        this.admin = admin;
    }

    public void registrarCliente(String rut, String nombre, String direccion,
                                 String telefono, String correoElectronico) throws SQLException {
        String sql = "INSERT INTO Cliente (rut, nombre, direccion, telefono, correo_electronico) " +
                "VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            // manejo de concucurrencia
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            //transaccion
            conn.setAutoCommit(false);
            conn = admin.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, rut);
            statement.setString(2, nombre);
            statement.setString(3, direccion);
            statement.setString(4, telefono);
            statement.setString(5, correoElectronico);
            statement.execute();

            conn.commit();

        } catch (PSQLException e) {
            conn.rollback();
            JOptionPane.showMessageDialog(null, "Error al ejecutar la operación", "Error", JOptionPane.ERROR_MESSAGE);

            e.printStackTrace();
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public void ActualizarCliente(String rut, String nombre, String direccion, String telefono,
                                  String correoElectronico){
        String sql = "UPDATE Cliente SET nombre=?, direccion=?, telefono=?, correo_electronico=? " +
                "WHERE rut=?";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            conn = admin.getConnection();
            statement = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            statement.setString(1, nombre);
            statement.setString(2, direccion);
            statement.setString(3, telefono);
            statement.setString(4, correoElectronico);
            statement.setString(5, rut);

            statement.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public void eliminarCliente(String rut){
        String sql = "DELETE FROM Cliente WHERE rut=?";
        PreparedStatement statement = null;
        Connection conn = null;
        try {
            conn = admin.getConnection();
            statement = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement.setString(1, rut);
            statement.execute();
            conn.commit();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al ejecutar la operación", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public ResultSet mostrarClientes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Cliente";
        PreparedStatement statement = conn.prepareStatement(sql);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return statement.executeQuery();
    }

    public ResultSet mostrarAutores(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Autor";
        PreparedStatement statement = conn.prepareStatement(sql);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return statement.executeQuery();
    }

    public void registrarAutor(String nombre, String pais, Date fechaNacimiento) throws SQLException {
        String sql = "INSERT INTO Autor VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            conn = admin.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            statement = conn.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, pais);
            statement.setDate(3, fechaNacimiento);
            statement.execute();
            conn.commit();

        }catch (SQLException e){
            conn.rollback();
            JOptionPane.showMessageDialog(null, "Error al ejecutar la operacion", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public void eliminarAutor(String id) throws SQLException {
        String sql = "DELETE FROM Autor WHERE rut=?";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            conn = admin.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            statement.execute();
        }catch (SQLException e){
            conn.rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }



    public void modificarAutor(String nombre, String pais, Date fechaNacimiento) throws SQLException {
        String sql = "UPDATE Cliente SET nombre=?, pais=?, fecha_acimiento=? WHERE rut=?";
        Connection conn = null;
        PreparedStatement statement = null;
        try{
            conn = admin.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement = conn.prepareStatement(sql);
            statement.setString(1, nombre);
            statement.setString(2, pais);
            statement.setDate(3, fechaNacimiento);

            statement.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            conn.rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public ResultSet mostrarLibros(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Libro";
        PreparedStatement statement = null;
        conn = admin.getConnection();
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        return statement.executeQuery(sql);
    }

    public void registrarLibro(String titulo, String id_autor, Date fechaPublicacion, float precio, int stock) throws SQLException {
        String sql = "INSERT INTO Libro (titulo, id_autor, fecha_publicacion, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            conn = admin.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            statement = conn.prepareStatement(sql);
            statement.setString(1, titulo);
            statement.setString(2, id_autor);
            statement.setDate(3, fechaPublicacion);
            statement.setFloat(4, precio);
            statement.setInt(5, stock);

            statement.execute();
            conn.commit();
        }catch (SQLException e){
            conn.rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public void modificarLibro(int id, int stock) throws SQLException {
        String sql = "UPDATE Libro SET stock=? WHERE id=?";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            conn = admin.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement = conn.prepareStatement(sql);

            statement.setInt(1, stock);
            statement.setInt(2, id);
            statement.execute();
            conn.commit();

        }catch (SQLException e){
            conn.rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public void eliminarLibro(int id) throws SQLException {
        String sql = "DELETE FROM Libro WHERE id=?";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            conn = admin.getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
            conn.commit();
        }catch (SQLException e){
            conn.rollback();
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public static void main(String[] args) throws SQLException {
        Consulta consulta = new Consulta(new SQL());
        consulta.registrarCliente("21345232-k", "javier prueba", "no tiene",
                "9898999", "javieralcaldevivas@gmail.com");
        /*consulta.ActualizarCliente("21345232-k", "holaaaaaaa", "no tiene",
                "9898999", "javieralcaldevivas@gmail.com");*/
    }
}
