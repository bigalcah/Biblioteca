package BBDD;
import javax.swing.*;
import java.sql.*;

public class SQL {
    final private String DB_URL;
    final private String USER;
    final private String PASSWORD;

    public SQL(){
        this.DB_URL = "jdbc:postgresql://localhost:5432/biblioteca";
        this.USER = "javier";
        this.PASSWORD = "Javier2003.";
    }

    public Connection getConnection(){
        Connection conexion = null;
        try {
            //Cargar driver JDBC
            conexion = DriverManager.getConnection(this.DB_URL,this.USER,this.PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conexion;
    }

    public static void cierreConexion(Connection conn, PreparedStatement stmt){
        if(stmt != null){
            try {
                stmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }if(conn != null){
            try {
                conn.setAutoCommit(true);
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

}