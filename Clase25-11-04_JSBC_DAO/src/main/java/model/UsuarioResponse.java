package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)//le pido que ignore los otros campos que no he incluido en UsuarioJSON
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    private List<UsuarioJSON> users;

    private String firstName;
    private String mail;
    private String age;
}
