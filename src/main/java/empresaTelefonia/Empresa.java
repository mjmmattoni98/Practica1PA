package empresaTelefonia;

import java.util.HashMap;
import java.util.Map;

public class Empresa {
    private Map<String, Cliente> clientes;

    public Empresa(){
        clientes = new HashMap<>();
    }

    public void añadirCliente(Cliente cliente){
        clientes.put(cliente.getNif(), cliente);
    }

    public Cliente getCliente(String nif){
        return clientes.get(nif);
    }

    public void añadirLlamada(String nif, Llamada llamada){
        Cliente cliente = clientes.get(nif);
        cliente.añadirLlamada(llamada);
        //clientes.put(nif, cliente);
    }

    public void añadirFactura(String nif, Factura factura){
        Cliente cliente = clientes.get(nif);
        cliente.añadirFactura(factura);
        //clientes.put(nif, cliente);
    }


}
