package empresa.telefonia;


import excepciones.TarifaException;

public class TardesReducidas extends TarifaDescuento {

    public TardesReducidas(Tarifa tarifa, double nuevaTarifa) throws TarifaException {
        super(tarifa, nuevaTarifa, fecha -> fecha.getHour() >= 16 && fecha.getHour() <= 20);
    }

    @Override
    public String toString(){
        return "\nTardes reducidas a " + super.toString() + " céntimos.";
    }
}
