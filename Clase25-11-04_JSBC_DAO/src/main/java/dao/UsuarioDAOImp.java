package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Usuario;

import javax.xml.validation.Schema;
import java.sql.*;
import java.util.ArrayList;

//DEFINIMOS LA LÓGICA CONTRA LA BBDD

public class UsuarioDAOImp implements InterfazDAO<Usuario>, UsuarioDAO {

    //HAGO LA CONEXIÓN CONTRA LA BBDD y abro la conexión
    private Connection connection;
    //para poder insertar un usuario necesito un objeto PreparedStatement
    private PreparedStatement preparedStatement;
    //para obtenerListaDatos necesito PreparedStatement y ResultSet
    private ResultSet resultSet;

    //abro la conexión en el constructor
    public UsuarioDAOImp() {
        connection = DBConnection.getConnection();
    }



    //IMPLEMENTO MÉTODOS INTERFAZ GENÉRICA
    //INSERTAR DATO
    @Override
    public boolean insertarDato(Usuario dato) throws SQLException {//Meto la excepción aquí, porque solo trabaja con la BBDD. Mejor lo trato desde PeticionesController
        //que es dónde lo puedo tratar
        //creo la query para insertar datos
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
                SchemaDB.TAB_NAME,
                SchemaDB.COL_NAME, SchemaDB.COL_MAIL, SchemaDB.COL_PHONE, SchemaDB.COL_PROFILE
        );

        //hago la llamada
        preparedStatement = connection.prepareStatement(query);
        //parametrizo las ? con SET y pidiendo el dato con GET
        preparedStatement.setString(1, dato.getNombre());
        preparedStatement.setString(2, dato.getMail());
        preparedStatement.setInt(3, dato.getTelefono());
        preparedStatement.setInt(4, dato.getIdPerfil());

        //ejecuto con execute, porque quiero que retorne, me confirme si se ha insertado
        return preparedStatement.execute();

    }


    //OBTENER LISTA DATOS
    @Override
    public ArrayList<Usuario> obtenerListaDatos() {
        //Tengo que devolver un ArrayList
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        try {//hago la llamada
            preparedStatement = connection.prepareStatement("SELECT * FROM " + SchemaDB.TAB_NAME);
            //para ejecutar la query
            resultSet = preparedStatement.executeQuery();
            //se recorre el ArrayList con un while
            while (resultSet.next()) {
                int id = resultSet.getInt(SchemaDB.COL_ID);
                String nombre = resultSet.getString(SchemaDB.COL_NAME);
                String mail = resultSet.getString(SchemaDB.COL_MAIL);
                int telefono = resultSet.getInt(SchemaDB.COL_PHONE);
                int idPerfil = resultSet.getInt(SchemaDB.COL_PROFILE);

                //construyo un usuario con el constructor que yo he creado y lo añado a la lista de usuario
                listaUsuarios.add(new Usuario(id, nombre, mail, telefono, idPerfil));
            }
            //cuando termine el bucle, qiue me devuelva la lista de usuarios completa
            return listaUsuarios;
        } catch (SQLException e) {
            //error en la query
        }
        //si has llegado aquí, ha habido un error en la query y me devuelves null
        return listaUsuarios;
    }

    //ACTUALIZAR DATO
    @Override
    public void actualizarDato(Usuario datoNuevo) throws SQLException {
        String query = String.format("UPDATE INTO %s (%s,%s,%d,%d) VALUES (?,?,?,?)",
                SchemaDB.TAB_NAME,
                SchemaDB.COL_NAME, SchemaDB.COL_MAIL, SchemaDB.COL_PHONE, SchemaDB.COL_PROFILE
        );

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, datoNuevo.getNombre());
        preparedStatement.setString(2, datoNuevo.getMail());
        preparedStatement.setInt(3, datoNuevo.getTelefono());
        preparedStatement.setInt(4, datoNuevo.getIdPerfil());

        preparedStatement.executeUpdate();
    }

    //BORRAR DATO
    @Override
    public int borrarDatos(int id) {//la eliminación se hace con el id
        try {
            String query = String.format("DELETE FROM %s WHERE %s=?", SchemaDB.TAB_NAME, SchemaDB.COL_ID);
            preparedStatement = connection.prepareStatement(query);//da error de sintaxis, por lo que lo trato aquí
            preparedStatement.setInt(1, id);//quiero que me devuelva un número concreto
            return preparedStatement.executeUpdate();//quiero que me devuelva un número concreto
        }catch (SQLException e){
            System.out.println("Error en la ejecución de la query");
        }
        return -1; //se pone la posición -1 es para que no seleccione o no lo que sea
    }

    //IMPLEMENTO MÉTODOS INTERFAZ USUARIODAO
    @Override
    public ArrayList<String> obtenerCorreos() {
        return null;
    }

    @Override
    public ArrayList<Usuario> obtenerPerfil(int idPerifl) {

        return null;
    }
}



