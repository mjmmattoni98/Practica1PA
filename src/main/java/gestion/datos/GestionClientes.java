package gestion.datos;

import empresa.telefonia.*;
import excepciones.TarifaException;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class GestionClientes extends BaseDatos implements Serializable {

    public GestionClientes(){
        super();
    }

    public void addClienteParticular(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa, String apellidos) throws TarifaException, IllegalArgumentException{
        checkNotContainsClient(nif);
        Tarifa miTarifa = new Tarifa(tarifa);
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Cliente cliente = new ClienteParticular(nombre, nif, direccion, correoElectronico,miTarifa, apellidos);
        clientes.put(nif, cliente);
    }

    public void addClienteEmpresa(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa) throws TarifaException, IllegalArgumentException{
        checkNotContainsClient(nif);
        Tarifa miTarifa = new Tarifa(tarifa);
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Cliente cliente = new ClienteEmpresa(nombre, nif, direccion, correoElectronico, miTarifa);
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

    public void cambiarTarifaCliente(String nif, double tarifa) throws TarifaException, IllegalArgumentException{
        checkContainsClient(nif);
        Tarifa nuevaTarifa = new Tarifa(tarifa);
        clientes.get(nif).setTarifa(nuevaTarifa);
    }

    public Set<Cliente> filterClientsByDate(Set<Cliente> clients, Periodo periodo){
        Predicate<Cliente> predicate = cliente -> cliente.getFecha().isAfter(periodo.getFechaInicio())
                                                && cliente.getFecha().isBefore(periodo.getFechaFin());
        return filter(clients, predicate);
    }

}
