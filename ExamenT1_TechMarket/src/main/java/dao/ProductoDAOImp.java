package dao;

import database.DBConnection;
import database.SchemeDB;
import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImp implements InterfazDAO<Producto>, ProductoDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private Producto productos;

    public ProductoDAOImp() {
        connection = DBConnection.getConnection();
    }


    @Override
    public boolean insertarDato(Producto dato) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUE (?,?,?)",
                SchemeDB.TAB_NAME,
                SchemeDB.COL_NAME, SchemeDB.COL_PRICE, SchemeDB.COL_CATEGORY
        );

       preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, dato.getNombre());
        preparedStatement.setDouble(2, dato.getPrecio());
        preparedStatement.setString(3, dato.getCategoria());
        return preparedStatement.execute();
    }

    @Override
    public int borrarDatos(int id) {
        try {
            String query = String.format("DELETE FROM %s WHERE %s=?", SchemeDB.TAB_NAME, SchemeDB.COL_ID);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error en la ejecución de la query");
        }
        return -1;
    }

    @Override
    public ArrayList<Producto> obtenerListaDatos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + SchemeDB.TAB_NAME);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(SchemeDB.COL_ID);
                String nombre = resultSet.getString(SchemeDB.COL_NAME);
                double precio = resultSet.getDouble(SchemeDB.COL_PRICE);
                String categoria = resultSet.getString(SchemeDB.COL_CATEGORY);

                listaProductos.add(new Producto(id, nombre, precio, categoria));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProductos;
    }

    @Override
    public void actualizarDato(Producto datoNuevo) throws SQLException {

    }

    @Override
    public void actualizarPrecioPerifericos() throws SQLException {

    }
}
