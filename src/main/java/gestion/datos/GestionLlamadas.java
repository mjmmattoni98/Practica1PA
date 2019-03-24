package gestion.datos;


import empresa.telefonia.Llamada;
import empresa.telefonia.Periodo;

import java.util.List;
import java.util.Map;

public class GestionLlamadas extends BaseDatos {

    public GestionLlamadas(){
        super();
    }

    public Llamada añadirLlamada(String nif, int numero, double duracion) {
        //comprobarKeyClientes(nif);
        Llamada llamada = clientes.get(nif).añadirLlamada(numero, duracion);
        return llamada;
    }

    public Map<Periodo, List<Llamada>> getLlamadasCliente(String nif) {
        //comprobarKeyClientes(nif);
        return clientes.get(nif).getLlamadas();
    }

}
