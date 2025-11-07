package dao;

import database.DBConnection;
import database.SchemaDB;
import model.Usuario;

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
    @Override
    public boolean insertarDato(Usuario dato) throws SQLException {
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
}


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
            String nombre = resultSet.getString(SchemaDB.COL_NAME);
            String mail = resultSet.getString(SchemaDB.COL_MAIL);
            int telefono = resultSet.getInt(SchemaDB.COL_PHONE);
            int idPerfil = resultSet.getInt(SchemaDB.COL_PROFILE);

            //construyo un usuario con el constructor que yo he creado y lo añado a la lista de usuario
            Usuario usuario = new Usuario(nombre, mail, telefono, idPerfil);
            listaUsuarios.add(usuario);
        }
        //cuando termine el bucle, qiue me devuelva la lista de usuarios completa
        return listaUsuarios;
    } catch (SQLException e) {
        //error en la query
    }
    //si has llegado aquí, ha habido un error en la query y me devuelves null
    return null;
}


@Override
public void actualizarDato(Usuario datoNuevo) {
    String query = String.format("UPDATE INTO %s (%s,%s,%d,%d) VALUES (?,?,?,?",
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

@Override
public void borrarDato(int id) {

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



