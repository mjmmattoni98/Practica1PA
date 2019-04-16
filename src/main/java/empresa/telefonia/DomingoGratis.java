package empresa.telefonia;


import java.time.DayOfWeek;

public class DomingoGratis extends TarifaDescuento {

    public DomingoGratis(Tarifa tarifa){
        super(tarifa, 0.0, fecha -> fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }

}
