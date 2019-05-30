package gestion.datos;

import empresa.telefonia.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class GestionClientes extends BaseDatos implements Serializable {

    public GestionClientes(){
        super();
    }

    public static void resetClients(){
        clientes = new HashMap<>();
    }

    public void addClienteParticular(Usuario usuario, Direccion direccion, String apellidos) throws IllegalArgumentException{
        checkNotContainsClient(usuario.getNif());
        FabricadoTarifa fabricadoTarifa = new FabricadoTarifa();
        FabricadoCliente fabricaCliente = new FabricadoCliente();
        ClienteParticular cliente = fabricaCliente.getClienteParticular(usuario, direccion, fabricadoTarifa.getTarifaBasica(), apellidos);
        clientes.put(usuario.getNif(), cliente);
        // todo el modelo debe informar a la vista de que ha habido cambios por si se quiere actualizar.
    }

    public void addClienteEmpresa(Usuario usuario, Direccion direccion) throws IllegalArgumentException{
        checkNotContainsClient(usuario.getNif());
        FabricadoTarifa fabricadoTarifa = new FabricadoTarifa();
        FabricadoCliente fabricaCliente = new FabricadoCliente();
        ClienteEmpresa cliente = fabricaCliente.getClienteEmpresa(usuario, direccion, fabricadoTarifa.getTarifaBasica());
        clientes.put(usuario.getNif(), cliente);
        // todo el modelo debe informar a la vista de que ha habido cambios por si se quiere actualizar.
    }

    public void addCliente(Cliente cliente){
        String nif = cliente.getNif();
        checkNotContainsClient(nif);
        clientes.put(nif, cliente);
    }

    public Cliente getCliente(String nif) throws IllegalArgumentException{
        checkContainsClient(nif);
        return clientes.get(nif);
    }

    public Map<String, Cliente> getClientes(){
        return clientes;
    }

    public Map<String, Cliente> borrarCliente(String nif) throws IllegalArgumentException{
        checkContainsClient(nif);
        Cliente miCliente = clientes.remove(nif);
        Map<Integer, Factura> facturasMiCliente = miCliente.getFacturas();
        for (Integer codigoFactura : facturasMiCliente.keySet())
              facturas.remove(codigoFactura);
        return clientes;
    }

    public void cambiarTarifaCliente(String nif, Tarifa tarifa) throws IllegalArgumentException{
        checkContainsClient(nif);
        clientes.get(nif).setTarifa(tarifa);
    }

    public Set<Cliente> filterClientsByDate(Set<Cliente> clients, Periodo periodo){
        Predicate<Cliente> predicate = cliente -> cliente.getFecha().isAfter(periodo.getFechaInicio())
                                                && cliente.getFecha().isBefore(periodo.getFechaFin());
        return BaseDatos.filter(clients, predicate);
    }

}
