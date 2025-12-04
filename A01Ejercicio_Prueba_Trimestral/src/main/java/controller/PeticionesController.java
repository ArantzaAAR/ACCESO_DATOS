package controller;

import dao.LibroDAOImp;
import model.Libro;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PeticionesController {
    private LibroDAOImp libroDAOImp;
    public PeticionesController(){
        libroDAOImp = new LibroDAOImp();
    }

    public void insertarLibro(Libro libro){
        Scanner scanner = new Scanner(System.in);
        boolean fallo = false;
        do {
            try {
                libroDAOImp.insertarDato(libro);
                fallo = false;
            } catch (SQLException e) {
                System.out.println("Error, isbn duplicado. Ya está en la BBDD");
                System.out.println("Introduce el nuevo isbn");
                fallo = true;
                String isbn = scanner.next();
                libro.setIsbn(isbn);
            }
        }while(fallo);
        System.out.println("Usuario agregado correctamente");
    }

    public void borrarLibro(int id){
        int rows = libroDAOImp.borrarDatos(id);
        if(rows>1){
            System.out.println("Usuarios borrados correctamente");
        }else if(rows == 1){
            System.out.println("Usuario borrado correctamente");
        }else if(rows == 0){
            System.out.println("No se ha encontrado usuario con ese id");
        }
        System.out.println("Fallo en el proceso");
        System.out.println("DIme el id del usuario que quieres borrar");
    }

    public void listarLibros(){
        System.out.println("LISTADO DE LIBROS");
        for (Libro item:libroDAOImp.obtenerListaDatos()) {
            item.mostrarDatos();
        }
    }

   /* public void exportacionXML(){
        JAXBContext context = null;//primero creamos el contexto y lo dejo a nulo para poder tratar en otro momento si lo considero necesario
        Marshaller marshaller; // Es como un mapeo, pero que escribe
        List<Libro> lista = libroDAOImp.obtenerListaDatos();//esta es la lista de usuarios que quiero guardar en el XML

        LibroXML libroXML = new LibroXML();//me creo el objeto general
        libroXML.setLista(lista);//lo lleno con la lista que he obtenido

        try {
            context =JAXBContext.newInstance(LibroXML.class);
            marshaller = context.createMarshaller();
            marshaller.marshal(libroXML, new File("src/main/java/controller/usuarios.xml"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public void importarXML(){
        JAXBContext context;
        Unmarshaller unmarshaller;

        try {
            context = JAXBContext.newInstance(LibroXML.class);
            unmarshaller = context.createUnmarshaller();
            LibroXML libroXML= (LibroXML) unmarshaller.unmarshal(new File("src/main/java/controller/libros.xml"));
            for (Libro item : libroXML.getLista()) {
                libroDAOImp.insertarDato(item);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Error, se repite un isbn");
        }
    }*/


}
