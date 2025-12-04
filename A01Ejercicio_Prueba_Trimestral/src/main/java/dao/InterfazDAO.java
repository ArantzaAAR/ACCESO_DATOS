package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfazDAO<T> {
    boolean insertarDato(T dato) throws SQLException;
    ArrayList<T> obtenerListaDatos();
    void actualizarDato(T datoNuevo) throws SQLException;
    int borrarDatos(int id);
}
