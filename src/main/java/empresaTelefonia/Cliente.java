package empresaTelefonia;

import java.util.LinkedList;
import java.util.List;

public abstract class Cliente {
    private String nombre;
    private String nif;
    private Direccion direccion;
    private String correoElectronico;
    private Fecha fechaDeAlta;
    private Tarifa tarifa;
    private List<Llamada> llamadas;
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
        this.llamadas = new LinkedList<>();
        this.facturas = new LinkedList<>();
    }

    public Fecha getFecha (){
        return fechaDeAlta;
    }

    public String getNif(){
        return nif;
    }

    public void añadirLlamada(Llamada llamada){
        llamadas.add(llamada);
    }

    public void añadirFactura(Factura factura){
        facturas.add(factura);
    }
}
