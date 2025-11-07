package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data//te agrupa los getter&setter
public class Pelicula {
    private String titulo, director;
    private Actor[] actores;
    private String genero;
    private int anio;
    private double calificacion;
    private String cartel;
}
