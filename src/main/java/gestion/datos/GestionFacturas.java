package gestion.datos;


import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;
import empresa.telefonia.Periodo;
import empresa.telefonia.TarifaBasica;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class GestionFacturas extends BaseDatos implements Serializable {
//    Map<Integer, Factura> facturas;
    int codigoFactura;

    public GestionFacturas(){
        super();
        this.codigoFactura = 0;
//        this.facturas = new HashMap<>();
    }

    public Map<Integer, Factura> getFacturas(){
        return facturas;
    }

    public int getCodigoFactura(){return codigoFactura;}

    public void setCodigoFactura(int codigoFactura){
        this.codigoFactura = codigoFactura;
    }

    public void removeBill(int codigoFactura){
        facturas.remove(codigoFactura);
    }

    public Factura emitirFacturaCliente(String nif) throws IllegalArgumentException {
        checkContainsClient(nif);
        Cliente miCliente = clientes.get(nif);
        Factura factura = new Factura(miCliente.getTarifa(), codigoFactura, miCliente.getActualPeriodoFacturacion());
        factura.calcularImporte(miCliente.getLlamadas().get(miCliente.getActualPeriodoFacturacion()));
        miCliente.añadirFactura(codigoFactura, factura);
        facturas.put(codigoFactura, factura);
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

    public Set<Factura> filterBillsByDate(Set<Factura> bills, Periodo periodo){
        Predicate<Factura> predicate = bill -> bill.getFecha().isAfter(periodo.getFechaInicio())
                                            && bill.getFecha().isBefore(periodo.getFechaFin());
        return BaseDatos.filter(bills, predicate);
    }

}
