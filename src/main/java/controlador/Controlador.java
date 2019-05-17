package controlador;

import empresa.telefonia.Tarifa;

public interface Controlador {
    //Cuentas
    /*void addCuenta();*/
//    void addClienteParticular(String nombre, String nif, int cp, String provincia, String poblacion, String correoElectronico, String apellidos);
//    void addClienteEmpresa(String nombre, String nif, int cp, String provincia, String poblacion, String correoElectronico);
    void delCuenta(String nif);

    //Llamadas
    void addLlamada(String nif, int numero, double duracion);

    //Tarifa
    void modificarTarifa(String nif, Tarifa tarifa);
//    void modTarifaTardesReducidas(String nif);

    //Facturas
    void emitirFactura(String nif);
}