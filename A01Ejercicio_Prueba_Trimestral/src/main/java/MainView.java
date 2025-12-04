import controller.PeticionesController;
import dao.LibroDAO;
import dao.LibroDAOImp;
import model.Libro;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {
        PeticionesController peticionesController = new PeticionesController();
        LibroDAOImp libroDAOImp = new LibroDAOImp();

        int opcion = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("SISTEMA DE GESTIÓN DE LIBROS:");
            System.out.println("1. Insertar libro");
            System.out.println("2. Borrar libro");
            System.out.println("3. Listar libros");
            System.out.println("4. Exportar libros a XML");
            System.out.println("5. Importar XML externo");
            System.out.println("6. Escribir HTML");
            System.out.println("7. Salir");
            System.out.println("Por favor, elige una de las opciones propuestas");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Indica el titulo");
                    String titulo = scanner.nextLine();
                    System.out.println("Indica el autor");
                    String autor = scanner.nextLine();
                    System.out.println("Indica el  año de publicación");
                    int ano = scanner.nextInt();
                    System.out.println("Indica el isbn");
                    String isbn = scanner.next();
                    System.out.println("Indica el idUsuario");
                    int idUsuario = scanner.nextInt();

                    peticionesController.insertarLibro(new Libro(titulo, autor, ano, isbn, idUsuario));
                }
                case 2 -> {
                    System.out.println("Indica el id del libro que quieres borrar");
                    int id = scanner.nextInt();//solicito la info
                    peticionesController.borrarLibro(id);
                }
                case 3 -> {
                    peticionesController.listarLibros();
                }
                case 4 -> {

                }
                case 5 -> {

                }
                case 6 -> {

                }
            }

        } while (opcion != 7);
    }
}
