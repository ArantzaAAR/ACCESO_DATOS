package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD) //es un campo para trabajar con la importación de XML
public class Usuario {

    @XmlAttribute
    private int id;
    private String nombre, mail;
    private int telefono;
    @XmlAttribute
    private int idPerfil;

    //HAGO UN CONSTRUCTOR PARA LANZAR A LA  DDBB SIN IDENTIFICADOR
    public Usuario(String nombre, String mail, int telefono, int idPerfil) {
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
        this.idPerfil = idPerfil;
    }

    public void mostrarDatos(){
        System.out.println("nombre = " + nombre);
        System.out.println("mail = " + mail);
        System.out.println("telefono = " + telefono);
        System.out.println("idPerfil = " + idPerfil);
    }
}
