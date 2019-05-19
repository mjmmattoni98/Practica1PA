package modelo;

import java.time.LocalDateTime;

public interface InterrogaModelo {

    //Datos
    void escribirDatos();

    //Clientes
    String mostrarClientes();
    String mostrarDatosCliente(String nif);
    String mostrarClientesEntreFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin);


    //Facturas
    String mostrarDatosFactura(int codFac);
    String mostrarFacturasCliente(String nif);
    String mostrarFacturasEntreFechas(String nif, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    //Llamadas
    String mostrarLlamadasCliente(String nif);
    String mostrarLlamadasEntreFechas(String nif, LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
