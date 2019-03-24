package gestion.datos;

import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;

import java.time.LocalDateTime;
import java.util.*;

public abstract class BaseDatos {
    static Map<String, Cliente> clientes;
    static Map<Integer, Factura> facturas;
    static int codigoFactura;

    public BaseDatos(){
        super();
        clientes = new HashMap<>();
        facturas = new HashMap<>();
        codigoFactura = 0;
    }

    public <T extends Cliente> Set<T> getConjuntoObjetosEntreFechas(Set<T> conjuntoObjetos, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        Set<T> setAux = new HashSet<>();
        for(T obj : conjuntoObjetos){
            if (obj.getFecha().isAfter(fechaInicio) && obj.getFecha().isBefore(fechaFin)) {
                setAux.add(obj);
            }
        }
        return setAux;
    }


}
