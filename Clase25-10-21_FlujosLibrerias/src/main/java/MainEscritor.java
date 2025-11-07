import model.Direccion;
import model.Listado;
import model.Usuario;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class MainEscritor {

    public static void main(String[] args) {

        //creo los objetos para hacer el listado
        Listado listado = new Listado();
        listado.getListado().add(new Usuario("1234", "Arantza", "Alcázar",
                new Direccion("Calle de las Cerquillas, 6", 28430, "Madrid")));
        listado.getListado().add(new Usuario("2345", "César", "Alcázar",
                new Direccion("Calle Sahagún, 2", 28039, "Madrid")));
        listado.getListado().add(new Usuario("3456", "Juan", "Morote",
                new Direccion("Calle de las Cerquillas, 6", 28430, "Madrid")));
        listado.getListado().add(new Usuario("4567", "César", "Santos",
                new Direccion("Calle Sahagún, 2", 28039, "Madrid")));
        listado.getListado().add(new Usuario("5678", "Margarita", "Romero",
                new Direccion("Calle Sahagún, 2", 28039, "Madrid")));


        try {
            //Me permite hacer la creación del generador del XML. Tengo que incluir la clase sobre la que me voy a basar.
            // Me da una excepción(no tienes RootElement, campos no bien marcados,
            JAXBContext context = JAXBContext.newInstance(Listado.class);

            //Creo el mapeador, traduce el objeto y su definición entera con atributos y contenido y extrae las notaciones
            //lo tengo en java y lo doy en xml
            Marshaller marshaller = context.createMarshaller();

           //sobre le mapeador, mapeo el objeto listado y creo el fichero usuarios.xml
            marshaller.marshal(listado, new File("usuarios2.xml"));
        } catch (JAXBException e) {
            System.out.println("Mapeado fallido"); //las anotaciones no son las correctas
        }

    }
}
