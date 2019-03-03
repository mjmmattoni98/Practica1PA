package empresaTelefonia;

public class Tarifa {
    private double tarifa; //Euros por minuto

    public Tarifa(double tarifa){
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
