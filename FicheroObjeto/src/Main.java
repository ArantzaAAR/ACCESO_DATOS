import java.io.*;

public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona("Juan", "Morote", 13);
        //ObjectOutputStream oos = null;
        ObjectInputStream ois =null;

        try {
            //PARA ESCRIBIR UN OBJETO
            //oos = new ObjectOutputStream(new FileOutputStream(new File("src/FicherosObjeto.obj")));
            //oos.writeObject(persona);
            //PARA LEER UN OBJETO
            ois = new ObjectInputStream(new FileInputStream(new File("src/FicherosObjeto.obj")));
            Persona personaLeida = (Persona) ois.readObject();
            System.out.println(personaLeida.getNombre());
            System.out.println(personaLeida.getApellido());
            System.out.println(personaLeida.getEdad());
            //ois puede que no lea el fichero, que no encuentre la clase o que no esté bien casteado
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            throw new RuntimeException(e);
        }finally{
            try {
                //SIEMPRE CERRAR LA OPERACION
                // oos.close();
                ois.close();
            } catch (IOException| NullPointerException e) {
                throw new RuntimeException(e);
            }
        }

    }


}
