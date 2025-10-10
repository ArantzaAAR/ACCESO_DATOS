import model.Usuario;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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


    //METODO PARA MOSTRAR FiCHEROS Y DIRECTORIOS DE FORMA RECURSIVA O RECURRENTE
    //SI CONOZCO NOMBRE O RUTA
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
        //La ruta no puede ser sobre C: porque hay archivos en los que no se tienen permisos de acceso
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

    //ESCRITURA CON SALTO LÍNEA IMPLÍCITA
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

    //EXPORTAR O ENVIAR LISTA DE DATOS A UN CVS
    public void exportarUsuario(String path) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();//Creamos los objetos
        listaUsuarios.add(new Usuario(1, "Arantza1", "Alcázar1", "1234A", "arantza@gmail.com"));
        listaUsuarios.add(new Usuario(2, "Arantza2", "Alcázar2", "1234A", "arantza@gmail.com"));
        listaUsuarios.add(new Usuario(3, "Arantza3", "Alcázar3", "1234A", "arantza@gmail.com"));
        listaUsuarios.add(new Usuario(4, "Arantza4", "Alcázar4", "1234A", "arantza@gmail.com"));
        listaUsuarios.add(new Usuario(5, "Arantza5", "Alcázar5", "1234A", "arantza@gmail.com"));

        File file = new File(path); //creo el fichero donde lo va a almacenar
        PrintWriter printWriter = null; //creo el printWrtier para que lo almacene con salto de línea

        try {//el objeto me lo llevo a un texto plano.txt
            printWriter = new PrintWriter(new FileWriter(file)); //lo inicializo y como quiero anexar cosas, añado append
            printWriter.println("id, nombre, apellido, dni, correo"); //los escribimos con esta estructura
            for (Usuario usuario : listaUsuarios) { //recorro la lista con el ArrayList
                printWriter.println(usuario);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }

    //METODO PARA IMPORTAR  O LEER UNA LISTA DE DATOS DE UN CSV
    //se hace con BufferedReader
    public void importarCsv(String path) {
        File file = new File(path);
        BufferedReader br = null;
        ArrayList<Usuario> listado = new ArrayList<>(); //creo la variable listado para importar el ArrayList String
        try {
            br = new BufferedReader(new FileReader(file));
            String linea = br.readLine();
            while((linea = br.readLine()) !=null) {
                String[] datos = linea.split(","); //split divide un String en partes usando la coma para devolver array String
                listado.add(new Usuario(Integer.parseInt(datos[0]), //tengo que parsear a Integer, porque pido dato String Array
                        datos[1],
                        datos[2],
                        datos[3],
                        datos[4]));
            }
            System.out.println("Importados con éxito los " + listado.size() + " usuarios"); //para que me muestre el número total de usuarios en listado
            //En un ArrayList size es el tamaño ->5 usuarios, pero coge de la posición 0 a 4
            //listado.getFirst(0)
            //listado.getLast(4)
            System.out.println("El último usuario tiene id: " + listado.getLast().getId());//para que me muestre el id del último usuario que he leído.
            mostrarUsuariosCsv(listado);
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el fichero");
        } catch (IOException e) {
            System.out.println("Error en la lectura del fichero");;
        } finally {
            try {
                br.close();
            } catch (IOException | NullPointerException e) {
                System.out.println("Error en el cerrado");
            }
        }
    }

    //METODO PARA LEER LE LISTADO ARRAYLIST QUE HE IMPORTADO
    //lo hago privado para que solo lo pueda utilizar el método importarCsv
    private void mostrarUsuariosCsv(ArrayList<Usuario> lista){
        for (Usuario usuario: lista) {
            System.out.println(usuario);
        }
    }
    

    //METODO DE LECTURA PARA DESCIFRADO DE MENSAJE
    public void lecturaFichero(String path) {
        File file = new File(path); //creo el archivo con el path que existe
        FileReader reader = null; //creo el FileReader e igualo a nulo para cerrarlo

        try {
            reader = new FileReader(file); //pongo en modo lectura el reader
            //para que continue con la lectura hasta que se termine, cuando encuentre un -1
            int lectorCodigo = 0;
            while ((lectorCodigo = reader.read()) != -1) {
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

    //ESCRIBIR OBJETO .OBJ o .DAT
    public void escribirObjeto(String path) {
        File file = new File(path);
        /* Si no existe el FIle, se crea
        if (!file.exist) {
            createNewFile();
            }
         */
        Scanner lector = new Scanner(System.in);//5- para leer el fichero
        FileOutputStream fos = null;//2- Creo el flujo de objeto. Igualo a nulo para tratar las excepciones
        ObjectOutputStream oos = null; // 1- quiero hacer escritura. Igualo a nulo para tratar las excepciones

        /*
        esta estructura no falla, pero es muy largo
        //para que no falle file, se mete en un do while. DO, pero ha fallado la creación del fichero, confirmo que quiero crearlo, lo crea y WHILE vuelve a ejecutarlo
        //si digo que no se va directo a WHILE y no lo crea
        boolean fallo = false; //creo e inicializo fallo en false
        do {
            try {
                fos = new FileOutputStream(file); //3- inicializo e indico el fichero que quiero poner en flujo saliente. Da un posible error, meto try/cacth
            } catch (FileNotFoundException e) {
                System.out.println("El fichero no existe. ¿Quieres crearlo?"); //4- introduzco el mensaje que quiero que muestre
                boolean crear = lector.nextBoolean();//6- lee el literal true o false
                if (crear) {
                    fallo = true; // si el fichero no existe y quiero crearlo, lo igualo a true
                    try {//da error porque no tienes permisos para crear fichero
                        file.createNewFile();//7- crear el fichero
                    } catch (IOException ex) {
                        System.out.println("No tienes permisos para crear ficheros");
                        ;
                    }
                }
            }
        }while (fallo);
        */

        //METODO SIMPLIFICADO PARA CREAR UN FICHERO CON OBJETOS DANDO POR HECHO QUE EXISTE EL FICHERO
        try {
            fos = new FileOutputStream(file);//1- inicializo e indico el fichero que quiero poner en flujo saliente. Da un posible error, meto try/cacth
            oos = new ObjectOutputStream(fos);//indico que el fichero fos quiero ponerlo en modo salida de  objeto. Añado clausula catch. No permiso
            oos.writeObject((new Usuario(1, "Borja", "Martin", "borja@unir.com", "1234")));
            //creo el objeto en oos con writeObject pero tengo otras opciones en write. Se puede hacer ArrayList para muchos objetos
        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe");
        } catch (IOException e) {
            System.out.println("No tienes permiso de escritura de ficheros");
            ;
        }finally {
            try {
                oos.close(); //siempre tengo que cerrar la operacion,
            } catch (IOException | NullPointerException e) {// IOException podría fallar el cerrado - NullPointerException no se ha podido inicializar
                System.out.println("Error en el cerrado del fichero");
            }
        }
    }

    //PARA LEER UN OBJETO DE UN FICHERO
    public void leerObjeto(String path){
        File file = new File(path);

        FileInputStream fis = null;//2- Creo el flujo de objeto. Igualo a nulo para tratar las excepciones
        ObjectInputStream ois = null; // 1- quiero hacer escritura. Igualo a nulo para tratar las excepciones

        try {
            fis = new FileInputStream(file); //3- inicializo fis
            ois = new ObjectInputStream(fis); //5- //indico que el fichero fis quiero ponerlo en modo escritura de  objeto. Añado clausula catch. No permiso
            Usuario usuario = (Usuario) ois.readObject();//6- pongo en modo lectura el objeto, pero da error de clase de lectura. Hay que castearlo
            System.out.println(usuario.getNombre());
            System.out.println(usuario.getCorreo());
        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra"); //4- incluyo mensaje de Excepcion
        } catch (IOException e) {
            System.out.println("No tienes permisos de lectura");//5
        } catch (ClassNotFoundException | ClassCastException e) {//6 e incluyo la excepcion de ClassCastException
            System.out.println("Errror en la clase de lectura");
        } finally {//5- cierro el flujo
            try {
                ois.close();//siempre tengo que cerrar la operacion,
            } catch (IOException | NullPointerException e) {// incluyo el NullPointer  no se ha podido inicializar
                System.out.println("Error en el cerrado");
            }
        }

    }

    //PARA LEER UN ARRAYLIST
    /*
     public void leerObjeto(String path){
        File file = new File(path);

        FileInputStream fis = null;//2- Creo el flujo de objeto. Igualo a nulo para tratar las excepciones
        ObjectInputStream ois = null; // 1- quiero hacer escritura. Igualo a nulo para tratar las excepciones

        try {
            fis = new FileInputStream(file); //3- inicializo fis
            ois = new ObjectInputStream(fis); //5- //indico que el fichero fis quiero ponerlo en modo escritura de  objeto. Añado clausula catch. No permiso
            Usuario usuario = null; //CREAR UN USUARIO

            while (usuario =(Usuario) ois.readObject()) !=null{
                System.out.println(usuario.getNombre());
                System.out.println(usuario.getCorreo());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, el fichero no se encuentra"); //4- incluyo mensaje de Excepcion
        } catch (IOException e) {
            System.out.println("No tienes permisos de lectura");//5
        } catch (ClassNotFoundException | ClassCastException e) {//6 e incluyo la excepcion de ClassCastException
            System.out.println("Error en la clase de lectura");
        } finally {//5- cierro el flujo
            try {
                ois.close();//siempre tengo que cerrar la operacion,
            } catch (IOException | NullPointerException e) {// incluyo el NullPointer  no se ha podido inicializar
                System.out.println("Error en el cerrado");
            }
        }

    }
     */
}

//EN EL PROGRAMA, SOLO SI EXISTE EL FICHERO, IMPORTAR EN UN ARRAYLIST TODOS LOS USUARIOS DEL MISMO
//SI EL PROGRAMA CIERRA, SI EL FICHERO EXISTE, ANEXA INFORMACIÓN
//SI EL PROGRAMA CIERRA, EI EL FICHERO NO EXISTE, LO CREA Y GUARDA INFORMACIÓN
