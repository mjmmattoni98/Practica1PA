package gestion.datos;


import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;
import empresa.telefonia.Llamada;
import empresa.telefonia.Periodo;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

public class GestionLlamadas extends BaseDatos implements Serializable {
//    private Map<String, Cliente> clientes;
//    private Map<Integer, Factura> facturas;
    private Map<String, List> llamadas;

    public GestionLlamadas(){
        super();
    }

    /*public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public Map<Integer, Factura> getFacturas() {
        return facturas;
    }

    public void setClientes(Map<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setFacturas(Map<Integer, Factura> facturas) {
        this.facturas = facturas;
    }
*/
    public Llamada añadirLlamada(String nif, int numero, double duracion) throws IllegalArgumentException {
        gestionClientes.checkContainsClient(nif);
        Llamada llamada = gestionClientes.clientes.get(nif).añadirLlamada(numero, duracion);
        return llamada;
    }

    public Map<Periodo, List<Llamada>> getLlamadasCliente(String nif) throws IllegalArgumentException {
        gestionClientes.checkContainsClient(nif);
        return gestionClientes.clientes.get(nif).getLlamadas();
    }

    public Set<Llamada> filterCallsByDate(Set<Llamada> calls, Periodo periodo){
        Predicate<Llamada> predicate = call -> call.getFecha().isAfter(periodo.getFechaInicio())
                                            && call.getFecha().isBefore(periodo.getFechaFin());
        return filter(calls, predicate);
    }
}
