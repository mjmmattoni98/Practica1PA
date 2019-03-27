package excepciones;


public class PeriodoException extends Exception{
    public PeriodoException(){
        super("Error: la fecha de inicio no puede ser posterior a la fecha de fin.");
    }
}
