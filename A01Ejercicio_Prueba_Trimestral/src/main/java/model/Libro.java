package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {

    private int id;
    private String titulo, autor, isbn;
    private int ano;
    private int idUsuario;

    //HAGO UN CONSTRUCTOR PARA LANZAR A LA  DDBB SIN IDENTIFICADOR
    public Libro(String titulo, String autor, int ano, String isbn, int idUsuario) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.isbn = isbn;
        this.idUsuario = idUsuario;
    }

    public void mostrarDatos(){
        System.out.println("titulo = " + titulo);
        System.out.println("autor = " + autor);
        System.out.println("año = " + ano);
        System.out.println("isbn = " +isbn);
        System.out.println("idUsuario = " + idUsuario);
    }
}
