package empresaTelefonia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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
    private Map<LocalDateTime, Factura> facturas; //Key -> fecha de emisión de la factura.
    private int codigoFactura;

    public Cliente () {
        super();
    }

    public Cliente (String nombre, String nif, Direccion direccion, String correoElectronico, LocalDateTime fechaDeAlta, Tarifa tarifa){
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.fechaDeAlta = fechaDeAlta;
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

    public String getNif(){
        return nif;
    }

    public void añadirLlamada(Llamada llamada){
        List<Llamada> listaLlamadas = llamadas.get(actualPeriodoFacturacion);
        listaLlamadas.add(llamada);
        llamadas.put(actualPeriodoFacturacion, listaLlamadas);
    }

    public void añadirFactura(Factura factura){
        facturas.put(factura.getFecha(), factura);
    }

    public void emitirFactura(){
        Factura factura = new Factura(tarifa, codigoFactura++, actualPeriodoFacturacion);
        factura.calcularImporte(llamadas.get(actualPeriodoFacturacion));
        facturas.put(factura.getFecha(), factura);
        setActualPeriodoFacturacion();
    }

    private void setActualPeriodoFacturacion(){
        this.actualPeriodoFacturacion = new Periodo(LocalDateTime.now(), LocalDateTime.now().plusDays(30));
    }
}
