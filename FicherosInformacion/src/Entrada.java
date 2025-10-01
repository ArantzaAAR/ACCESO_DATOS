import java.io.File;

public class Entrada {

    public static void main(String[] args) {
        Operaciones operaciones= new Operaciones();
        //operaciones.informacionFichero (  "src/resources/ejemplo1.txt" );
        //operaciones.mostrarFicherosRecurrentes( new File( "Acceso_Datos\\Ejercicios_clase\\FicherosInformacion"));
        //operaciones.informacionDirectorio (  "src/resources/cosas/para/analizar" );
        // operaciones.escribirFichero (  "src/resources/escritura.txt" );
        //operaciones.cifrarMensaje("src/resources/escritura.txt");
        //operaciones.escrituraSuperior("src/resources/escrituraSuperior.txt");
        //operaciones.exportarUsuario("src/resources/Usuarios.csv");
        operaciones.lecturaFichero("src/resources/escritura.txt");
    }
}
