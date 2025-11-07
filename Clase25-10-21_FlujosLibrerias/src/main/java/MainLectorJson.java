import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ProductJson;
import model.RespuestaProductosJson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

//SE MAPEA UN DOC JSON EXTERNO. TENGO QUE FACILITAR LA URL
public class MainLectorJson {
    public static void main(String[] args) {
        //ObjectMapper mapea desde URL toda la estructura
        ObjectMapper mapper = new ObjectMapper();

        //meto la URL, pero me da exception
        try {
            URL url = new URL("https://dummyjson.com/products");

            //Igualo RespuestaProductos para que el mapeador lea un valor URL, de RespuestaProductosJson. Da tres catch
            RespuestaProductosJson respuestaProductosJson = mapper.readValue(url, RespuestaProductosJson.class);

            //tengo la RespuestaProductos que es una lista que tengo que recorrer
            for (ProductJson item : respuestaProductosJson.getProducts()) {
                System.out.println(item);
            }
            
        } catch (MalformedURLException e) {
            System.out.println("Error en la URl indicada");
        } catch (StreamReadException e) {
            System.out.println("Error de lectura, en la lectura. No es Json");
        } catch (DatabindException e) {
            System.out.println("Error en el mapeado Json, las variables y el tipo no se llaman correctamente");
        } catch (IOException e) {
            System.out.println("Error en la conexión de red");
        }
    }
}
