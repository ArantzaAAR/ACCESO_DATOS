package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {

    private  int id;
    private String nombre;
    private double precio;
    private String categoria;

    public Producto (String nombre, double precio, String categoria){
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public void mostrarDatos(){
        System.out.println("Id: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Categoría: " + categoria);
        System.out.println("-------------------");
    }
}
