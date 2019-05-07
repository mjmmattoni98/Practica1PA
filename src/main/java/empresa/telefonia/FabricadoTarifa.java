package empresa.telefonia;

public class FabricadoTarifa implements FabricaTarifa {

    @Override
    public Tarifa getTarifaTardesReducidas(Tarifa tarifa, double nuevaTarifa) {
        return new TardesReducidas(tarifa, nuevaTarifa);
    }

    @Override
    public Tarifa getTarifaDomingoGratis(Tarifa tarifa) {
        return new DomingoGratis(tarifa);
    }

    @Override
    public Tarifa getTarifaBasica(){
        return new TarifaBasica(15);
    }
}
