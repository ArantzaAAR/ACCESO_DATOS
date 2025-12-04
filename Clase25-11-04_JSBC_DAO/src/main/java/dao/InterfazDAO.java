package dao;

import model.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfazDAO<T> {
    //PONEMOS TODOS LOS MÉTODOS QUE EL USUARIO PUEDE HACER CONTRA BBDD
    //Una interafaz es contrato que define un conjunto de métodos abstractos y constantes que una clase debe implementar,
    // especificando "qué" se debe hacer, pero no "cómo" se debe hacer.
    //Los métodos se implementa y suelen ser abstractos y sin cuerpo, excepto si son genéricos, que están marcados como default,
    // que sí que pueden tener cuerpo. No tiene un uso muy potente, pero es importante tener en cuenta.
    default void metodoGeneral() {
        //tendría cuerpo
    }

    //CRUD GENÉRICO

    //para crear un dato
    boolean insertarDato(T dato) throws SQLException;

    //para obtener una lista generica de un dato, no tiene que preguntar nada pero tienes que devolver un ArrayList
    ArrayList<T> obtenerListaDatos();

    void actualizarDato(T datoNuevo) throws SQLException;

    //actualizar un usuario
    int borrarDatos(int id);
}

