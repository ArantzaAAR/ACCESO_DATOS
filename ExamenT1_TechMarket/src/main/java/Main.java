import controller.TechController;
import dao.ProductoDAOImp;
import model.Producto;


import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        TechController techController = new TechController();
        ProductoDAOImp usuarioDAOImp = new ProductoDAOImp();

        int opcion = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Sistema de TECHMARKET");
            System.out.println("1. Insertar producto");
            System.out.println("2. Borrar producto");
            System.out.println("3. Listar productos");
            System.out.println("4. Guardar en fichero.dat");
            System.out.println("5. Generar en archivo.xml");
            System.out.println("6. Actualizar precios periféricos");
            System.out.println("7. Salir");
            System.out.println("Indique qué quiere hacer");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Indique el nombre");
                    String nombre = scanner.nextLine();
                    System.out.println("Indique el precio");
                    double precio = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Indique la categoria");
                    String categoria = scanner.nextLine();

                   techController.insertarProducto(new Producto(nombre, precio, categoria));
                }
                case 2 -> {
                    System.out.println("Indique el id del producto que quiera borrar");
                    int id = scanner.nextInt();
                    techController.borrarProducto(id);
                }
                case 3 -> {
                    techController.listarProductos();
                }
                case 4 -> {
                    List<Producto> productos = techController.obtenerProductos();
                    techController.guardarFicheroDAT(productos, "src/main/java/controller/productos.dat");
                }
                case 5 -> {
                    techController.exportarXML();
                }
                case 6 -> {

                }
            }

        } while (opcion != 7);
    }
}

