package gestion.datos;

import empresa.telefonia.Llamada;
import empresa.telefonia.Periodo;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

public class GestionLlamadas extends BaseDatos implements Serializable {

    public GestionLlamadas(){
        super();
    }

    public Llamada añadirLlamada(String nif, int numero, double duracion) throws IllegalArgumentException {
        checkContainsClient(nif);
        Llamada llamada = clientes.get(nif).añadirLlamada(numero, duracion);
        return llamada;
    }

    public Map<Periodo, List<Llamada>> getLlamadasCliente(String nif) throws IllegalArgumentException {
        checkContainsClient(nif);
        return clientes.get(nif).getLlamadas();
    }

    public Set<Llamada> filterCallsByDate(Set<Llamada> calls, Periodo periodo){
        Predicate<Llamada> predicate = call -> call.getFecha().isAfter(periodo.getFechaInicio())
                                            && call.getFecha().isBefore(periodo.getFechaFin());
        return BaseDatos.filter(calls, predicate);
    }
}
