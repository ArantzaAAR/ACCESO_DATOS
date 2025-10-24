package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data//Solo getter setter
@JsonIgnoreProperties(ignoreUnknown = true)//para que ignore los atributos que no he seleccionado

public class ProductJson {

    //necesita todos los atributos que quiero traer con el MISMO TIPO Y NOMBRE
    private long id;
    private String title;
    private String description;
    private double price;

    //hago toString para que devuelva la información estructurada en consola
    @Override
    public String toString() {
        return "ProductJson{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
