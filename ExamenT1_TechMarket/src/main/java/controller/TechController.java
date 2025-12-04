package controller;

import dao.ProductoDAOImp;
import model.Producto;
import model.ProductoXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TechController {
    private ProductoDAOImp productoDAOImp;

    public TechController() {
        productoDAOImp = new ProductoDAOImp();
    }

    public void insertarProducto(Producto producto) {
        Scanner scanner = new Scanner(System.in);
        boolean fallo = false;
        do {
            try {
                productoDAOImp.insertarDato(producto);
                fallo = false;
            } catch (SQLException e) {
                System.out.println("Error, producto duplicado. Ya está en la BBDD");
                System.out.println("Introduce el nuevo producto");
                fallo = true;
                String correo = scanner.next();
                producto.setNombre(producto.getNombre());
            }
        } while (fallo);
        System.out.println("Producto agregado");
    }

    public void borrarProducto(int id) {
        int rows = productoDAOImp.borrarDatos(id);
        if (rows > 1) {
            System.out.println("Productos borrados correctamente");
        } else if (rows == 1) {
            System.out.println("Producto borrado correctamente");
        } else if (rows == 0) {
            System.out.println("No se ha encontrado producto con ese id");
        }
        System.out.println("Fallo en el proceso");
        System.out.println("DIme el id del producto que quieres borrar");
    }

public void listarProductos() {
    for (Producto item : productoDAOImp.obtenerListaDatos()) {
        item.mostrarDatos();
    }
}

 public static void guardarFicheroDAT(List<Producto> productos, String archivo) throws IOException {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = new FileOutputStream(archivo);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(productos);
            System.out.println("Productos guardados");

        } catch (FileNotFoundException e) {
            System.out.println("Error, archivo no encontrado");
        } catch (IOException e) {
            System.out.println("Error, no tiene permiso de lectura");
        }
        oos.close();
    }

    public List<Producto> obtenerProductos() {
        return productoDAOImp.obtenerListaDatos();
    }
    public void exportarXML(){
        JAXBContext context = null;
        Marshaller marshaller;
        List<Producto> lista = productoDAOImp.obtenerListaDatos();
        ProductoXML usuarioXML = new ProductoXML();
        usuarioXML.setLista(lista);

        try {
            context =JAXBContext.newInstance(ProductoXML.class);
            marshaller = context.createMarshaller();
            marshaller.marshal(usuarioXML, new File("src/main/java/controller/productos.dat"));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public void importarXML(){
        JAXBContext context;
        Unmarshaller unmarshaller;

        try {
            context = JAXBContext.newInstance(ProductoXML.class);
            unmarshaller = context.createUnmarshaller();
            ProductoXML usuarioXML= (ProductoXML) unmarshaller.unmarshal(new File("src/main/resources/productos.xml"));
           for (Producto item : usuarioXML.getLista()) {
                productoDAOImp.insertarDato(item);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Error, se repite un correo electrónico");
        }
    }

    public void actualizarPreciosPerifericos() {
        try {
            productoDAOImp.actualizarPrecioPerifericos();
            System.out.println("Precios de periféricos actualizados correctamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar precios");
        }
    }

}




