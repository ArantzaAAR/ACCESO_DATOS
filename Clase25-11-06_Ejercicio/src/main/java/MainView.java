import controller.PeticionesController;
import dao.UsuarioDAOImp;
import model.Usuario;

import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        PeticionesController peticionesController = new PeticionesController();
        UsuarioDAOImp usuarioDAOImp = new UsuarioDAOImp();

        int opcion = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Sistema de gestión de usuarios: ");
            System.out.println("1. Insertar usuario");
            System.out.println("2. Borrar usuario");
            System.out.println("3. Listar usuarios");
            System.out.println("4. Exportar usuarios a XML");
            System.out.println("5. Importar a BBDD");
            System.out.println("6. Crear página web con nombre y mail");
            System.out.println("7. Crear tabla productos");
            System.out.println("8. Salir");
            System.out.println("Indica qué quieres hacer");

            opcion = scanner.nextInt();
            scanner.nextLine();

           switch (opcion) {
                case 1 -> {
                    System.out.println("Indica el nombre");
                    String nombre = scanner.nextLine();
                    System.out.println("Indica el correo");
                    String correo = scanner.nextLine();
                    System.out.println("Indica el  teléfono");
                    int telefono = scanner.nextInt();
                    System.out.println("Indica el perfil");
                    int perfil = scanner.nextInt();

                    peticionesController.insertarUsuario(new Usuario(nombre, correo, telefono, perfil));
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
                    peticionesController.ExportarXML();
                }
                case 5 -> {
                    peticionesController.ImportarXMLExterno("nuevosdatos.xml");
                }
               case 6 -> {
                    peticionesController.EscribirHTML();
               }
               case 7 -> {
               }
            }

        } while (opcion != 8); //cuado sea la opcion de salir, no quiero que repitas
    }
}

