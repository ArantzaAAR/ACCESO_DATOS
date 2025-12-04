package database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection connection;
    public static Connection getConnection(){
        //el valor inicial es null, por lo que tengo que inicializarlo
        if(connection==null){
            //inicialízala
            createConnection();
        }
        return connection;
    }

private static void createConnection(){

    String user = "root";
    String pass = "";
    String url = "jdbc:mysql://127.0.0.1:41063/entregas";

    Properties properties = new Properties();
    try {
        FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\database.properties");
        properties.load(fileInputStream);

        user = properties.getProperty("user");
        pass= properties.getProperty("pass");
        url = properties.getProperty("url");

        connection = DriverManager.getConnection(url, user, pass);

    } catch (FileNotFoundException e) {
        System.out.println("El fichero indicado no existe");
    } catch (IOException e) {
        System.out.println("No tienes permisos para acceder al fichero");
    } catch (SQLException e) {
        System.out.println("Error en la ejecución SQL");
    }
}
}
