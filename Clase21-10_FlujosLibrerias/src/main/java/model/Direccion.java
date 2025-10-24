package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

//para que sea un nodo con atributos, creo una clase que uso como objeto en clase usuario
@AllArgsConstructor
@NoArgsConstructor
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Direccion {

    private String calle;
    private int codigoPostal;
    private String Ciudad;

}
