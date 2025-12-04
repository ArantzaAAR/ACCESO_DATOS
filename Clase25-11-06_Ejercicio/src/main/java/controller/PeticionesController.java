package controller;

import dao.UsuarioDAO;
import dao.UsuarioDAOImp;
import model.Usuario;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class PeticionesController {
    private UsuarioDAOImp usuarioDAOImp;
    public PeticionesController(){
        usuarioDAOImp = new UsuarioDAOImp();
    }

    public void insertarUsuario(Usuario usuario){
        Scanner scanner = new Scanner(System.in);
        boolean fallo = false;
        do {
            try {
                usuarioDAOImp.insertarDato(usuario);
                fallo = false;
            } catch (SQLException e) {
                System.out.println("Error, correo duplicado. Ya está en la BBDD");
                System.out.println("Introduce el nuevo correo electronico");
                fallo = true;
                String correo = scanner.next();
                usuario.setMail(correo);
            }
        }while(fallo);
        System.out.println("Usuario agregado correctamente");
    }

    public void borrarUsuario(int id){
        int rows = usuarioDAOImp.borrarDatos(id);
        if(rows>1){
            System.out.println("Usuarios borrados correctamente");
        }else if(rows == 1){
            System.out.println("Usuario borrado correctamente");
        }else if(rows == 0){
            System.out.println("No se ha encontrado usuario con ese id");
        }
        System.out.println("Fallo en el proceso");
        System.out.println("DIme el id del usuario que quieres borrar");
    }

    public void listarUsuarios(){
        System.out.println("¿Quieres listar todos los usuarios o solo alguno?");
        //en caso que no
        for (Usuario item : usuarioDAOImp.obtenerListaDatos()) {
            item.mostrarDatos();
        }
    }

    public void ExportarXML(){
        usuarioDAOImp.exportarMySQLaXML();
        System.out.println("Exportación de datos a fichero XML");
    }

    public void ImportarXMLExterno(String rutaArchivo){
        usuarioDAOImp.leerXML(rutaArchivo);
        System.out.println("Importación de datos externos correcta");


    }

    public void EscribirHTML(){
        usuarioDAOImp.escribirHTML();
    }
}
