package empresa.telefonia;

import utilities.SerializablePredicate;

import java.time.LocalDateTime;

public abstract class TarifaDescuento extends Tarifa{
    private Tarifa tarifa; //Céntimos por minuto.

    public TarifaDescuento(){
        super();
    }

    public TarifaDescuento(Tarifa tarifa, double nuevaTarifa, SerializablePredicate<LocalDateTime> aplicarDescuento) {
        super(aplicarDescuento, nuevaTarifa);
        this.tarifa = tarifa;
    }

    @Override
    public double getTarifa(LocalDateTime fecha){
        double miTarifa = tarifa.getTarifa(fecha);
        double otraTarifa = super.getTarifa(fecha);
        return miTarifa<otraTarifa?miTarifa:otraTarifa;
    }

    @Override
    public String description(){
        return tarifa.description();
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
