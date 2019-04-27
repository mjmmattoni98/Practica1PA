package empresa.telefonia;


import excepciones.TarifaException;
import utilities.SerializablePredicate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.Predicate;

public abstract class Tarifa implements Serializable {
    private double tarifa; //Céntimos por minuto.
    private SerializablePredicate<LocalDateTime> aplicarDescuento;

    public Tarifa(){
        super();
    }

    public Tarifa(SerializablePredicate<LocalDateTime> aplicarDescuento, double tarifa) {
        this.aplicarDescuento = aplicarDescuento;
        this.tarifa = tarifa;
    }

    public Tarifa withTarifa(double tarifa){
        this.tarifa = tarifa;
        return this;
    }

    public double getTarifa(LocalDateTime fecha){
        return aplicarDescuento.test(fecha) ? tarifa : 100000.0;
    }

    public abstract String description();

    @Override
    public String toString(){
        return tarifa + "";
    }

}
