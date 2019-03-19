package excepciones;

public class NIFException extends Exception{
    public NIFException(){
        super("Error: El NIF tiene que tener 8 numeros y una letra.");
    }
}
