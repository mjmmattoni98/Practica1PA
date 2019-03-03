package empresaTelefonia;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Cliente {
    private String nombre;
    private String nif;
    private Direccion direccion;
    private String correoElectronico;
    private LocalDateTime fechaDeAlta;
    private Tarifa tarifa;
    private Map<Periodo, List<Llamada>> llamadas;
    private Periodo actualPeriodoFacturacion;
    private Map<Integer, Factura> facturas; //Key -> código de la factura.
    private int codigoFactura;

    public Cliente () {
        super();
    }

    public Cliente (String nombre, String nif, Direccion direccion, String correoElectronico, Tarifa tarifa){
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.fechaDeAlta = LocalDateTime.now();
        this.tarifa = tarifa;
        this.codigoFactura = 0;
        this.llamadas = new HashMap<>();
        this.actualPeriodoFacturacion = new Periodo(LocalDateTime.now(), LocalDateTime.now().plusDays(30));
        this.llamadas.put(actualPeriodoFacturacion, new LinkedList<>());
        this.facturas = new HashMap<>();
    }

    public void setTarifa(Tarifa nuevaTarifa){
        this.tarifa = nuevaTarifa;
    }

    public LocalDateTime getFecha (){
        return fechaDeAlta;
    }

    public Factura getFactura(int codigo){
        return facturas.get(codigo);
    }

    public Map<Integer, Factura> getFacturas(){
        return facturas;
    }

    public Map<Periodo, List<Llamada>> getLlamadas(){
        return llamadas;
    }

    public String getNif(){
        return nif;
    }

    public void añadirLlamada(Llamada llamada){
        List<Llamada> listaLlamadas = llamadas.get(actualPeriodoFacturacion);
        listaLlamadas.add(llamada);
        llamadas.put(actualPeriodoFacturacion, listaLlamadas);
    }

    private void añadirFactura(int codigo, Factura factura){
        facturas.put(codigo, factura);
    }

    public Factura emitirFactura(){
        Factura factura = new Factura(tarifa, codigoFactura, actualPeriodoFacturacion);
        factura.calcularImporte(llamadas.get(actualPeriodoFacturacion));
        añadirFactura(codigoFactura++, factura);
        setActualPeriodoFacturacion();
        return factura;
    }

    private void setActualPeriodoFacturacion(){
        this.actualPeriodoFacturacion = new Periodo(LocalDateTime.now(), LocalDateTime.now().plusDays(30));
    }
}
