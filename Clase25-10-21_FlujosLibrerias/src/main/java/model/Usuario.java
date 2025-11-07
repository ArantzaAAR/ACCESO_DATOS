package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@AllArgsConstructor
@NoArgsConstructor
@Data //getter y setter
@XmlAccessorType(XmlAccessType.FIELD)//identifica que es un objeto que se va a guardar en XML. Es un tipo CAMPO del XML(nodo)
public class Usuario {
    @XmlAttribute //indico que es dni un atributo del nodo. Se pone delante de cada elemento que queremos que sea atributo
    private String dni;
    private String nombre; //esto es contenido
    private String apellido;
    @XmlElement(name = "direccion")//indico que es un elemento, para que sea otro nodo dentro del contenido del nodo usuario
    private Direccion direccion; //para que sea un nodo con atributos y contenido, creo una clase que uso como objeto en clase usuario

    //Quiero una lista de elementos Listado donde definir los CAMPOS, ATRIBUTOS y CONTENIDO

    //PARA LA LECTURA DE XML a JAVA
public void mostrarDatos(){
    System.out.println("dni = " + dni);
    System.out.println("nombre = " + nombre);
    System.out.println("apellido = " + apellido);
    System.out.println("direccion = " + direccion);
}
}
