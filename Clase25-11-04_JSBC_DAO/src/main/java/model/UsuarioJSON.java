package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)//como no me voy a exportar todos los campos, le pido que ignore los que no incluya
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioJSON {
    private String firstName;
    private String email;
    //private String phone; no trabajamos con este dato, porque al transformarlo en la query es un long y me da problemas en la importación en este caso
    private int age;

}
