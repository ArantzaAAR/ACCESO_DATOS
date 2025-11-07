package dao;

import model.Usuario;

import java.util.ArrayList;

public interface UsuarioDAO {
    //MÉTODOS PROPIOS DE LA CLASE
    ArrayList<String> obtenerCorreos();
    ArrayList<Usuario> obtenerPerfil (int idPerifl);



}
