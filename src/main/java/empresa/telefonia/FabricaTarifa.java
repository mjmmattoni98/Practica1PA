package empresa.telefonia;

public interface FabricaTarifa {
    Tarifa getTarifaBasica();
    Tarifa getTarifaTardesReducidas(Tarifa tarifa, double nuevaTarifa);
    Tarifa getTarifaDomingoGratis(Tarifa tarifa);

}