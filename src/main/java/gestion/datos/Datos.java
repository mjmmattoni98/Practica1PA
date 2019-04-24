package gestion.datos;

import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;

import java.io.Serializable;
import java.util.Map;

public class Datos implements Serializable {
    private Map<String, Cliente> clientes;
    private Map<Integer, Factura> facturas;
    private int codigoFactura;

    public Datos(){
    }

    public Map<String, Cliente> getClientes(){
        return clientes;
    }

    public Map<Integer, Factura> getFacturas(){
        return facturas;
    }

    public int getCodigoFactura(){
        return codigoFactura;
    }

    public void setClientes(Map<String, Cliente> clientes){
        this.clientes = clientes;
    }

    public void setFacturas(Map<Integer, Factura> facturas){
        this.facturas = facturas;
    }

    public void setCodigoFactura(int codigoFactura){
        this.codigoFactura = codigoFactura;
    }

}
