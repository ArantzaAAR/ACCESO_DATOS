package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //para que no coja las propiedades que no necesito
public class Drink {
    private String idDrink,strDrink,strTags,strCategory,strInstructionsES,strDrinkThumb,strIngredient1,strIngredient2;

}
