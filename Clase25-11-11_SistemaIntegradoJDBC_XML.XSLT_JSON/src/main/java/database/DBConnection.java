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
        if(connection==null){
            createConnection();
        }
        return connection;
    }

    private static void createConnection(){
        String user = "root";
        String pass = "";
        String url = "jdbc:mysql://127.0.0.1:41063/peticiones";

        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\Documentos\\UNIR_DAM2\\Acceso_Datos\\Ejercicios_clase\\Clase25-11-11_SistemaIntegradoJDBC_XML.XSLT_JSON\\src\\main\\resources\\database.properties.xml");

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
