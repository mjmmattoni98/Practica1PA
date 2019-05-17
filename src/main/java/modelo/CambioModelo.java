package modelo;

import empresa.telefonia.Direccion;
import empresa.telefonia.Tarifa;
import empresa.telefonia.Usuario;

public interface CambioModelo {
    //Cuentas
    /*void addCuenta();*/
    void addClienteParticular(Usuario usuario, Direccion direccion, String apellidos);
    void addClienteEmpresa(Usuario usuario, Direccion direccion);
    void delCuenta(String nif);

    //Llamadas
    void addLlamada(String nif, int numero, double duracion);

    //Tarifa
    void modificarTarifa(String nif, Tarifa tarifa);
//    void modTarifaTardesReducidas(String nif);

    //Facturas
    void emitirFactura(String nif);
}
