import java.io.*;

public class Entrada {

    public static void main(String[] args) {
        OperacionesCaracteres operacionesCaracteres = new OperacionesCaracteres();
        operacionesCaracteres.leerFicheroASCII(new File( "src\\consola\\ASCIILetra.txt"));
        operacionesCaracteres.leerFicheroASCII2((new File( "src\\consola\\ASCIINum.txt")));
    }

}
