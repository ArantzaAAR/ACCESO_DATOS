package dao;

import java.sql.*;
import java.util.ArrayList;

public class LibroDAOImp implements InterfazDAO {

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public boolean insertarDato(Object dato) throws SQLException {
        return false;
    }

    @Override
    public ArrayList obtenerListaDatos() {
        return null;
    }

    @Override
    public void actualizarDato(Object datoNuevo) throws SQLException {

    }

    @Override
    public int borrarDatos(int id) {
        return 0;
    }
}
