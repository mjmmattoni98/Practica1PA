package empresa.telefonia;

import excepciones.TarifaException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public abstract class Cliente implements Serializable, Fecha {
    private String nombre;
    private String nif;
    private Direccion direccion;
    private String correoElectronico;
    private LocalDateTime fechaDeAlta;
    private Tarifa tarifa;
    private Map<Periodo, List<Llamada>> llamadas;
    private Periodo actualPeriodoFacturacion;
    private Map<Integer, Factura> facturas; //Key -> código de la factura.

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
        this.llamadas = new HashMap<>();
        this.actualPeriodoFacturacion = new Periodo(LocalDateTime.now(), LocalDateTime.now().plusDays(30));
        this.llamadas.put(actualPeriodoFacturacion, new LinkedList<>());
        this.facturas = new HashMap<>();
    }

    public void setTarifa(Tarifa nuevaTarifa) throws  TarifaException{
        if (nuevaTarifa.getTarifa() < 0) throw new TarifaException();
        this.tarifa = nuevaTarifa;
    }

    public double getTarifa(){
        return tarifa.getTarifa();
    }

    public String getNombre(){
        return nombre;
    }

    public String getCorreoElectronico(){
        return correoElectronico;
    }

    public Direccion getDireccion(){
        return direccion;
    }

    @Override
    public LocalDateTime getFecha (){
        return fechaDeAlta;
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

    public Periodo getActualPeriodoFacturacion(){
        return actualPeriodoFacturacion;
    }

    public Periodo setActualPeriodoFacturacion(Periodo periodoFacturacion){
        this.actualPeriodoFacturacion = periodoFacturacion;
        return actualPeriodoFacturacion;
    }

    public Llamada añadirLlamada(int numero, double duracion) {
        Llamada llamada = new Llamada(numero, LocalDateTime.now(), duracion);
        List<Llamada> listaLlamadas = llamadas.get(actualPeriodoFacturacion);
        listaLlamadas.add(llamada);
        llamadas.put(actualPeriodoFacturacion, listaLlamadas);
        return llamada;
    }

    public void añadirFactura(int codigo, Factura factura){
        facturas.put(codigo, factura);
    }

    public abstract String toString();
}
