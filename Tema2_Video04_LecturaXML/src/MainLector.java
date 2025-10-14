import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;

public class MainLector {
    //se crean las excepciones porque no se va a independizar errores al ser cosas fijas, se pasa por el throw Parser es de DocumentBuilder.
    //IOException (el fichero puede no existir), SAXException (puede haber un error de conversión) son de builder. parse()
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        File file = new File("personas.xml");//Creamos el fichero con la ruta para que lo lea
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();//Creamos un objeto tipo Builder
        DocumentBuilder builder = builderFactory.newDocumentBuilder();//creamos un builder para poder traducir lo squ está en XML y pasarlo al fichero
        Document document = builder.parse(file);//Creo el Document con la libreria DOM e indico el fichero que queremos parsear(convertir en fichero java entendible)

        //YA TENGO EL DOCUMENTO, EMPIEZO A HACER LA LECTURA
        //Captura la información que quiero extraer de los nodos, por lo que usamos NodeList porque usamos una lista de personas.
        //lo buscamos con el método getElementsByTagName de persona para que encuentre el contenido
        NodeList listaPersonas = document.getElementsByTagName("persona");

        for (int i = 0; i < listaPersonas.getLength(); i++) {//Tengo que recorrer la lista nodo a nodo con un for
            Node node= listaPersonas.item(i);//saco individualmente los nodos
            if (node.getNodeType() == Node.ELEMENT_NODE){//Para hacer análisis del contenido, el tipo de nodo tiene que ser un elemento
                Element persona = (Element) node;//Creamos la variable persona, para crear un objeto de tipo ELEMENT. Se hace el casteo
                String dni = persona.getAttribute("dni");//Se sacan los datos, que son atributos, pero para capturar indico que de persona quiero obtener un atributo
                String ciudad = persona.getAttribute("ciudad");
                String nombre = persona.getElementsByTagName("nombre").item(0).getTextContent();
                String apellido = persona.getElementsByTagName("apellido").item(0).getTextContent();
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido: " + apellido);
                System.out.println("Dni: " + dni);
                System.out.println("Ciudad: " + ciudad);
                System.out.println("----------------------------------------");

            }

        }

    }
}
