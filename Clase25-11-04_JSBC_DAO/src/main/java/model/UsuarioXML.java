package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)//indico que es de tipo field
@XmlRootElement(name = "usuarios")//indico el elemento root
public class UsuarioXML {
    @XmlElement(name = "usuario")//indico que va a tener elementos usuario
    private List<Usuario> lista;
}
