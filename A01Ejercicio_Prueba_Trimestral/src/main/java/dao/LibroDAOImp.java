package dao;

import database.DBConnection;
import database.SchemeDB;
import model.Libro;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImp implements  InterfazDAO<Libro>, LibroDAO{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private Libro libro;

    public LibroDAOImp() {
        connection = DBConnection.getConnection();
    }

    @Override
    public boolean insertarDato(Libro dato) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUE (?,?,?,?)",
                SchemeDB.TAB_NAME,
                SchemeDB.COL_TITULO, SchemeDB.COL_AUTOR, SchemeDB.COL_ANO, SchemeDB.COL_ISBN, SchemeDB.ID_USUARIO);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, dato.getTitulo());
        preparedStatement.setString(2, dato.getAutor());
        preparedStatement.setInt(3, dato.getAno());
        preparedStatement.setString(4, dato.getIsbn());
        preparedStatement.setInt(5, dato.getIdUsuario());
        return preparedStatement.execute();
    }

    @Override
    public ArrayList<Libro> obtenerListaDatos() {
        ArrayList<Libro> listaLibro = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(("SELECT * FROM " + SchemeDB.TAB_NAME));
            resultSet =  preparedStatement.executeQuery();

            while (resultSet.next()){
                String titulo = resultSet.getString(SchemeDB.COL_TITULO);
                String autor = resultSet.getString(SchemeDB.COL_AUTOR);
                int ano = resultSet.getInt(SchemeDB.COL_ANO);
                String isbn = resultSet.getString(SchemeDB.COL_ISBN);
                int idPerfil = resultSet.getInt(SchemeDB.ID_USUARIO);

                listaLibro.add(new Libro(titulo, autor,ano,isbn, idPerfil));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLibro;
    }

    @Override
    public void actualizarDato(Libro datoNuevo) throws SQLException {
        String query = String.format("UPDATE INTO %s (%s,%s,%s,%s,%s WHERE (?,?,?,?)",
                SchemeDB.TAB_NAME,
                SchemeDB.COL_TITULO, SchemeDB.COL_AUTOR, SchemeDB.COL_ANO, SchemeDB.COL_ISBN, SchemeDB.ID_USUARIO);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, datoNuevo.getTitulo());
        preparedStatement.setString(2, datoNuevo.getAutor());
        preparedStatement.setInt(3, datoNuevo.getAno());
        preparedStatement.setString(4, datoNuevo.getIsbn());
        preparedStatement.setInt(5, datoNuevo.getIdUsuario());

        preparedStatement.executeUpdate();
    }

    @Override
    public int borrarDatos(int id) {

        try {
            String query = String.format("DELETE FROM %s WHERE %s=?", SchemeDB.TAB_NAME, SchemeDB.COL_ID);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la query");
        }
        return -1;
    }

}
