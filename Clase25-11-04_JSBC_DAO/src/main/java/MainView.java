import controller.PeticionesController;
import model.Usuario;

public class MainView {
    //VIEW -> LA INTERACCION CON EL USUARIO
    public static void main(String[] args) {
        PeticionesController peticionesController = new PeticionesController();
        peticionesController.insertarUsuario(new Usuario("Eugenia", "eugenia@gmail.com", 456, 2));

    }




}
