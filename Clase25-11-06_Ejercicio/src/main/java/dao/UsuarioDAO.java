package dao;

import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface UsuarioDAO {
    //MÉTODOS PROPIOS DE LA CLASE

    // Métod para exportar datos de MySQL a XML
    void exportarMySQLaXML();

    // Métod para escribir una lista de usuarios en XML
    void escribirUsuariosAXML(List<Usuario> usuarios, String rutaArchivo);

    // Métod para leer y mostrar un archivo XML
    void leerXML(String rutaArchivo);

    void escribirHTML();
}












/*    // Métod para probar la conexión a la base de datos
    boolean probarConexion();*/