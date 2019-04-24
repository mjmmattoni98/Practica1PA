package empresa.telefonia;


import excepciones.TarifaException;

import java.io.Serializable;

public class TarifaBasica extends Tarifa implements Serializable {

    public TarifaBasica(double tarifa) {
        super(fecha -> true, tarifa);
    }

    @Override
    public String toString(){
        return "\nTarifa básica: " + super.toString() + " céntimos.";
    }

}
