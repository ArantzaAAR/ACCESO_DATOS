package database;

public interface SchemaDB {
    /*CREAMOS UNA INTERFACE
     *   metodos -> NO ESCRITOS (vacíos)
     *   atrIbutos -> PUBLICOS FINAL STATIC (son accesibles directamente
     * */

    //al ser estático es accesible y me permite hacer las querys de forma mas sencilla
    String DB_NAME = "peticiones";
    String TAB_NAME = "usuarios";
    String  COL_NAME = "nombre";
    String COL_MAIL = "mail";
    String COL_PHONE = "telefono";
    String COL_PROFILE = "id_perfil";
    String COL_ID = "id";


}
