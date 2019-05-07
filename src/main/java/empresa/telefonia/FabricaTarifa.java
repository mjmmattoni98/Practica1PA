package empresa.telefonia;

public interface FabricaTarifa {
//    Tarifa getTarifa(TipoTarifa tarifa);
    Tarifa getTarifaBasica();
    Tarifa getTarifaTardesReducidas(Tarifa tarifa, double nuevaTarifa);
    Tarifa getTarifaDomingoGratis(Tarifa tarifa);

}