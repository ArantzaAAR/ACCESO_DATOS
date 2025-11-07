import database.DBConnection;
import database.SchemaDB;

import java.sql.*;

public class Main {
    public static void main(String[] args) { //tenemos que crear una conexión con el patrón SINGLETON

        Connection connection = DBConnection.getConnection();//creo una llamada al metod de DBConnection

            /* INSERCION DE DATOS EN TABLA
        try {
            Statement statement = connection.createStatement();//creo la conexión que da try/catch
            String nombre = "Nombre Insertar"; //creo las variables en donde me van a insertar los datos
            String mail = "Mail Insertar";
            int telefono = 123;
            int idPerfil = 1;

            //crea la query
            String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES ('%s','%s',%d,%d)", //%s - para string, lleva ''; %d para int; %f para double
                    SchemaDB.TAB_NAME, //son datos fijos, por eso los saco de los SCHEMA (de la interfaz)
                    SchemaDB.COL_NAME, SchemaDB.COL_MAIL, SchemaDB.COL_PHONE, SchemaDB.COL_PROFILE,
                    nombre, mail, telefono, idPerfil); //son las variables donde inserto los datos

             /Hago la inserción
             statement.execute(query);

            //esto es para comprobar si ha funcionado
            //boolean fallo =  statement.execute(query);
            //System.out.println("El resultado de la query es " + fallo);

        } catch (SQLException e) {
            System.out.println("Error de ejecución de query");
        }

        */

        /*
        //ACTUALIZACIÓN DE TABLA. TAMBIÉN SIRVE PARA BORRADO
        //LA EJECUCIÓN DE LA QUERY SE HACE DIRECTAMENTE AL MOTOR DE LA BBDD, POR EL PREPAREDSTAMENT, así solo se ocupa de la ejecución

        // Crear la consulta SQL dinámicamente usando String.format
        // Reemplaza los %s por los nombres de tabla y columnas definidos en SchemeDB
        //UPDATE usuarios SET id_perfil=3 where TELEFONO=123
        String query = String.format("UPDATE %s SET %s=? WHERE %s=?",
                SchemaDB.TAB_NAME,      // Nombre de la tabla a actualizar
                SchemaDB.COL_PROFILE,   // Columna que se va a modificar
                SchemaDB.COL_PHONE);    // Columna usada en la condición WHERE

        try {
            // Preparar la sentencia SQL para evitar inyecciones SQL. connection debe estar previamente definida y conectada a la BD
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Asignar valores a los parámetros (?) de la consulta
            // El primer ? se reemplaza por el valor 3 en la columna de perfil
            preparedStatement.setInt(1, 3);  // Parámetro 1 = 3 (nuevo valor para COL_PROFILE)

            // El segundo ? se reemplaza por el valor 123 en la condición WHERE
            preparedStatement.setInt(2, 234); // Parámetro 2 = 123 (valor para buscar en COL_PHONE)

            // Ejecutar la actualización y obtener el número de filas afectadas
            int numRow = preparedStatement.executeUpdate();

            // Verificar si se actualizó al menos una fila
            if (numRow > 0) {
                System.out.println("Has actualizado los registros de la base de datos");
                System.out.println("En concreto se han visto afectadas " + numRow + " filas");
            }
            //meto esta excepción a mano para evitar que haya una clave duplicada
        }catch (SQLIntegrityConstrainViolationException e) {
            System.out.println("Error, calve duplicada");
        } catch (SQLException e) {
            // Manejar posibles errores de base de datos
            System.out.println("Error en la sentencia query");
        }
        */
        /*
        //PARA OBTENCION DE DATOS - SELECT
        //RESULTSET ->se basa en un statement o preparedStatement

        String query = String.format("SELECT * FROM %s", SchemaDB.TAB_NAME);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //Necesito un conjunto de querys (executeQuery) y lo guardo en resulSet
            ResultSet resultSet = preparedStatement.executeQuery();

            //resulSet es un conjunto de resultados, por lo que tengo que ir de uno en uno obteniendo el resultado
            //tengo que si hay alguien a mi lado, me muevo y pregunto, hats que no haya nadie
            while(resultSet.next()){
                String nombre = resultSet.getString("nombre");
                String telefono = resultSet.getString("apellidos");
                System.out.println("Nombre: %s, Telefono: %d%n", nombre, telefono);
            }
            System.out.println("Datos obtenidos correctamente");
        } catch (SQLException e) {

            System.out.println("Error en la ejecución de la query");
        }
        */


    }
}
