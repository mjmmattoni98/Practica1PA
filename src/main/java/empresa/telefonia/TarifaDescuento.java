package empresa.telefonia;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public abstract class TarifaDescuento extends Tarifa{
    private Tarifa tarifa; //Céntimos por minuto.

    public TarifaDescuento(){
        super();
    }

    public TarifaDescuento(Tarifa tarifa, double nuevaTarifa, Predicate<LocalDateTime> aplicarDescuento){
        super(aplicarDescuento, nuevaTarifa);
        this.tarifa = tarifa;
    }

    @Override
    public double getTarifa(LocalDateTime fecha){
        double miTarifa = tarifa.descuento(fecha);
        double otraTarifa = super.descuento(fecha);
        return miTarifa<otraTarifa?miTarifa:otraTarifa;
    }

    @Override
    public String toString(){
        return tarifa + "";
    }

}
