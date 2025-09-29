import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.io.IOException;

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


    public void mostrarFicherosRecurrentes(String path) {
        //mostrar todos los ficheros y hacerlo de una forma recursiva.
        //La ruta no puede ser sobre C: porque hay archivos en los que no se tienen permisos
        File file = new File(path);

        System.out.println("Vamos a trabajar con los ficheros de forma recurrente");
        if (file.isDirectory()) {
            File[] fichero = file.listFiles();
            if (fichero != null) {
                for (File ruta : fichero) {
                    System.out.println(ruta.getAbsolutePath());
                    if (ruta.isDirectory()) {
                        System.out.println(ruta.getAbsolutePath());
                    }
                }
            } else {
                System.out.println("No se puede mostrar el contenido");
            }
        }else {
            System.out.println("La ruta no es correcta");
        }
    }

    //EJEMPLO PARA ESCRIBIR FICHERO
    public void escribirFichero(String path) {
        File file = new File(path);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            System.out.println("No puedes realizar la escritura");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}