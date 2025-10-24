import com.fasterxml.jackson.core.JsonToken;
import controller.DrinkController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //El usuario me tiene que dar datos, por lo que lo pido por consola
        Scanner scanner = new Scanner(System.in);
        //para que se cumpla la condición de NO salida creo la variable opción para inicializar y que pueda identificar la opcion salida (4)
        int opcion = 0;
        //creo una variable de DrinkController para que si tengo el objeto DrinkController pueda acceder a todas sus funcionalidades (metodo consultar nombre
        DrinkController controller = new DrinkController();
        //hago un menú que meto en un do-while para que ejecute siempre que se cumpla la condición de NO salida
        do{
             System.out.println("Elige una de estas opciones:");
            System.out.println("1. Buscar lo nombre");
            System.out.println("2. Buscar por letra");
            System.out.println("3. Buscar por ingrediente");
            System.out.println("4. ¡Sorpréndeme! (Cóctel random)");
            System.out.println("5. Información por ingrediente");
            System.out.println("6. Filtrar por tipo de vaso");
            System.out.println("7. Ver todas las categorías");
            System.out.println("8. Salir");
            System.out.println("Selecciiona una opción: ");
            //leo la petición del cliente
            opcion = scanner.nextInt();

            //hago un switch para ver qué ejecuta en funicón a la respuesta del scanner
            switch (opcion){
                case 1->{
                    System.out.println("Búsqueda por nombre");
                    System.out.println("¿Por qué nombre quieres buscar?");
                    String nombre = scanner.next();
                    //controller.consultarNombre(nombre.toLowerCase());
                    controller.consultarPalabra(nombre.toLowerCase(), "s");
                }
                case 2->{
                    System.out.println("Búsqueda por letra");
                    System.out.println("¿Por qué letra quieres realizar la búsqueda?");
                    String letra = scanner.next();
                    //controller.buscarLetra(letra);
                    controller.consultarPalabra(letra.toLowerCase(), "f");
                }
                case 3->{
                    System.out.println("Búsqueda aleatoria");
                    controller.aleatorio();
                }
                case 4->{
                    System.out.println("Saliendo");
                }
            }
        }while (opcion != 4);

    }
}
