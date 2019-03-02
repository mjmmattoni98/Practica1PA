package empresaTelefonia;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public abstract class Cliente {
    private String nombre;
    private String nif;
    private Direccion direccion;
    private String correoElectronico;
    private Fecha fechaDeAlta;
    private Tarifa tarifa;
    private Map<Period, List<Llamada>> llamadas;
    private Period actualPeriodoFacturacion;
    private List<Factura> facturas;

    public Cliente () {
        super();
    }

    public Cliente (String nombre, String nif, Direccion direccion, String correoElectronico, Fecha fechaDeAlta, Tarifa tarifa){
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.fechaDeAlta = fechaDeAlta;
        this.tarifa = tarifa;
        this.llamadas = new HashMap<>();
        this.actualPeriodoFacturacion = Period.between(LocalDate.now(), LocalDate.now().plusDays(30));
        this.llamadas.put(actualPeriodoFacturacion, new LinkedList<>());
        this.facturas = new LinkedList<>();
    }

    public void setTarifa(Tarifa nuevaTarifa){
        this.tarifa = nuevaTarifa;
    }

    public Fecha getFecha (){
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
        facturas.add(factura);
    }

    public void emitirFactura(){

    }
}
