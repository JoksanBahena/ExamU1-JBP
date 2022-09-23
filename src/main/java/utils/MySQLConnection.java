package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static Connection getConnection(){
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/examen", "root", "root");        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try{
            Connection conexion = MySQLConnection.getConnection();
            if(conexion != null) {
                System.out.println("Conectado");
                conexion.close();
            }else {
                System.err.println("No Conectado");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
