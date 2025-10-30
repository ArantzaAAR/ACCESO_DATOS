import database.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //tenemos que crear una conexión con el patrón SINGLETON

        //creo una llamada al metod de DBConnection
        Connection connection = DBConnection.getConnection();
        try {
            //compruebo que la conexión funciona. getCatalog es la sentencia
            System.out.println(connection.getCatalog());
        } catch (SQLException e) {
            System.out.println("Error en la sentencia");
        }

        //creo otra conexión que es una réplica de la primera
        Connection connection1 = DBConnection.getConnection();
        try {
            System.out.println(connection1.getCatalog());
        } catch (SQLException e) {
            System.out.println("Error en la sentencia");
        }

    }
}
