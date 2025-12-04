package controller;

import model.Libro;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class OperationXML extends Libro {

    public void lecturaXML() {
        File file = new File("src/main/resources/basedatos.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);

            NodeList nodoLibro = document.getElementsByTagName("titulo");

            for (int i = 0; i < nodoLibro.getLength(); i++) {
                System.out.println("Nodo analizado");

                Element nodo = (Element) nodoLibro.item(i);

                String isbn = nodo.getElementsByTagName("isbn").item(0).getTextContent();
                String titulo = nodo.getElementsByTagName("nombre").item(0).getTextContent();
                String  autor = nodo.getElementsByTagName("autor").item(0).getTextContent();
                String editorial = nodo.getElementsByTagName("editorial").item(0).getTextContent();
                int ano_publi = Integer.parseInt(nodo.getElementsByTagName("ano_publicacion").item(0).getTextContent());
                String categoria = nodo.getElementsByTagName("categoria").item(0).getTextContent();
                double precio = Double.parseDouble(nodo.getElementsByTagName("precio").item(0).getTextContent());
                int stock = Integer.parseInt(nodo.getElementsByTagName("stock").item(0).getTextContent());

            }

        } catch (ParserConfigurationException e) {
            System.out.println("Error en el parseo");
        } catch (IOException e) {
            System.out.println("Error, no tienes permisos de lectura");
        } catch (SAXException e) {
            System.out.println("Error del SAX");
        }

    }

    public void escrituraXML() {
        ArrayList<Libro> listaLibro = new ArrayList<>();
        Libro libro1 = new Libro("978-84-08-11234-0", "Juego de tronos",
                "George R. R. Martin", "Gigamesh", 1996,
                "Fantasía", 24.95, 10);

        Libro libro2 = new Libro("978-84-08-11628-7", "Harry Potter y la piedra filosofal",
                "J. K. Rowling", "Salamandra", 1997,
                "Fantasía", 16.00, 25);

        Libro libro3 = new Libro("978-84-08-15063-2", "El código Da Vinci",
                "Dan Brown", "Umbriel", 2003,
                "Thriller", 19.90, 22);

        Libro libro4 = new Libro("978-84-08-12149-4", "Los pilares de la Tierra",
                "Ken Follett", "Plaza & Janés", 1989,
                "Histórica", 21.50, 14);

        Libro libro5 = new Libro("978-84-08-11950-9", "Crónica de una muerte anunciada",
                "Gabriel García Márquez", "Debolsillo", 1981,
                "Realismo mágico", 11.25, 17);

        Libro libro6 = new Libro("978-84-08-11235-7", "Choque de reyes",
                "George R. R. Martin", "Gigamesh", 1998,
                "Fantasía", 25.50, 9);

        Libro libro7 = new Libro("978-84-08-11951-6", "El amor en los tiempos del cólera",
                "Gabriel García Márquez", "Debolsillo", 1985,
                "Realismo mágico", 15.80, 11);

        Libro libro8 = new Libro("978-84-08-12150-0", "Un mundo sin fin",
                "Ken Follett", "Plaza & Janés", 2007,
                "Histórica", 23.75, 13);

        Libro libro9 = new Libro("978-84-08-15064-9", "Ángeles y demonios",
                "Dan Brown", "Umbriel", 2000,
                "Thriller", 18.95, 19);

        Libro libro10 = new Libro("978-84-08-11629-4", "Harry Potter y la cámara secreta",
                "J. K. Rowling", "Salamandra", 1998,
                "Fantasía", 16.50, 23);

        Libro libro11 = new Libro("978-84-08-11952-3", "El coronel no tiene quien le escriba",
                "Gabriel García Márquez", "Debolsillo", 1961,
                "Realismo mágico", 10.50, 16);

        Libro libro12 = new Libro("978-84-08-11236-4", "Tormenta de espadas",
                "George R. R. Martin", "Gigamesh", 2000,
                "Fantasía", 26.25, 7);

        Libro libro13 = new Libro("978-84-08-12151-7", "La caída de los gigantes",
                "Ken Follett", "Plaza & Janés", 2010,
                "Histórica", 24.90, 15);

        Libro libro14 = new Libro("978-84-08-11630-0", "Harry Potter y el prisionero de Azkaban",
                "J. K. Rowling", "Salamandra", 1999,
                "Fantasía", 17.00, 21);

        listaLibro.add(libro1);
        listaLibro.add(libro2);
        listaLibro.add(libro3);
        listaLibro.add(libro4);
        listaLibro.add(libro5);
        listaLibro.add(libro6);
        listaLibro.add(libro7);
        listaLibro.add(libro8);
        listaLibro.add(libro9);
        listaLibro.add(libro10);
        listaLibro.add(libro11);
        listaLibro.add(libro12);
        listaLibro.add(libro13);
        listaLibro.add(libro14);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            Element nodoRoot = document.createElement("Biblioteca");
            nodoRoot.setAttribute("id", "1");
            nodoRoot.appendChild(document);
            for (Libro item : listaLibro) {
                Element nodoLibro = document.createElement("Libro");
                nodoLibro.setAttribute("id_libro", String.valueOf(item.getId()));
                nodoLibro.appendChild(nodoRoot);

                Element nodoIsbn = document.createElement("isbn");
                nodoIsbn.setTextContent(item.getIsbn());
                nodoLibro.appendChild(nodoIsbn);

                Element nodoTitulo = document.createElement("titulo");
                nodoTitulo.setTextContent(item.getTitulo());
                nodoLibro.appendChild(nodoTitulo);

                Element nodoAutor = document.createElement("autor");
                nodoAutor.setTextContent(item.getAutor());
                nodoLibro.appendChild(nodoAutor);

                Element nodoEditorial = document.createElement("editorial");
                nodoEditorial.setTextContent(item.getEditorial());
                nodoLibro.appendChild(nodoEditorial);

                Element nodoAnoPubli = document.createElement("ano_publicacion");
                nodoAnoPubli.setTextContent(String.valueOf(item.getAno_publicacion()));
                nodoLibro.appendChild(nodoAnoPubli);

                Element nodoCategoria = document.createElement("categoria");
                nodoCategoria.setTextContent(item.getCategoria());
                nodoLibro.appendChild(nodoCategoria);

                Element nodoPrecio = document.createElement("precio");
                nodoPrecio.setTextContent(String.valueOf(item.getPrecio()));
                nodoLibro.appendChild(nodoPrecio);

                Element nodoStock = document.createElement("stock");
                nodoStock.setTextContent(String.valueOf(item.getStock()));
                nodoLibro.appendChild(nodoStock);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("catalogo_temp.xml"));

        } catch (ParserConfigurationException e) {
            System.out.println("Error en le parseo");
        } catch (TransformerConfigurationException e) {
            System.out.println("Error en la configuración de la transformación del documento");
        }
    }
}
