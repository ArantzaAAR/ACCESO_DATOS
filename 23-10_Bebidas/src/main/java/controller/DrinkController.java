package controller;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Drink;
import model.ResponseDrinks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;

//ESTA ES LA PARTE LÓGICA DE LA APLICACIÓN. SEPARO LA PARTE LÓGICA, DE MODELO Y VISTA
public class DrinkController {
    //PARA OBTENER EL COCTAIL POR EL NOMBRE
    public void consultarNombre(String nombre) {
        //creo mapeador para coger lo  que da la URL y pasarlo al objeto correspondiente
        ObjectMapper mapper = new ObjectMapper();

        try {
            //Copio la URL pero quiero que haga la búsqueda por parámetro(nombre). Da Exception
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=" + nombre);
            //de la URL necesito que mapper lea la url y le indico cómo la transformas(en una lista en la clase ResponseDrinks)
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            //cabe la posibilidad que me introduzcan una bebida que no está en la lista
            if (responseDrinks.getDrinks() != null) {
                //con responseDrinks tengo acceso a la lista Drinks para sacar los objetos Drink. Lo recorro con foEach
                for (Drink item : responseDrinks.getDrinks()) {
                    System.out.println(item);
                }
            } else {
                System.out.println("No hay cocktails con ese nombre");
            }

        } catch (MalformedURLException e) {
            System.out.println("La URL es inválida");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta: en la URL no hay JSON o no está bien formateado ");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden: el atributo del JSON no coincide");

        } catch (IOException e) {
            System.out.println("Error de internet");
        }

    }

    //PARA OBTENER EL COCTAIL POR LA LETRA
    public void buscarLetra(String letra) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + letra);
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            if (responseDrinks.getDrinks() != null) {//cabe la posibilidad que me introduzcan una bebida que no está en la lista
                for (Drink item : responseDrinks.getDrinks()) {//con responseDrinks tengo acceso a la lista Drinks para sacar los objetos Drink. Lo recorro con foEach
                    System.out.println(item);
                }
            } else {
                System.out.println("No ha cocktails con esa letra");
            }
        } catch (MalformedURLException e) {
            System.out.println("La URL es inválida");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta: en la URL no hay JSON o no está bien formateado ");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden: el atributo del JSON no coincide");
        } catch (IOException e) {
            System.out.println("Error de internet");
        }
    }

    //METODO COMUN DE BÚSQUEDA POR NOMBRE Y POR LETRA. Hago una sobrecarga del método
    //Las variables PALABRA es para recibir la petición del usuario y BUSQUEDA va a ver qué URL coge.(solo se diferencian por la s o f, por lo que en la URL
    //de este método elimino el final para dejar una URL más genérica
    public void consultarPalabra(String palabra, String busqueda) {
        //creo mapeador para coger lo  que da la URL y pasarlo al objeto correspondiente
        ObjectMapper mapper = new ObjectMapper();

        try {
            //Copio la URL pero quiero que haga la búsqueda por parámetro(nombre). Da Exception
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/search.php?" + busqueda + "=" + palabra);
            //de la URL necesito que mapper lea la url y le indico cómo la transformas(en una lista en la clase ResponseDrinks)
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            //cabe la posibilidad que me introduzcan una bebida que no está en la lista
            if (responseDrinks.getDrinks() != null) {
                //con responseDrinks tengo acceso a la lista Drinks para sacar los objetos Drink. Lo recorro con foEach
                for (Drink item : responseDrinks.getDrinks()) {
                    System.out.println(item);
                }
            } else {
                System.out.println("No ha cocktails con esa palabra");
            }

        } catch (MalformedURLException e) {
            System.out.println("La URL es inválida");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta: en la URL no hay JSON o no está bien formateado ");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden: el atributo del JSON no coincide");

        } catch (IOException e) {
            System.out.println("Error de internet");
        }

    }

    public void aleatorio() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            URL url = new URL("https://www.thecocktaildb.com/api/json/v1/1/random.php");
            ResponseDrinks responseDrinks = mapper.readValue(url, ResponseDrinks.class);
            //EN TESE CASO RANDOM SOLO DEVUELVE 1, PORQ L OQUE PIDO QUE ME DEVUELVA LA PRIMERA
            System.out.println(responseDrinks.getDrinks().getFirst());
        } catch (MalformedURLException e) {
            System.out.println("La URL es inválida, está mal escrita");
        } catch (StreamReadException e) {
            System.out.println("Lectura incorrecta: en la URL no hay JSON o no está bien formateado ");
        } catch (DatabindException e) {
            System.out.println("Los tipos de datos no coinciden: el atributo del JSON no coincide");
        } catch (IOException e) {
            System.out.println("Error de internet");
        }
    }
}

