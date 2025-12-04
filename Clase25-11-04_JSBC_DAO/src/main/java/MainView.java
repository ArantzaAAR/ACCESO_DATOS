

import controller.PeticionesController;
import model.Usuario;

import java.util.Scanner;

public class MainView {
    //VIEW -> LA INTERACCION CON EL USUARIO
    public static void main(String[] args) {
        PeticionesController peticionesController = new PeticionesController();

        int opcion; //inicializo la opción
        Scanner scanner = new Scanner(System.in);

        do {//que lo repita hasta que sea la opción de salir
            System.out.println("Sistema de gestión de usuarios: ");
            System.out.println("1. Insertar usuario");
            System.out.println("2. Borrar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Exportar usuarios a XML");
            System.out.println("5. Importar usuarios desde XML");
            System.out.println("6. Exportar usuarios a JSON");
            System.out.println("7. Importar usuarios desde JSON");
            System.out.println("8. Salir");
            System.out.println("Indica qué quieres hacer");

            //opcion lo relleno con lo que diga el scanner
            opcion = scanner.nextInt();
            scanner.nextLine();

            //me switch para que en función de la opción, te de un resultado
            switch (opcion) {
                case 1 -> {
                    System.out.println("Indica el nombre");
                    String nombre = scanner.nextLine();//esto es la capa de la vista y tengo que pedir cada uno de los datos
                    System.out.println("Indica el correo");
                    String correo = scanner.nextLine();
                    System.out.println("Indiaca el  teléfono");
                    int telefono = scanner.nextInt();
                    System.out.println("Indica el perfil");
                    int perfil = scanner.nextInt();

                    //aquí paso el usuario que quiero insertar
                    peticionesController.insertarUsuario(new Usuario(nombre, correo, telefono, perfil));//llamamos al metodo adecuado con las variables que acabo de declarar
                }
                case 2 -> {
                    System.out.println("Indica el id del usuario que quieres borrar");
                    int id = scanner.nextInt();//solicito la info
                    peticionesController.borrarUsuario(id);
                }
                case 3 -> {
                    peticionesController.listarUsuarios();
                }
                case 4 -> {
                    peticionesController.exportacionXML();
                }
                case 5 -> {
                    peticionesController.importarXML();
                }
                case 6 -> {

                }
                case 7 -> {
                    peticionesController.importarJSON();
                }
            }

        } while (opcion != 8); //cuado sea la opcion de salir, no quiero que repitas
        peticionesController.insertarUsuario(new Usuario("BorjaM", "borjam@gmail.com", 625, 2));
    }
}
