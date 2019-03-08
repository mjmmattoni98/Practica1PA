package empresaTelefonia;


import excepciones.TarifaException;

public class Tarifa {
    private double tarifa; //Euros por minuto

    public Tarifa(double tarifa) throws TarifaException {
        if(this.tarifa<0) throw new TarifaException();
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
