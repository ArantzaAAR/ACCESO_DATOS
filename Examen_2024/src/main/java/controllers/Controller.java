package controllers;

import java.util.Scanner;

public class Controller {
    private Scanner scanner;

    // Constructor para inicializar el Scanner
    public Controller() {
        scanner = new Scanner(System.in);
    }

    public int checkIntAnswer() {
        int respuesta;
        while (true) {
            if (scanner.hasNextInt()) {
                respuesta = scanner.nextInt();
                scanner.nextLine(); // Consume el salto de línea
                return respuesta;
            } else {
                System.out.println("Entrada inválida. Por favor, introduzca un número válido.");
                scanner.nextLine(); // Limpia el buffer
            }
        }
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
