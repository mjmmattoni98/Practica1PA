package gestion.datos;

import empresa.telefonia.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class GestionClientes extends BaseDatos implements Serializable {
    //Map<String, Cliente> clientes;

    public GestionClientes(){
        super();
//        this.clientes = new HashMap<>();
    }

    public void addClienteParticular(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, String apellidos) throws IllegalArgumentException{
        checkNotContainsClient(nif);
        TarifaBasica miTarifa = new TarifaBasica(15.0);
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Cliente cliente = new ClienteParticular(nombre, nif, direccion, correoElectronico,miTarifa, apellidos);
        clientes.put(nif, cliente);
    }

    public void addClienteEmpresa(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico) throws IllegalArgumentException{
        checkNotContainsClient(nif);
        TarifaBasica miTarifa = new TarifaBasica(15.0);
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
//            BaseDatos.gestor.gestionFacturas.removeBill(codigoFactura);
        return clientes;
    }
//      facturas.remove(codigoFactura);

    public void cambiarTarifaCliente(String nif, double tarifa) throws IllegalArgumentException{
        checkContainsClient(nif);
        TarifaBasica nuevaTarifa = new TarifaBasica(15.0);
        clientes.get(nif).setTarifa(nuevaTarifa);
    }

    public Set<Cliente> filterClientsByDate(Set<Cliente> clients, Periodo periodo){
        Predicate<Cliente> predicate = cliente -> cliente.getFecha().isAfter(periodo.getFechaInicio())
                                                && cliente.getFecha().isBefore(periodo.getFechaFin());
        return BaseDatos.filter(clients, predicate);
    }

}
