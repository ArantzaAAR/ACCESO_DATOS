import controller.OperationXML;
import controller.PeticionesController;
import jdk.dynalink.Operation;

import java.util.Scanner;

public class MainView {
    public static void main(String[] args) {

        PeticionesController peticionesController = new PeticionesController();
        OperationXML operadoresXML = new OperationXML();
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("=== SISTEMAS DE GESTIÓN DE BIBLIOTECA ===\n");
            System.out.println("MENÚ PRINCIPAL:");
            System.out.println("1. Gestión de Libros");

            System.out.println("2. Gestión de Préstamos");

            System.out.println("3. Exportar Catálogo a XML");

            System.out.println("4. Generar Informe HTML");

            System.out.println("5. Exportar Datos a JSON");

            System.out.println("6. Importar Catálogo desde JSON");

            System.out.println("7. Sincronizar con Biblioteca Externa");

            System.out.println("8. Backup Completo");

            System.out.println("9. Restaurar desde Backup");

            System.out.println("10. Estadísticas y Reportes");

            System.out.println("0. Salir\n");
            System.out.println("Selecciona una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();//limpiar buffer

            switch (opcion) {
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                }
                case 4 -> {
                    int opcion2 = 0;
                    do {
                        System.out.println("=== GENERAR INFORME HTML ===");
                        System.out.println("1. Catálogo completo de libros");

                        System.out.println("2. Libros por categoría");

                        System.out.println("3. Historial de préstamos por usuario");

                        System.out.println("4. Libros más prestados");

                        System.out.println("5. Volver\n");
                        System.out.println("Selecciona una opcion: ");

                        opcion2 = scanner.nextInt();
                        scanner.nextLine();

                        int numLibros = 0;
                        switch (opcion2) {
                            case 1 -> {
                                System.out.println("Generando informe...");
                                System.out.println("✓ Datos extraídos de BD (" + numLibros + "libros)");

                                System.out.println("✓ XML generado: catalogo_temp.xml");
                                operadoresXML.escrituraXML();
                                System.out.println("✓ Transformación XSLT aplicada: catalogo_template.xslt");

                                System.out.println("✓ HTML generado: catalogo_biblioteca.html");

                                System.out.println("Archivo guardado en: ./reportes/catalogo_biblioteca.html");

                                System.out.println("¿Deseas abrir el archivo en el navegador? (S/N):");

                                String respuesta = scanner.nextLine();
                                if (respuesta.equalsIgnoreCase("S")) {
                                    // Código para abrir en navegador
                                    System.out.println("Abriendo en navegador...");
                                }

                            }
                            case 2 -> {
                            }
                            case 3 -> {
                            }
                            case 4 -> {
                            }
                            case 5 -> {
                            }
                            default -> System.out.println("Opción no válida. Elige una de las opciones propuestas");
                        }
                    }
                    while (opcion2 != 5);
                }
                case 5 -> {
                }
                case 6 -> {
                }
                case 7 -> {
                }
                case 8 -> {
                }
                case 9 -> {
                }
                case 10 -> {
                }
                case 0 -> {
                    System.out.println("Saliendo del sistema...");
                }
                default -> System.out.println("Opción no válida. Elige una de las opciones propuestas");
            }

        }while (opcion != 0);

        scanner.close();
    }
}