package empresa.telefonia;

public class FabricadoTarifa implements FabricaTarifa {
    private double tarifa;
    private double nuevaTarifa;

    public FabricadoTarifa(double tarifa, double nuevaTarifa) {
        this.tarifa = tarifa;
        this.nuevaTarifa = nuevaTarifa;
    }

    public Tarifa getTarifa(TipoTarifa tipo) {
        Tarifa tarifa = new TarifaBasica(this.tarifa);
        switch (tipo) {
            case TARDES_REDUCIDAS:
                tarifa = new TardesReducidas(tarifa, nuevaTarifa);
                break;
            case DOMINGO_GRATIS:
                tarifa = new DomingoGratis(tarifa);
                break;
        }
        return tarifa;
    }
}
