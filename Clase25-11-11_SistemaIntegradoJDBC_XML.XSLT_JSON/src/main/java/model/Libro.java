package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {

    private int id;
    private String isbn, titulo, autor, editorial, categoria;
    private int ano_publicacion;
    double precio;
    private int stock = 0;

    public Libro(String isbn, String titulo, String autor, String editorial, int ano_publicacion, String categoria, double precio, int stock) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.categoria = categoria;
        this.ano_publicacion = ano_publicacion;
        this.precio = precio;
        this.stock = stock;
    }

    public void mostrarDatos(){

    }
}
