package empresa.telefonia;

public class FabricadoTarifa implements FabricaTarifa {
    private Tarifa tarifa;
    private double nuevaTarifa;

    public FabricadoTarifa(Tarifa tarifa, double nuevaTarifa) {
        this.tarifa = tarifa;
        this.nuevaTarifa = nuevaTarifa;
    }

    public Tarifa getTarifa(TipoTarifa tipo) {
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
