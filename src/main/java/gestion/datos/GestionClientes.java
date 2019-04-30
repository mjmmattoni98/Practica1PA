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

    public void resetClients(){
        clientes = new HashMap<>();
    }

    public void addClienteParticular(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, String apellidos) throws IllegalArgumentException{
        checkNotContainsClient(nif);
        FabricadoCliente fabricaCliente = new FabricadoCliente(nif,nombre, cp,provincia,poblacion, correoElectronico,Tarifa.tarifaBasica, apellidos);
        ClienteParticular cliente = (ClienteParticular) fabricaCliente.getCliente(TipoCliente.PARTICULAR);
        clientes.put(nif, cliente);
    }

    public void addClienteEmpresa(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico) throws IllegalArgumentException{
        checkNotContainsClient(nif);
        FabricadoCliente fabricaCliente = new FabricadoCliente(nombre,nif,cp,provincia,poblacion, correoElectronico,Tarifa.tarifaBasica,"");
        ClienteEmpresa cliente = (ClienteEmpresa) fabricaCliente.getCliente(TipoCliente.EMPRESA);
        clientes.put(nif, cliente);
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
