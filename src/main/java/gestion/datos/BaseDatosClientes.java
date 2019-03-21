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

    public void checkContainsClient(String nif) throws IllegalArgumentException{
        if (!clientes.containsKey(nif)) throw new IllegalArgumentException("El cliente no exixte o ya está la cuenta creada.");
    }

    public void checkNotContainsClient(String nif) throws IllegalArgumentException{
        if (!clientes.containsKey(nif)) throw new IllegalArgumentException("El cliente no exixte o ya está la cuenta creada.");
    }

    public void checkContainsKey(int codigo) throws IllegalArgumentException{
        if (!facturas.containsKey(codigo)) throw new IllegalArgumentException("Codigo erróneo.");
    }

}
