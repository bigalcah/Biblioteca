package BBDD;
import java.sql.*;

class Consulta {

    public void registarCliente(String rut, String nombre, String direccion,
                                String telefono, String correoElectronico) {
        String sql = "INSERT INTO Cliente (rut, nombre, direccion, telefono, correo_electronico) " +
                "VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;
        SQL admin = new SQL();

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
            admin.manejoExcepciones(conn,e);
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public void ActualizarCliente(String rut, String nombre, String direccion, String telefono,
                                  String correoElectronico){
        SQL admin = new SQL();
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
        }catch (SQLException e){
            admin.manejoExcepciones(conn,e);
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }

    public void eliminarCliente(String rut){
        SQL admin = new SQL();
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
            admin.manejoExcepciones(conn,e);
        }finally {
            admin.cierreConexion(conn,statement);
        }
    }
    public void mostrarClientes(){
        SQL admin = new SQL();
        String sql = "SELECT * FROM Cliente";
        PreparedStatement statement = null;
        Connection conn = null;
        try{
            conn = admin.getConnection();
            statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String rut = rs.getString("rut");
                String nombre = rs.getString("nombre");
            }
        }catch (SQLException e){
            admin.manejoExcepciones(conn,e);
        }finally {
            admin.cierreConexion(conn, statement);
        }
    }

    public static void main(String[] args) {
        Consulta consulta = new Consulta();
        /*consulta.registarCliente("21345232-k", "javier prueba", "no tiene",
                "9898999", "javieralcaldevivas@gmail.com");*/
        consulta.ActualizarCliente("21345232-k", "holaaaaaaa", "no tiene",
                "9898999", "javieralcaldevivas@gmail.com");
    }
}
