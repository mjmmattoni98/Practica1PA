package empresa.telefonia;

import java.io.Serializable;

public class TarifaBasica extends Tarifa implements Serializable {

    public TarifaBasica(double tarifa) {
        super(fecha -> true, tarifa);
    }

    @Override
    public String description(){
        return "\nTarifa básica a " + super.toString() + " céntimos.";
    }

    @Override
    public String toString(){
        return super.toString();
    }

}
