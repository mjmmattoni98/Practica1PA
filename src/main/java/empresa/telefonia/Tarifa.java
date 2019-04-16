package empresa.telefonia;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.function.Predicate;

public abstract class Tarifa implements Serializable {
    private double tarifa; //Céntimos por minuto.
    private Predicate<LocalDateTime> aplicarDescuento;

    public Tarifa(){
        super();
    }

    public Tarifa(Predicate<LocalDateTime> aplicarDescuento, double tarifa){
        this.aplicarDescuento = aplicarDescuento;
        this.tarifa = tarifa;
    }

    public Tarifa withTarifa(double tarifa){
        this.tarifa = tarifa;
        return this;
    }

    public double descuento(LocalDateTime fecha){
        return aplicarDescuento.test(fecha) ? tarifa : 100000.0;
    }

    public double getTarifa(LocalDateTime fecha){
        return tarifa;
    }

    @Override
    public String toString(){
        return tarifa + "";
    }

}
