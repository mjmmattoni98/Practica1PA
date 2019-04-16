package excepciones;

public class TarifaException extends Exception {
    public TarifaException(){
        super("Error: TarifaBasica debe contener un valor positivo.");
    }
}
