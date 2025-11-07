import model.Listado;
import model.Usuario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MainLector {
    public static void main(String[] args) {
        try {
            //tengo que indicar el contexto para que lea el XML. Me da exception
            JAXBContext context = JAXBContext.newInstance(Listado.class);

            //Creo el mapeador, traduce el objeto y su definición entera con atributos y contenido y extrae las notaciones
            //lo tengo en xml y lo comprimo en java
            Unmarshaller unmarshaller = context.createUnmarshaller();

            //sobre le mapeador, mapeo  el fichero usuarios.xml y me da un objeto de tipo object Listado
            //como me da un object y yo doy un Listado, tengo que hacer un casteo
            Listado listado = (Listado) unmarshaller.unmarshal(new File("usuarios2.xml"));

            //hago un foreach para recorrer el listado
            for (Usuario item : listado.getListado()) {
                item.mostrarDatos();//y que lo imprima
            }
        } catch (JAXBException ex) {
            System.out.println("Mapeado fallido"); //las anotaciones no son las correctas
        }
    }
}
