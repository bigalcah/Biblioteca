package BBDD;
import java.sql.*;

class Consulta {

    public void registarCliente(String rut, String nombre, String direccion,
                                String telefono, String correoElectronico) {
        String sql = "INSERT INTO Cliente (rut, nombre, direccion, telefono, correoElectronico) " +
                "VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = new SQL().getConnection();
            statement = conn.prepareStatement(sql);
            conn.setAutoCommit(false);

            statement.setString(1, rut);
            statement.setString(2, nombre);
            statement.setString(3, direccion);
            statement.setString(4, telefono);
            statement.setString(5, correoElectronico);
            statement.execute();

            conn.commit();

        }catch (SQLException e){
            //(if )
        }

    }
}
