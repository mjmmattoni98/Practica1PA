package gestion.datos;

import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseDatosClientes {
    Map<String, Cliente> clientes;
    Map<Integer, Factura> facturas;
    int codigoFactura;

    public BaseDatosClientes(){
        super();
        this.clientes = new HashMap<>();
        this.facturas = new HashMap<>();
        this.codigoFactura = 0;
    }

}
