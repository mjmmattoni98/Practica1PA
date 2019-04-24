package gestion.datos;

import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;
import empresa.telefonia.Fecha;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TODO hacer composicion de objetos.
public class BaseDatos implements Serializable {
    static Map<String, Cliente> clientes = new HashMap<>();
    static Map<Integer, Factura> facturas = new HashMap<>();
//    GestionLlamadas gestionLlamadas;
//    GestionClientes gestionClientes;
//    GestionFacturas gestionFacturas;
//    static Gestor gestor = new Gestor();

    public BaseDatos(){
//        this.gestionClientes = new GestionClientes();
//        this.gestionLlamadas = new GestionLlamadas();
//        this.gestionFacturas = new GestionFacturas();
//        this.gestor = new Gestor();
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

    public static <T> Set<T> filter(Set<T> conjuntoObjetos, Predicate<T> predicate){
        return conjuntoObjetos.stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    public void checkNotContainsClient(String nif) throws IllegalArgumentException{
        if (clientes.containsKey(nif)) throw new IllegalArgumentException("Ya hay una cuenta asociada a ese NIF.");
    }

    public void checkContainsClient(String nif) throws IllegalArgumentException{
        if (!clientes.containsKey(nif)) throw new IllegalArgumentException("El cliente no exixte.");
    }

}
