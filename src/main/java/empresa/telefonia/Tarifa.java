package empresa.telefonia;


import excepciones.TarifaException;

import java.io.Serializable;

public class Tarifa implements Serializable {
    private double tarifa; //Euros por minuto

    public Tarifa(double tarifa) throws TarifaException {
        if(tarifa<0) throw new TarifaException();
        this.tarifa = tarifa;
    }

    public double getTarifa(){
        return tarifa;
    }

    @Override
    public String toString(){
        return tarifa + "";
    }
}
