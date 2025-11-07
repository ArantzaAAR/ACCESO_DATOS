package database;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
        //ESTE TIPO DE DECLARACIÓN EN EL PATRON SINGLETON DEJA LOS DATOS DESPROTEGIDOS
        // AL RESTAR COMO TEXTO PLANO. HACEMOS UN FICHERO PROPERTIES PARA GUARDAR DATOS MÁS SEGURA

        //necesito 3 parámetros
        String user = "root";
        String pass = "";
        //Estructura de la url
        // tipo de protocolo(jdbc); a traves de protocolo mysql; el servidor(localhost = 127.0.0.1);puerto(41063); a qué BBDD me conecto(test)
        String url = "jdbc:mysql://127.0.0.1:41063/peticiones";

        Properties properties = new Properties();//creo una instancia para acceder al fichero properties donde está los datos de conexión
        try {
            //indico la ruta del fichero. Da un posible error "el fichero n o existe"
            FileInputStream fileInputStream = new FileInputStream("D:\\Documentos\\UNIR_DAM2\\Acceso_Datos\\Ejercicios_clase\\Clase28-11_PeticionesAPP\\PeticionesAPP\\src\\main\\resources\\database.properties");

            properties.load(fileInputStream);//tomo los datos de conexión desde las properties. A erro de permisos de acceso al fichero

            //indico los datos que quiero que tome para la conexión
            user = properties.getProperty("user");
            pass= properties.getProperty("pass");
            url = properties.getProperty("url");

            //realizo la conexión. Da un posible error en la ejecución de SQL
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

