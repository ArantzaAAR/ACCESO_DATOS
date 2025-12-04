package dao;

import database.DBConnection;
import database.SchemeDB;
import model.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.crypto.dsig.Transform;
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
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImp implements InterfazDAO<Usuario>, UsuarioDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private Usuario usuarios;

    public UsuarioDAOImp() {
        connection = DBConnection.getConnection();
    }


    @Override
    public boolean insertarDato(Usuario dato) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUE (?,?,?,?)",
                SchemeDB.TAB_NAME,
                SchemeDB.COL_NAME, SchemeDB.COL_MAIL, SchemeDB.COL_PHONE, SchemeDB.COL_PROFILE
        );

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, dato.getNombre());
        preparedStatement.setString(2, dato.getMail());
        preparedStatement.setInt(3, dato.getTelefono());
        preparedStatement.setInt(4, dato.getIdPerfil());
        return preparedStatement.execute();
    }

    @Override
    public ArrayList<Usuario> obtenerListaDatos() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM " + SchemeDB.TAB_NAME);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString(SchemeDB.COL_NAME);
                String mail = resultSet.getString(SchemeDB.COL_MAIL);
                int telefono = resultSet.getInt(SchemeDB.COL_PHONE);
                int idPerfil = resultSet.getInt(SchemeDB.COL_PROFILE);

                listaUsuarios.add(new Usuario(nombre, mail, telefono, idPerfil));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    @Override
    public void actualizarDato(Usuario datoNuevo) throws SQLException {
        String query = String.format("UPDATE INTO %s (%s,%s,%s,%s WHERE (?,?,?,?)",
                SchemeDB.TAB_NAME,
                SchemeDB.COL_NAME, SchemeDB.COL_MAIL, SchemeDB.COL_PHONE, SchemeDB.COL_PROFILE
        );
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, datoNuevo.getNombre());
        preparedStatement.setString(2, datoNuevo.getMail());
        preparedStatement.setInt(3, datoNuevo.getTelefono());
        preparedStatement.setInt(4, datoNuevo.getIdPerfil());

        preparedStatement.executeUpdate();
    }

    @Override
    public int borrarDatos(int id) {
        String query = String.format("DELETE FROM %s WHERE %s=?", SchemeDB.TAB_NAME, SchemeDB.COL_ID);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en la ejecución de la query");
        }
        return -1;
    }

    @Override
    public void exportarMySQLaXML() {
        List<Usuario> listaUsuarios = obtenerListaDatos();

        if (listaUsuarios.isEmpty()) {
            System.out.println("El listado está vacío");
        }
        escribirUsuariosAXML(listaUsuarios, "src/main/resources/dasedatos.xml");

    }

    @Override
    public void escribirUsuariosAXML(List<Usuario> usuarios, String rutaArchivo) {

        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.newDocument();

            Element root = document.createElement("usuarios");
            document.appendChild(root);

            for (Usuario item : usuarios) {
                Element nodoUsuario = document.createElement("usuario");
                nodoUsuario.setAttribute("id", String.valueOf(item.getId()));
                nodoUsuario.setAttribute("idPerfil", String.valueOf(item.getIdPerfil()));
                root.appendChild(nodoUsuario);

                Element nodoNombre = document.createElement("nombre");
                nodoNombre.setTextContent(item.getNombre());
                nodoUsuario.appendChild(nodoNombre);

                Element nodoMail = document.createElement("mail");
                nodoMail.setTextContent(item.getMail());
                nodoUsuario.appendChild(nodoMail);

                Element nodoTelefono = document.createElement(("Telefono"));
                nodoTelefono.setTextContent(String.valueOf(item.getTelefono()));
                nodoUsuario.appendChild(nodoTelefono);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();

            File file = new File("src/main/resources/basedatos.xml");

            DOMSource domSource = new DOMSource(document);
            StreamResult result = new StreamResult(file);

            transformer.transform(domSource, result);

        } catch (ParserConfigurationException e) {
            System.out.println("Error en el parseo");
        } catch (TransformerConfigurationException e) {
            System.out.println("Error en la configuración de la transformación del documento");
        } catch (TransformerException e) {
            System.out.println("Error en la transformación del documento");
        }
    }

    @Override
    public void leerXML(String rutaArchivo) {
        File file = new File("src/main/resources/nuevosdatos.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);

            NodeList nodoUsuario = document.getElementsByTagName("usuario");

            for (int i = 0; i < nodoUsuario.getLength(); i++) {
                Element nodo = (Element) nodoUsuario.item(i);

                int id = Integer.parseInt(nodo.getAttribute("id"));
                int idPerfil = Integer.parseInt(nodo.getAttribute("id_perfil"));

                String nombre = nodo.getElementsByTagName("nombre").item(0).getTextContent();
                String mail = nodo.getElementsByTagName("mail").item(0).getTextContent();
                int telefono = Integer.parseInt(nodo.getElementsByTagName("telefono").item(0).getTextContent());

                Usuario usuario = new Usuario(id, nombre, mail, telefono, idPerfil);
                insertarDato(usuario);
            }
            connection.commit();
            System.out.println("Se ha subido los nuevos usuarios correctamente");

        } catch (ParserConfigurationException e) {
            System.out.println("Error en el parseo");
        } catch (IOException e) {
            System.out.println("Error, no tienes permisos de lectura");
        } catch (SAXException e) {
            System.out.println("Error del SAX");
        } catch (SQLException e) {
            System.out.println("Error en la inserción de los usuarios");
        }
    }

    @Override
    public void escribirHTML() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new File("src/main/resources/basedatos.xml"));

            String html = """
                                            <!DOCTYPE html>
                                            <html lang='es'>
                                            <head>
                                                <meta charset='UTF-8'>
                                                <title>Lista de Usuarios</title>
                                                <style>
                                                    table { border-collapse: collapse; width: 100%; }
                                                    th, td { border: 1px solid black; padding: 8px; }
                                                    th { background-color: #f2f2f2; }
                                                </style>
                                            </head>
                                            <body>
                                                <h1>USUARIOS</H1>
                    """;

            NodeList usuarios = document.getElementsByTagName("usuario");

            for (int i = 0; i < usuarios.getLength(); i++) {
                Element usuario = (Element) usuarios.item(i);

                String nombre = usuario.getElementsByTagName("nombre").item(0).getTextContent();
                String mail = usuario.getElementsByTagName("mail").item(0).getTextContent();

                html += "<ul>" +
                        "<li>" + nombre + mail + "</li>" +
                        "</ul>";

                html += "</body></html>";

                FileWriter writer = new FileWriter("src/main/resources/usuariosdatabase.html");
                writer.write(html);
            }
            System.out.println("HTML generado correctamente");
        } catch (ParserConfigurationException e) {
            System.out.println("Error en el parseo");
        } catch (IOException e) {
            System.out.println("Error, no tienes permisos de lectura");
        } catch (SAXException e) {
            System.out.println("Error en la ruta");
        }

    }
}
























/*
    @Override
    public boolean probarConexion() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Conexión a MySQL exitosa!");
            return true;
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            return false;
        }
    }
*/
