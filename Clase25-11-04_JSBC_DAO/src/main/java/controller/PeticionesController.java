package controller;

import dao.UsuarioDAOImp;
import model.Usuario;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class PeticionesController {
    //LA CAPA DE LÓGICA DE NEGOCIO, PERO NO VA CONTRA LA BBDD. Es un filtro que si lo cumple, se realiza la consulta a la BBDD desde DAO
    //EN CASO QUE UN USUARIO SE DÉ DE REGISTRE, INDICAMOS CÓMO ACTUAR ANTE UN ERROR PRODUCIDO POR ELEMENTOS EXTERNOS

    //Para mandar un correo electrónico a todos los elementos. Esto es la capa lógica, pero tengo que ir a DAO para extraer todos los datos de la BBDD
    //como voy a trabajar cosas del DAO, tengo que implementar un objeto DAO
    private UsuarioDAOImp usuarioDAOImp;

    //como lo voy a utilizar dentro, lo pido en el constructor
    public PeticionesController() {
        usuarioDAOImp = new UsuarioDAOImp();//con esto se activa la conexión

    }

    public void insertarUsuario(Usuario usuario) {
        //voy a intentar insertar un usuario, pero si tengo un error en el correo, porque está duplicado
        //pido nuevamente el correo y lo intento agregar otra vez.
        //aquí analizo si ha podido insertarUsuario
        boolean fallo = false;
        do{
            try {
                usuarioDAOImp.insertarDato(usuario);
                fallo = false;
                System.out.println("Usuario agregado correctamente");
            } catch (SQLException e) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("El correo ya esta en la bd");
                System.out.println("Introduce el nuevo correo electronico");
                String correo = scanner.next();
                usuario.setMail(correo);
                fallo = true;
            }
        } while (fallo);
    }
    }
