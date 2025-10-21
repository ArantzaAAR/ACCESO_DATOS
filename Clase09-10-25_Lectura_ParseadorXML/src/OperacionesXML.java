import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class OperacionesXML {
    public void lecturaXML() {

        //1- NECESITO CREAR EL FICHERO File
        File file = new File("src/usuarios.xml");
        //2- CREO EL GENERADOR DEL DocumentBuilder
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();//El metodo newInstance, al ser estático que pertenece a la clase, se accede a él a través del nombre de la clase. Es estático para

        try {
            //3- CREO EL PARSEADOR PARA QUE PUEDA GENERAR EL DOCUMENT. Lo meto en la excepción
            DocumentBuilder db = dbf.newDocumentBuilder();//creamos una instancia para hacer un nuevo newDocumentBuilder
            //4- LO CONVIERTO EN UN DOCUMENT  Y LO PARSEO. Meto las dos excepciones
            Document doc = db.parse(file);

            //5- busco los NODOS con el metodo getElementByTagName, seleccionando los nodos que quiero que lea
            NodeList nodosUsuario = doc.getElementsByTagName("usuario");//se puede buscar: getDocumentById, ByClass, ByTag

            //6-recorro la lista con un for
            for (int i = 0; i < nodosUsuario.getLength(); i++) {
                System.out.println("Nodo analizando");

                //Llamo a Element para sacar datos de xml. Tengo que castear a Element
                Element nodo = (Element) nodosUsuario.item(i);

                //Creo variable de los datos que quiero sacar
                String nacionalidad = nodo.getAttribute("nacionalidad");
                int edad = Integer.parseInt(nodo.getAttribute("edad"));

                //Como nombre está en el nodosUsuario, se utiliza otros métodos
                String nombre = nodo.getElementsByTagName("nombre").item(0).getTextContent();//solo tiene contenido, por eso pido el contenido de texto
                String apellido = nodo.getElementsByTagName("apellido").item(0).getTextContent();

                //Imprimo en pantalla
                System.out.println("La nacionalidad es: " + nacionalidad);
                System.out.println("El nombre es: " + nombre);
                System.out.println("El apellido es: " + apellido);
                System.out.println("La edad es: " + edad);
            }

            //3- Excepción de DocumentBuilder. Está mal configurado y no es capaz de leerlo (mal el nombre, 2 nodos root...)
        } catch (ParserConfigurationException e) {
            System.out.println("Error en el parseo");
            //4- Excepciones del PARSEO de Document
        } catch (IOException e) {
            System.out.println("Error, no tienes permisos de lectura ");//no tienes acceso a un fichero
        } catch (
                SAXException e) {
            System.out.println("Error de SAX");//esás intentando hacer una traducción que no es capaz de hacerla
        }
    }
}


