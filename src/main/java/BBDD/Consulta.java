package BBDD;
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
            conn = admin.getConnection();
            statement = conn.prepareStatement(sql);
            // manejo de concucurrencia
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            //transaccion
            conn.setAutoCommit(false);

            statement.setString(1, rut);
            statement.setString(2, nombre);
            statement.setString(3, direccion);
            statement.setString(4, telefono);
            statement.setString(5, correoElectronico);
            statement.execute();

            conn.commit();

        } catch (SQLException e) {
            conn.rollback();

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
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

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
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            statement.setString(1, rut);
            statement.execute();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public ResultSet mostrarClientes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Cliente";
        PreparedStatement statement = conn.prepareStatement(sql);
        return statement.executeQuery();
    }

    public void registrarLibro(String titulo, String idAutor, Date fecha, float precio){


    }

    public static void main(String[] args) throws SQLException {
        Consulta consulta = new Consulta(new SQL());
        consulta.registrarCliente("21345232-k", "javier prueba", "no tiene",
                "9898999", "javieralcaldevivas@gmail.com");
        /*consulta.ActualizarCliente("21345232-k", "holaaaaaaa", "no tiene",
                "9898999", "javieralcaldevivas@gmail.com");*/
    }
}
