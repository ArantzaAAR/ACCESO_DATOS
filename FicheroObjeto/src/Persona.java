import java.io.Serializable;
import java.rmi.server.UID;

public class Persona implements Serializable {

    private static final Long serialVersionUID = 1L;
    private String nombre, apellido;
    int edad;

    public Persona(String nombre, String apellido, int edad) {
        this.edad = edad;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Persona() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
