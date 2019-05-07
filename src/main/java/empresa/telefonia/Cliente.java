package empresa.telefonia;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public abstract class Cliente implements Serializable, Fecha {
    private Usuario usuario;
    private Direccion direccion;
    private LocalDateTime fechaDeAlta;
    private Tarifa tarifa;
    private Map<Periodo, List<Llamada>> llamadas;
    private Periodo actualPeriodoFacturacion;
    private Map<Integer, Factura> facturas; //Key -> código de la factura.
    public static final Cliente NULL_CLIENT = new ClienteParticular();

    public Cliente () {
        super();
    }

    public Cliente (Usuario usuario, Direccion direccion, Tarifa tarifa){
        this.usuario = usuario;
        this.direccion = direccion;
        this.fechaDeAlta = LocalDateTime.now();
        this.tarifa = tarifa;
        this.llamadas = new HashMap<>();
        this.actualPeriodoFacturacion = new Periodo(LocalDateTime.now(), LocalDateTime.now().plusDays(30));
        this.llamadas.put(actualPeriodoFacturacion, new LinkedList<>());
        this.facturas = new HashMap<>();
    }

    public void setTarifa(Tarifa nuevaTarifa) {
        this.tarifa = nuevaTarifa;
    }

    public Tarifa getTarifa(){
        return tarifa;
    }

    public String getNombre(){
        return usuario.getNombre();
    }

    public String getCorreoElectronico(){
        return usuario.getCorreoElectronico();
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
        return usuario.getNif();
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
