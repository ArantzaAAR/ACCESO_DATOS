package model;

import lombok.Data;

import java.util.List;

@Data
public class RespuestaProductosJson {
    //me responde con una lista de la clase ProductJason y llamo a la variable products(igual que el Json)
    private List<ProductJson> products;
    private int total;
    private  int skip;
    private int limit;

}
