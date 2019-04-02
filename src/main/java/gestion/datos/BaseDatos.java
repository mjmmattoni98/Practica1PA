package gestion.datos;

import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;
import empresa.telefonia.Fecha;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;

//TODO hacer composicion de objetos.
public abstract class BaseDatos implements Serializable {
    static Map<String, Cliente> clientes = new HashMap<>();
    static Map<Integer, Factura> facturas = new HashMap<>();

    public BaseDatos(){
        super();
    }

    public static void setClientesBD(Map<String, Cliente> nuevosClientes){
        clientes = nuevosClientes;
    }

    public static void setFacturasBD(Map<Integer, Factura> nuevasFacturas){
        facturas = nuevasFacturas;
    }

    public static Map<Integer, Factura> getFacturasBD() {
        return facturas;
    }

    public static Map<String, Cliente> getClientesBD() {
        return clientes;
    }

    public <T extends Fecha> Set<T> filtrarEntreFechas(Set<T> conjuntoObjetos, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        Set<T> conjuntoFiltrado = new HashSet<>();
        for(T obj : conjuntoObjetos)
            if (obj.getFecha().isAfter(fechaInicio) && obj.getFecha().isBefore(fechaFin))
                conjuntoFiltrado.add(obj);
        return conjuntoFiltrado;
    }

    //TODO utilizar streams
    public <T> Set<T> filter(Set<T> conjuntoObjetos, Predicate<T> predicate){
        Set<T> conjuntoFiltrado = new HashSet<>();
        for(T obj : conjuntoObjetos)
            if (predicate.test(obj))
                conjuntoFiltrado.add(obj);
        return conjuntoFiltrado;
    }

    public void checkNotContainsClient(String nif) throws IllegalArgumentException{
        if (clientes.containsKey(nif)) throw new IllegalArgumentException("Ya hay una cuenta asociada a ese NIF.");
    }

    public void checkContainsClient(String nif) throws IllegalArgumentException{
        if (!clientes.containsKey(nif)) throw new IllegalArgumentException("El cliente no exixte.");
    }

}
