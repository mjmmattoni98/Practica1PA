package gestion.datos;


import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;
import empresa.telefonia.Periodo;
import empresa.telefonia.Tarifa;
import excepciones.TarifaException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;

public class GestionFacturas extends BaseDatos implements Serializable {
    int codigoFactura;

    public GestionFacturas(){
        super();
        this.codigoFactura = 0;
    }

    public Map<Integer, Factura> getFacturas(){
        return facturas;
    }

    public int getCodigoFactura(){return codigoFactura;}

    public Factura emitirFacturaCliente(String nif) throws TarifaException, IllegalArgumentException {
        checkContainsClient(nif);
        Cliente miCliente = clientes.get(nif);
        Factura factura = new Factura(new Tarifa(miCliente.getTarifa()), codigoFactura, miCliente.getActualPeriodoFacturacion());
        factura.calcularImporte(miCliente.getLlamadas().get(miCliente.getActualPeriodoFacturacion()));
        miCliente.añadirFactura(codigoFactura, factura);
        facturas.put(codigoFactura, factura);
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

    public Factura getFactura(int codigo) throws IllegalArgumentException{
        checkContainsBill(codigo);
        return facturas.get(codigo);
    }

    public Map<Integer, Factura> getFacturasCliente(String nif) throws IllegalArgumentException{
        checkContainsClient(nif);
        return clientes.get(nif).getFacturas();
    }

    public void checkContainsBill(int codigo) throws IllegalArgumentException{
        if (!facturas.containsKey(codigo)) throw new IllegalArgumentException("Codigo erróneo. No hay ninguna factura asociada a ese codigo.");
    }

}
