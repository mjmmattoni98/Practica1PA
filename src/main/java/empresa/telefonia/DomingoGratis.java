package empresa.telefonia;


import excepciones.TarifaException;

import java.time.DayOfWeek;

public class DomingoGratis extends TarifaDescuento {

    public DomingoGratis(Tarifa tarifa) throws TarifaException {
        super(tarifa, 0.0, fecha -> fecha.getDayOfWeek().equals(DayOfWeek.SUNDAY));
    }

    @Override
    public String toString(){
        return "\nDomingos gratis." + super.toString();
    }
}
