package model;

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
import java.util.ArrayList;

public class OperadoresXML {



    public void escrituraXML() {
        ArrayList<Pelicula> listaPelicula = new ArrayList<>();

        //las películas están realizadas como si fueran un OBJETO
        Pelicula p1 = new Pelicula(
                "El Chico",
                "Charles Chaplin",
                new Actor[]{ new Actor("Charles Chaplin"), new Actor("Jackie Coogan") },
                "Comedia dramática",
                1921,
                8.3,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/The_Kid.jpg/800px-The_Kid.jpg");

        Pelicula p2 = new Pelicula(
                "Tiempos Modernos",
                "Charles Chaplin",
                new Actor[]{ new Actor("Charles Chaplin"), new Actor("Paulette Goddard") },
                "Comedia satírica",
                1936,
                8.5,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/Modern_Times_poster.jpg/800px-Modern_Times_poster.jpg");

        Pelicula p3 = new Pelicula(
                "Luces de la Ciudad",
                "Charles Chaplin",
                new Actor[]{ new Actor("Charles Chaplin"), new Actor("Virginia Cherrill") },
                "Comedia romántica",
                1931,
                8.5,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/City_Lights_%281931_poster%29.jpg/800px-City_Lights_%281931_poster%29.jpg");

        Pelicula p4 = new Pelicula(
                "El Gran Dictador",
                "Charles Chaplin",
                new Actor[]{ new Actor("Charles Chaplin"), new Actor("Paulette Goddard"), new Actor("Jack Oakie") },
                "Sátira política",
                1940,
                8.4,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/The_Great_Dictator_%281940%29.png/800px-The_Great_Dictator_%281940%29.png");

        Pelicula p5 = new Pelicula(
                "La Quimera del Oro", "Charles Chaplin",
                new Actor[]{new Actor("Charles Chaplin"), new Actor("Mack Swain") },
                "Comedia aventuras",
                1925,
                8.2,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/The_Gold_Rush_%281925%29.png/800px-The_Gold_Rush_%281925%29.png");

        Pelicula p6 = new Pelicula(
                "Sopa de Ganso",
                "Leo McCarey",
                new Actor[]{new Actor("Groucho Marx"), new Actor("Chico Marx"), new Actor("Harpo Marx") },
                "Comedia absurda",
                1933,
                7.7,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Duck_Soup_%281933%29.jpg/800px-Duck_Soup_%281933%29.jpg");

        Pelicula p7 = new Pelicula(
                "Una Noche en la Ópera",
                "Sam Wood",
                new Actor[]{ new Actor("Groucho Marx"), new Actor("Chico Marx"), new Actor("Harpo Marx") },
                "Comedia",
                1935,
                7.9,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/A_Night_at_the_Opera_%281935%29.jpg/800px-A_Night_at_the_Opera_%281935%29.jpg");

        Pelicula p8 = new Pelicula(
                "El Hotel de los Líos",
                "Sam Wood",
                new Actor[]{ new Actor("Groucho Marx"), new Actor("Chico Marx"), new Actor("Harpo Marx") },
                "Comedia",
                1938,
                7.8,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Room_Service_%281938%29_1.jpg/800px-Room_Service_%281938%29_1.jpg");

        Pelicula p9 = new Pelicula(
                "Una Tarde en el Circo",  "Edward Buzzell",
                new Actor[]{ new Actor("Groucho Marx"), new Actor("Chico Marx"), new Actor("Harpo Marx") },
                "Comedia",
                1939,
                7.6,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/At_the_Circus_%281939%29.jpg/800px-At_the_Circus_%281939%29.jpg");

        Pelicula p10 = new Pelicula(
                "Los Hermanos Marx en el Oeste",
                "Edward Buzzell",
                new Actor[]{ new Actor("Groucho Marx"), new Actor("Chico Marx"), new Actor("Harpo Marx") },
                "Comedia-Western",
                1940,
                7.1,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Go_West_%281940%29.jpg/800px-Go_West_%281940%29.jpg");

        listaPelicula.add(p1);
        listaPelicula.add(p2);
        listaPelicula.add(p3);
        listaPelicula.add(p4);
        listaPelicula.add(p5);
        listaPelicula.add(p6);
        listaPelicula.add(p7);
        listaPelicula.add(p8);
        listaPelicula.add(p9);
        listaPelicula.add(p10);

        //1.CREO EL DOCUMENTO (Document, que es el XML que voy a crear, que me ofrece la posibilidad de escribir etiquetas)
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();//creo el documento de la pagina con newDocument. Este el documento fuente

            //2. CREO EL NODO PRINCIPAL
            //indico cual es el nodo root
            Element nodoRoot = document.createElement("cartelera");//creo el elemento con createElement y pongo el nombre de la etiqueta que quiero que tenga
            nodoRoot.setAttribute("id", "1");//al nodo root le pongo un atributo con un id numero 1
            document.appendChild(nodoRoot); //lo añado al Document

            //3. CREO LOS NODOS - PELICULA. Recorro las 10 películas, indico cómo se hace una y el resto se van creando
            for (Pelicula item : listaPelicula) {
                //Creo la película con sus atributos
                Element nodoPelicula = document.createElement("pelicula");//tengo el primer nodo de la primera película
                nodoPelicula.setAttribute("genero", item.getGenero()); //pido a nodoPelicula que genere el atributo género del item Pelicula
                nodoPelicula.setAttribute("anio", String.valueOf(item.getAnio()));//pido a nodoPelicula que genere el año, pero como es un entero y lo casteo a String con valueOf()
                nodoRoot.appendChild(nodoPelicula);//lo incluyo en el documento, dentro del nodo raiz Cartelera

                //4. Creo los componentes: 1. título, director, calificación y cartel, que son los nodos, 2. Actores que es una clase y es más compleja
                //4.1 Creo título y pido su contenido de texto
                Element nodoTitulo = document.createElement("titulo");//creo el nodo titulo con create
                nodoTitulo.setTextContent(item.getTitulo());//no tiene Atributo, pero tiene texto, por lo que pido con setTextContent que el item(que es listaPelicula) me facilite el titulo
                nodoPelicula.appendChild(nodoTitulo);//lo incluyo en el documento, dentro del nodo Pelicula
                //4.2 Creo director y pido su contenido de texto
                Element nodoDirector = document.createElement("director");//creo e nodo director con create
                nodoDirector.setTextContent(item.getDirector());//no tiene Atributo, pero tiene texto, por lo que pido con setTextContent que el item(que es listaPelicula me facilite el director
                nodoPelicula.appendChild(nodoDirector);//lo incluyo en el documento, dentro del nodo Pelicula
                //4.3 Creo calificacion y pido su contenido de texto
                Element nodoCalificacion = document.createElement("calificacion");//creo e nodo director con create
                nodoCalificacion.setTextContent(String.valueOf(item.getCalificacion()));//no tiene Atributo, pero tiene texto, por lo que pido con setTextContent que el item(que es listaPelicula me facilite el director
                nodoPelicula.appendChild(nodoCalificacion);//lo incluyo en el documento, dentro del nodo Pelicula
                //4.4 Creo cartel y pido su contenido de texto
                Element nodoCartel = document.createElement("cartel");//creo e nodo director con create
                nodoCartel.setTextContent(item.getCartel());//no tiene Atributo, pero tiene texto, por lo que pido con setTextContent que el item(que es listaPelicula me facilite el director
                nodoPelicula.appendChild(nodoCartel);//lo incluyo en el documento, dentro del nodo Pelicula

                //5. Creo el nodo Actores. Tengo que crear nodo actores y recorrer el array del nodo Actores con un for each
                Element nodoActores = document.createElement("actores");//creo el nodo actores
                nodoPelicula.appendChild(nodoActores);//lo incluyo en el documento, dentro del nodo Pelicula
                for (Actor actor: item.getActores()) {//recorremos la variable item(de listaPelicula) queme devuelva un Array que tiene un Actor y con la variable actor
                    Element nodoActor = document.createElement("Actor");
                    nodoActor.setTextContent(actor.getNombre());//creo el nodo Actor, al que le pido el contenido de texto que coincida con el atributo
                    // nombre
                    nodoPelicula.appendChild(nodoActor);//lo incluyo en el documento, dentro del nodo Pelicula. Lo meto en el for, para que lo incluya cada vez que lo recorre
                }
            }
            //6. YA TENGO LOS DATOS QUE QUIERO INCLUIR, AHORA TENGO QUE CREAR EL FICHERO DONDE VOY A ESCRIBIR EL XML
            TransformerFactory tf = TransformerFactory.newInstance();//quiero transformar los datos a un documento XML. Creo una instancia
            Transformer transformer = tf.newTransformer();//con la instancia creo el new Transformador. Me da una excepción (TransformerConfigurationException)
            // que incluyo con la parserConfiguration, pero que debo de eliminar porque tendré un error de TransformerException que es el padre.
            // Ese error es que las configuraciones del XML no son correctas

            //creo el documento fuente, que hemos creado al inicio, del que tengo que extraer la información para convertir.
            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(new File("peliculas.xml"));//necesito un Stream de tipo Result. Nos lo pide en formato XML. Ya tengo un documento y
            // lo tengo que guardar estructurado XML. Nos pide dónde queremos guardarlo"peliculas.xml"
            transformer.transform(domSource, result);//tengo la capacidad de ejecutar el metodo Transformer. Me pide la fuente de donde extrae la info (domSource)
            // y un flujo de datos saliente (result), dónde quiere que lo guardemos (peliculas). Me da un catch TransformerException

        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Error en el parseo del documento");//Excepción de DocumentBuilder
        }
    }
}
