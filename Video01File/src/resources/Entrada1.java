package resources;

import java.io.File;
import java.io.IOException;


// TODOS ESTOS MÉTODOS TE PERMITEN ACCEDER Y TRABAJAR SIN TENER QUE ESCRIBIR O LEER,
//SOLAMENTE ACCEDIENDO A SUS PROPIEDADES
public class Entrada1 {
    public static void main(String[] args) {
        File f =new File("src/resources/");
        System.out.println(f.exists());
        System.out.println(f.getName());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getAbsoluteFile());
        System.out.println(f.canRead());
        System.out.println(f.getParent());

        //condicional en caso que no exista
        if (!f.exists()) {
            try {
                //sirve para crear un nuevo fichero
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
                //En este caso no crea el documento porque existe. Si tuviera otro nombre, lo crea
            }
        }

        /*
        La lista me va a devolver un array de String. Se trabaja con un for each que es un doc.
        Como doc es un file me puede devolver lo que quiera
        Si apunta a un directorio, me va a devolver la lista de documentos en formato String
        */
        String[] listaDocumentos = f.list();
        for (String doc : listaDocumentos){
            System.out.println(doc);
        }

        /*
        En este caso le da una lista de Files. Se trabaja con un for each.
        En este caso nos pueda dar más datos
        */

        File [] listaFicheros = f.listFiles();
        for (File file :listaFicheros){
            System.out.println(file.getName());
            System.out.println(file.getAbsoluteFile());
        }

        File directorio = new File( "src/resources/consola");
        directorio.mkdir();;
    }
}
