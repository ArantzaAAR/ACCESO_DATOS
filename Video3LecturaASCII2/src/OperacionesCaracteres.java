import java.io.*;

public class OperacionesCaracteres {
    public void leerFicheroASCII(File file) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            //ME DEVUELVE LO QUE ESTÁ ESCRITO EN EL TXT (Ejemplo)
            // String linea = bufferedReader.readLine();
            //System.out.println(linea);

            //ME DEVUELVO EL CONTENIDO DE LA PRIMERA LETRA DEL  TXT EN ASCII
            int codigo = bufferedReader.read();
            System.out.println("Devuelve el contenido de la primera letra");
            System.out.println(codigo);
            System.out.println("-------------------------------------------------");

            //PARA QUE ME SAQUE LA LETRA DEL CÓDIGO
            System.out.println("Devuelve todas las letras y el código ASCII");
            System.out.println(Character.toChars(codigo));

            //AL METERLO EN WHILE ME DEVUELVE TODAS LAS LETRAS CON CÓDIGO ASCII
            ///while ((codigo1 = bufferedReader.read()) > 0) TAMBIÉN SE PUEDE HACER ASÍ
            while ((codigo = bufferedReader.read()) != -1) {
                System.out.println(codigo);
                //IMPRIME EL CÓDIGO ASCII
                System.out.println(Character.toChars(codigo));
            }
            System.out.println("-------------------------------------------------");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ;
        } catch (IOException e) {
            e.printStackTrace();
            ;
        } finally {
            try {
                fileReader.close();
                ;
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                ;
            }

        }

    }

    public void leerFicheroASCII2(File file) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            //ME DEVUELVO EL CONTENIDO DEL PRIMER NUMERO DEL  TXT EN ASCII
            String palabra;

            System.out.println("Pasa de ASCII  A LETRA");
            while ((palabra = bufferedReader.readLine()) != null) {
                System.out.println(palabra);
                //PARA QUE DEVUELVA EL CARACTER ASOCIADO
                int codigoLeido = Integer.valueOf(palabra);
                System.out.println((char) codigoLeido);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
