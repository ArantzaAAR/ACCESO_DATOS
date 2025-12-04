package controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dao.UsuarioDAOImp;
import model.Usuario;
import model.UsuarioJSON;
import model.UsuarioResponse;
import model.UsuarioXML;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PeticionesController {
    //LA CAPA DE LÓGICA DE NEGOCIO, PERO NO VA CONTRA LA BBDD. Es un filtro que si lo cumple, se realiza la consulta a la BBDD desde DAO
    //EN CASO QUE UN USUARIO SE DÉ DE REGISTRE, INDICAMOS CÓMO ACTUAR ANTE UN ERROR PRODUCIDO POR ELEMENTOS EXTERNOS

    //Para mandar un correo electrónico a todos los elementos. Esto es la capa lógica, pero tengo que ir a DAO para extraer todos los datos de la BBDD
    //como voy a trabajar cosas del DAO, tengo que implementar un objeto DAO
    private UsuarioDAOImp usuarioDAOImp;

    //como lo voy a utilizar dentro, lo pido en el constructor
    public PeticionesController() {
        usuarioDAOImp = new UsuarioDAOImp();//con esto se activa la conexión
    }

    public void insertarUsuario(Usuario usuario) {
        //voy a intentar insertar un usuario, pero si tengo un error en el correo, porque está duplicado
        //pido nuevamente el correo y lo intento agregar otra vez.
        //aquí analizo si ha podido insertarUsuario
        Scanner scanner = new Scanner(System.in);//Scanner para que el usuario me envíe el correo correcto(no repetido)
        boolean fallo = false;//si el fallo es false, NO está metido en la bbdd
        do {//primero trata de hacer la insercion y lo vas a retepir miestras el fallo sea true
            try {
                usuarioDAOImp.insertarDato(usuario);//intento insertar el usuario
                fallo = false;//para que no repitas la inserción del dato que acabas de meter
            } catch (
                    SQLException e) { //si el mail está repetido(porque es clave unica) pido que me escriba el correo correcto
                System.out.println("Error, correo duplicado. Ya está en la ");
                System.out.println("Introduce el nuevo correo electronico");
                fallo = true;//has entrado en fallo, porque has detectado que está duplicado
                String correo = scanner.next();//Hago una instancia para que el usuario me envíe el correo correcto
                usuario.setMail(correo);//leo lo que me ha enviado el usuario
            }
        } while (fallo);//
        System.out.println("Usuario agregado correctamente");//se agrega correctamente
    }

    public void borrarUsuario(int id) {
        int rows = usuarioDAOImp.borrarDatos(id); //creo una variable para que me guarde los usuarios(en caso de borrar muchos)
        if (rows > 1) {   //me dicen varios id y si coincide, borras todos los usuarios
            System.out.println("Usuarios borrados correctamente");
        } else if (rows == 1) { //me dices un id y si coincide, lo borras
            System.out.println("Usuario borrado correctamente");
        } else if (rows == 0) {
            System.out.println("No se ha encontrado usuario con ese id");
        }
        System.out.println("Fallo en el proceso");
        System.out.println("Dime el id del usuario que quieres borrar");
    }

    public void listarUsuarios() {
        System.out.println("¿Quieres listar todos los usuarios o solo alguno?"); //se mete lógica
        //en caso que no
        for (Usuario item : usuarioDAOImp.obtenerListaDatos()) {
            item.mostrarDatos();
        }
    }

    public void importarJSON() {
        ObjectMapper mapper = new ObjectMapper();//necesito obtener la respuesta desde JSON. Coge la respuesta, pasar URL, pasar los datos y traducirla
        try {
            URL url = new URL("https://dummyjson.com/users"); //tengo que pasar la página de la que va a tomar los datos. Da error, la página puede estar mal creada
            UsuarioResponse response = mapper.readValue(url, UsuarioResponse.class);//el mapeador lo lee y con la clase UsuarioResponse traduce
            for (UsuarioJSON item : response.getUsers()) {//recorro la lista de UsuarioJSON y que la variable de UsurarioResponse lo traduzca
                    // int =telefono = Integer.parseInt(item.getPhone().replaceAll(" ", ""), replaceAll("-", ""), replaceAll("\\+", ""));
                    // como phone está almacenado como String, tengo que transformarlo y cambiar los espacios por nadareplaceAll(" ", ""), los guines por nadareplaceAll("-", "") y
                    // mas por nadareplaceAll("\\+", "")+  lo puede entender como una regularExpresion, por lo que escapo el caracter con \\.
                    // Escapar es indicar que lo trate como caracter.
                Usuario usuario = new Usuario(item.getFirstName(), item.getEmail(), item.getAge(), 2); //tengo el usuario definido
                usuarioDAOImp.insertarDato(usuario);//lo tenfo que insertar, por lo que aprovecho el métod que ya tengo
            }
        } catch (MalformedURLException e) {
            System.out.println("URL mal creada. Servidor erróneo");
        } catch (StreamReadException | DatabindException | SQLException e) {//colapso los errores, porque están relacionados
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportacionXML(){
        JAXBContext context = null;//primero creamos el contexto y lo dejo a nulo para poder tratar en otro momento si lo considero necesario
        Marshaller marshaller; // Es como un mapeo, pero que escribe
        List<Usuario> lista = usuarioDAOImp.obtenerListaDatos();//esta es la lista de usuarios que quiero guardar en el XML
        UsuarioXML usuarioXML = new UsuarioXML();//me creo el objeto general
        usuarioXML.setLista(lista);//lo lleno con la lista que he obtenido

        try {
            context =JAXBContext.newInstance(UsuarioXML.class);//saco la instancia del contexto y tengo que indicar quién quieres obtener los datos. Da error
            marshaller = context.createMarshaller();//creo una especie de mapeo
            marshaller.marshal(usuarioXML, new File("src/main/java/controller/usuarios.xml"));//el mapeo lo va a hacer de la lista que he obtenido e indico dónde guardar
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

   public void importarXML(){
        JAXBContext context;  //creo el contexto
        Unmarshaller unmarshaller;//Es como un decodificador que crea la traducción a través del contexto

        try {
            context = JAXBContext.newInstance(UsuarioXML.class);//inicializo el contexto
            unmarshaller = context.createUnmarshaller();//con unmashaller me crea la traducción a través del contexto
            UsuarioXML usuarioXML= (UsuarioXML) unmarshaller.unmarshal(new File("src/main/java/controller/usuarios.xml"));//obtengo un objeto de tipo
            // UsuarioXML a través de unmasharller con el métod unmarshal y traduce el fichero usuarios.xml del que quiero hacer la traducción
            for (Usuario item : usuarioXML.getLista()) {//de usuarioXML obtengo una lista que recorro con foreach y con usuarioDAOImp inserto el dato item
                usuarioDAOImp.insertarDato(item);
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Error, se repite un correo electrónico");
        }
   }

    public void exportarJSON() {
        ObjectMapper mapper = new ObjectMapper();//necesito obtener la respuesta desde JSON. Coge la respuesta, pasar URL, pasar los datos y traducirla
        ObjectWriter response = mapper.writerFor(UsuarioResponse.class);//Tienes el elemento response y escribe lo que hay en una clase concreta
        try {
            response.createGenerator(new File("src/main/java/controller/usuarios.json"), //escribes todos los elementos en el fichero que te indico
                    JsonEncoding.UTF8);//Te paso un codificador para que traduzca los datos
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


