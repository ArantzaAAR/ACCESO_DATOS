import model.Usuario;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class Operaciones {

    //EJEMPLO PARA CREAR Y TRABAJAR CON ARCHIVO
    public void informacionFichero(String path) {
        File file = new File(path); //fichero lógico

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("No se ha podido crear el fichero por problemas de OI" + e.getCause());
            }
        } else {
            //obtener su información
            System.out.println(file.exists());
            System.out.println(file.isFile());
            System.out.println(file.canWrite());
            System.out.println(file.canRead());
            System.out.println(file.canExecute());
            System.out.println(file.isDirectory());
        }

    }

    //EJEMPLO PARA CREAR DIRECTORIO
    public void informacionDirectorio(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            System.out.println("Vamos a trabajar con directorios");
            System.out.println("La ruta de este directorio es " + file.getAbsolutePath());
            //cuantos ficheros tengo dentro del directorio
            //! opcion Saca la lista de los nombres que hay en la carperta
            String[] nombreFicheros = file.list();
            /*for(String item : nombreFicheros) {
                //para que no me saque los archivos ocultos
                if( item.charAt(0) != '.'){
                    //Lo meto en un if, porque me saca todos los archivos, hasta los ocultos
                    System.out.println(item);
                }*/
            File[] ficheros = file.listFiles();

            for (File item : ficheros) {
                if (!item.isHidden()) {
                    System.out.println(item.getAbsolutePath());
                }
            }
        } else if (!file.exists()) {

            //Solo crea 1 directorio
            //file.mkdir();
            //Crea varios directorios
            file.mkdirs();
        }

    }


    //METODO PARA MOSTRAR FiCHEROS Y DIRECTORIOS DE FORMA RECURSIVA O RECURRENTE SI CONOZCO NOMBRE O RUTA
    public void mostrarFicherosRecurrentes(File file) {
        //mostrar todos los ficheros y hacerlo de una forma recursiva.
        //La ruta no puede ser sobre C: porque hay archivos en los que no se tienen permisos
        System.out.println("El nombre de la carpeta a analizar es: " + file.getName());
        if (file.isDirectory()) {
            for (File item : file.listFiles()) {
                mostrarFicherosRecurrentes(item);
            }
        }
    }

    //METODO PARA MOSTRAR FICHEROS Y DIRECTORIOS RECURSIVA O RECURRENTE NO CONOZCO RUTA O NOMBRE
    public void mostrarFicherosRecurrentes(String path) {
        //mostrar todos los ficheros y hacerlo de una forma recursiva.
        //La ruta no puede ser sobre C: porque hay archivos en los que no se tienen permisos
        File file = new File(path);
        System.out.println("El nombre de la carpeta a analizar es: " + file.getName());
        if (file.isDirectory()) {
            for (File item : file.listFiles()) {
                mostrarFicherosRecurrentes(item.getPath());
            }
        }
    }

    //EJEMPLO PARA SOBREESCRIBIR EN EL FICHERO
    public void escribirFichero(String path) {
        File file = new File(path);
        FileWriter fileWriter = null; //pongo el fichero fileWriter igualado a nulo para cerrar sí o sí

        try {
            fileWriter = new FileWriter(file); //pongo el fichero en modo escritor que sobreescribe
            fileWriter.write("Esto es un ejemplo de escritura para el fichero de clase"); //meto mensaje que quiero que escriba
        } catch (IOException e) {
            System.out.println("No puedes realizar la escritura");
        } finally { //cierro fileWriter
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //EJEMPLO PARA NO SOBREESCRIBIR FICHERO
    public void noSobreescribirFichero(String path) {
        File file = new File(path);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file, true); //le meto en modo append
            fileWriter.write("Esto es un ejemplo de escritura para el fichero de clase"); //meto mensaje que quiero que escriba
        } catch (IOException e) {
            System.out.println("\nNo puedes realizar la escritura"); //pongo un salto de línea, para que anexe y no sobreescriba
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //EJEMPLO PARA CIFRAR MENSAJE
    public void cifrarMensaje(String path) {
        File file = new File(path);
        FileWriter fileWriter = null; //pongo el fichero fileWriter igualado a nulo para cerrar sí o sí
        String mensajeCifrar = "Este mensaje es oculto y sera el enunciado del examen final";

        try {
            fileWriter = new FileWriter(file);
            for (int i = 0; i < mensajeCifrar.length(); i++) { //me tiene que recorrer todas las letras
                char letra = mensajeCifrar.charAt(i); //lees letra a letra
                fileWriter.write((int) letra * 5); //quiero que me escriba el código ASCII, por lo que hago un casteo a la letra para que lo pase a número y le encripto multiplicando 5
            } //el 5 se el llama la fase de cifrado
        } catch (IOException e) {
            System.out.println("No puedes realizar la escritura");
        } finally { //cierro fileWriter
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //ESCRITURA CON ALTO LÍNEA IMPLÍCITA
    public void escrituraSuperior(String path) {
        File file = new File(path);
        //FileWriter fileWriter = null; no es necesario incluir esta línea por el constructor implícito
        PrintWriter printWriter = null;

        try {
            printWriter = new PrintWriter(new FileWriter(file)); //constructor implícito para ahorrar código
            printWriter.println("Esto es un ejemplo con printWriter");
            printWriter.println("Esto es una nueva línea");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {//cierra el flujo de datos
            try {//printWriter no requiere de excepciones, pero como puede dar un fallo porque no haya podido crear el file
                printWriter.close(); //como es posible que dé error
            } catch (NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }

    //ENVIAR LISTA DE DATOS A UN CVS
    public void exportarUsuario(String path){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.add(new Usuario(1, "Arantza1", "Alcázar1", "1234A", "arantza@gmail.com"));
        listaUsuarios.add(new Usuario(2, "Arantza2", "Alcázar2", "1234A", "arantza@gmail.com"));
        listaUsuarios.add(new Usuario(3, "Arantza3", "Alcázar3", "1234A", "arantza@gmail.com"));
        listaUsuarios.add(new Usuario(4, "Arantza4", "Alcázar4", "1234A", "arantza@gmail.com"));
        listaUsuarios.add(new Usuario(5, "Arantza5", "Alcázar5", "1234A", "arantza@gmail.com"));

        File file =  new File(path); //creo el fichero donde lo va a almacenar
        PrintWriter printWriter = null; //creo el printWrtier para que lo almacene con salto de línea

        try {
            printWriter = new PrintWriter(new FileWriter(file, true)); //lo inicializo y como quiero anexar cosas, añado append
            printWriter.println("id, nombre, apellido, dni, correo");
            for (Usuario usuario : listaUsuarios) {
                printWriter.println(usuario);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            printWriter.close();;
        }
    }

    //METODO DE LECTURA PARA DESCIFRADO DE MENSAJE
    public void  lecturaFichero (String path) {
        File file = new File(path); //creo el archivo con el path que existe
        FileReader reader = null; //creo el FileReader e igualo a nulo para cerrarlo

        try {
            reader = new FileReader(file); //pongo en modo lectura el reader
            //para que continue con la lectura hasta que se termine, cuando encuentre un -1
            int lectorCodigo = 0;
            while((lectorCodigo = reader.read()) != -1) {
                System.out.print((char) (lectorCodigo / 5));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try { //no es tan necesario cerrarlo, porque no hay nada que se rompa. Se cierra la lectura y ya está
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
