package gestion.datos;


import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;
import empresa.telefonia.Periodo;
import empresa.telefonia.Tarifa;
import excepciones.TarifaException;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

public class GestionFacturas extends BaseDatos {

    public GestionFacturas(){
        super();
    }

    public Map<Integer, Factura> getFacturas(){
        return facturas;
    }

    public int getCodigoFactura(){return codigoFactura;}

    /*public Factura emitirFacturaCliente(String nif) throws TarifaException {
        //comprobarKeyClientes(nif);
        Factura factura = clientes.get(nif).emitirFactura(codigoFactura);
        facturas.put(codigoFactura, factura);
        codigoFactura++;
        return factura;
    }*/

    public Factura emitirFacturaCliente(String nif) throws TarifaException {
        Cliente miCliente = clientes.get(nif);
        Factura factura = new Factura(new Tarifa(miCliente.getTarifa()), codigoFactura, miCliente.getActualPeriodoFacturacion());
        factura.calcularImporte(miCliente.getLlamadas().get(miCliente.getActualPeriodoFacturacion()));
        miCliente.añadirFactura(codigoFactura, factura);
        //miCliente.setActualPeriodoFacturacion();
        setActualPeriodoFacturacionCliente(miCliente);
        codigoFactura++;
        return factura;
    }

    private void setActualPeriodoFacturacionCliente(Cliente miCliente){
        LocalDateTime fechaFin = miCliente.getActualPeriodoFacturacion().getFechaFin();
        Periodo actualPeriodoFacturacion = miCliente.setActualPeriodoFacturacion(new Periodo(fechaFin, fechaFin.plusDays(30)));
        miCliente.getLlamadas().put(actualPeriodoFacturacion, new LinkedList<>());
    }

    public Factura getFactura(int codigo){
        //comprobarKeyFacturas(codigo);
        return facturas.get(codigo);
    }

    public Map<Integer, Factura> getFacturasCliente(String nif) {
        //comprobarKeyClientes(nif);
        return clientes.get(nif).getFacturas();
    }

    public void checkContainsBill(int codigo) throws IllegalArgumentException{
        if (!facturas.containsKey(codigo)) throw new IllegalArgumentException("Codigo erróneo. No hay ninguna factura asociada a ese codigo.");
    }

}
