package almacenamiento;

import java.io.*;

public class Entrada {
    public static void main(String[] args) throws IOException {

        //METER LA RUTA RELATIVA
        File file = new File("D:src\\almacenamiento\\documentos\\escritura.txt");

        //PARA TRABAJAR CON LOS FLUJOS DE DATOS
        //InputStream IinputStream;
        //OutputStream outputStream;

        //PARA TRABAJAR CON FICHEROS
        FileReader fileReader = null; //se iguala a nulo para en try/catch hacer cerrado

        try {
            //lee caracter a caracter y me devuelve un número correspondiente a la tabla ASCII
            fileReader = new FileReader(file);
            //como me devuelve un número lo igual
            int lectura = -1;

            //mientras la lectura sea igual  y distinto de -1
            while ((lectura = fileReader.read()) != -1)  {

                //me saca todos los números en tabla ASCII
                System.out.println(lectura);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

