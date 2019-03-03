package empresaTelefonia;

import java.util.HashMap;
import java.util.Map;

public class Empresa {
    private Map<String, Cliente> clientes;

    public Empresa(){
        clientes = new HashMap<>();
    }

    public void añadirCliente(String nif, Cliente cliente){
        clientes.put(nif, cliente);
    }

    public Cliente getCliente(String nif){
        return clientes.get(nif);
    }

    public Map<String, Cliente> getClientes(){
        return clientes;
    }

    public Map<String, Cliente> borrarCliente(String nif){
        clientes.remove(nif);
        return clientes;
    }

    public void añadirLlamada(String nif, Llamada llamada){
        Cliente cliente = clientes.get(nif);
        cliente.añadirLlamada(llamada);
        //clientes.put(nif, cliente);
    }



}
