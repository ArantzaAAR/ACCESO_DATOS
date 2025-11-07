package model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data //solo necesito getter y setter para inicializar y dar valor. No constructores definidos, lo tengo que crear yo
@XmlRootElement (name = "usuarios") //creo el NodoRoot
@XmlAccessorType (XmlAccessType.FIELD) //indica que formará parte del XML

public class Listado {
    @XmlElement (name = "usuario")//quiero que se repita para que me cree los NODOS dentro del nodo root usuarios
    //Quiero una lista de elementos Listado donde definir los CAMPOS, ATRIBUTOS y CONTENIDO
    private List<Usuario> listado;


    public Listado() { //creo el constructor para inicializar el ArrayList de listado
        this.listado = new ArrayList<>();
    }


}
