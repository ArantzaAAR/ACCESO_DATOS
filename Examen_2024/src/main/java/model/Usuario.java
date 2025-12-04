package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private static final long serialVersionUID = 20241129L;

    private int id;
    private String nombre, apellido, correo, pass;

    public Usuario(String nombre, String apellido, String correo, String pass) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.pass = pass;
    }

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}

