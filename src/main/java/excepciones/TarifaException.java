package excepciones;

public class TarifaException extends Exception {
    public TarifaException(){
        super("Error: Tarifa debe contener un valor positivo.");
    }
}
