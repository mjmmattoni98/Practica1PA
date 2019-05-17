package modelo;

public interface InterrogaModelo {

    //Clientes
    void mostrarClientes();
    void mostrarDatosCliente(String nif);
    void mostrarClientesEntreFechas();


    //Facturas
    void mostrarDatosFactura();
    void mostrarFacturasCliente(String nif);
    void mostrarFacturasEntreFechas(String nif);

    //Llamadas
    void mostrarLlamadasCliente(String nif);
    void mostrarLlamadasEntreFechas(String nif);

}
