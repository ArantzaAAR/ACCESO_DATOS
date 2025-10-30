
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    //AQUÍ TENGO LA CONEXION que servirá para replicar
    //tiene que ser STATIC (pertenece a la clase y no al objeto) y PRIVATE para el encapsulamiento
    private static Connection connection;

    //para acceder a la conexión necesitas un metodo público
    public static Connection getConnection(){
        //el valor inicial es null, por lo que tengo que inicializarlo
        if(connection==null){
            //inicialízala
            createConnection();
        }
        return connection;
    }

    //creo el métod encapsulado, static, porque llama a un metod static, void porque no devuelve nada crea la conexion
    private static void createConnection(){
        //necesito 3 parámetros
        String user = "root";
        String pass = "";
        //Estructura de la url
        // tipo de protocolo(jdbc); a traves de protocolo mysql; el servidor(localhost = 127.0.0.1);puerto(41063); a qué BBDD me conecto(test)
        String url = "jdbc:mysql://127.0.0.1:41063/peticiones";
        try {
            //creo la conexion: Driver>MAnager hace que se entienda la url. En getConnection especifico los datos. Da excepción
            connection = DriverManager.getConnection(url, user,pass);
        } catch (SQLException e) {
            System.out.println("Error en conexión SQL");
        }

    }
}
