package main;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("src\\resources\\escritura.txt");
        //CON ESTE MÉTODO LO SACAMOS EN ASCII Y LO TENEMOS QUE MODIFICAR
        FileReader reader = null;
        //CON ESTE METODO LO LEE DIRECTAMENTE COMO STRING
        BufferedReader bufferedReader = null;

        //CODIGO DE FILEREADER
        /*try {
            reader = new FileReader(file);
            int lectura = -1;

            while ((lectura = reader.read()) != -1) {
                //PARA CONVERTIR EL CÓDIGO ASCII A SU CARACTER REAL
                System.out.println(Character.toChars(lectura));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }*/

        //CODIGO BUFFEREDREADER
        try {
            reader = new FileReader(file);
            bufferedReader = new BufferedReader(reader);
            String lectura = null;

            while ((lectura = bufferedReader.readLine()) != null) {
                //PARA CONVERTIR EL CÓDIGO ASCII A SU CARACTER REAL
                String[ ] palabras = lectura.split( " ");
                for(String item: palabras) {
                    System.out.println(Character.toChars(Integer.parseInt(item)));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


