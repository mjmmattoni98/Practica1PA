package empresa.telefonia;


public class TarifaBasica extends Tarifa {

    public TarifaBasica() {
        super(fecha -> true, 15.0);
    }

    @Override
    public String toString(){
        return "Tarifa básica: " + 15.0 + " céntimos.";
    }

}
