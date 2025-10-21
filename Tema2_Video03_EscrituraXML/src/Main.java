import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Main {
    //se mete en el main las excepciones de DocumentBuilder y Transformer
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {

        //0º- CREAR UN DOCUMENTO FUENTE de dónde va a coger todos los datos
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //nos permite crear documentos
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        //1º- CREAR UNA RAIZ
        Element root = document.createElement("personas");//solo va a tener nodos intermedios

        //2º- CREAR NODOS INTERMEDIOS
        Element persona = document.createElement("persona");//creamos los elementos
        document.appendChild(root);//añado el DOM al nodoRaiz

        persona.setAttribute("dni", "1234A");//creamos los atributos del nodo persona
        persona.setAttribute("ciudad", "Madrid");
        root.appendChild(persona);//meter el nodo persona en el root personas

        //3º- MONTAR LOS NODOS - poner los nodos entre cada uno de sus elementos
        //para crear otros nodos dentro del nodo persona
        Element nombre = document.createElement("nombre");
        nombre.setTextContent("Juan");//introducimos el valor dentro del nodo
        Element apellido = document.createElement("apellido");
        apellido.setTextContent("Morote");
        persona.appendChild(nombre);// se introduce la variable del nodo nombre en el nodo persona
        persona.appendChild(apellido);

        //4º- ESCRIBIR EL DOCUMENTO EN XML
        TransformerFactory tf = TransformerFactory.newInstance();//libreria TransformerFactory
        Transformer t = tf.newTransformer();//creo el transformer a traves de la variable tf

        //Creamos un DOMSource, para coger un elemento y crear un DOM a través de todos los datos que hemos creado en document
        DOMSource source = new DOMSource(document);//pasa el documento completo al DOMSource

        //Creo un flujo para que pueda guardarlo en el fichero que no está creado, por lo que lo creo con File
        StreamResult result = new StreamResult(new File("personas.xml"));

        //Yo he incluido el código para que indente la información. También se puede dar formato al xml con atajo teclado
        t.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        //con t transformamos el source a traves de result, el resultado es la obtención de datos
        t.transform(source, result);

    }
}

