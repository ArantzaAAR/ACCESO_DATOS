package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private int id;
    private String nombre, mail;
    private int telefono;
    private int idPerfil;

    //HAGO UN CONSTRUCTOR PARA LANZAR A LA  DDBB SIN IDENTIFICADOR
    public Usuario(String nombre, String mail, int telefono, int idPerfil) {
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }
}
