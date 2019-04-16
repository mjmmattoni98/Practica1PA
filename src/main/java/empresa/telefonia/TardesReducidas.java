package empresa.telefonia;


public class TardesReducidas extends TarifaDescuento {

    public TardesReducidas(Tarifa tarifa, double nuevaTarifa){
        super(tarifa, nuevaTarifa, fecha -> fecha.getHour() >= 16 && fecha.getHour() <= 20);
    }


}
