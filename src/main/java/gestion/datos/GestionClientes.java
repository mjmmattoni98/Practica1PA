package gestion.datos;

import empresa.telefonia.*;
import excepciones.NIFException;
import excepciones.TarifaException;

import java.io.Serializable;
import java.util.Map;

public class GestionClientes extends BaseDatos implements Serializable {

    public GestionClientes(){
        super();
    }

    public void addClienteParticular(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa, String apellidos) throws TarifaException, NIFException {
        //comprobarKeyClientes(nif);
        Tarifa miTarifa = new Tarifa(tarifa);
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Cliente cliente = new ClienteParticular(nombre, nif, direccion, correoElectronico,miTarifa, apellidos);
        clientes.put(nif, cliente);
    }

    public void addClienteEmpresa(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa) throws TarifaException, NIFException{
       // comprobarKeyClientes(nif);
        Tarifa miTarifa = new Tarifa(tarifa);
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Cliente cliente = new ClienteEmpresa(nombre, nif, direccion, correoElectronico, miTarifa);
        clientes.put(nif, cliente);
    }

    public Cliente getCliente(String nif){
        //comprobarKeyClientes(nif);
        return clientes.get(nif);
    }

    public Map<String, Cliente> getClientes(){
        return clientes;
    }

    public Map<String, Cliente> borrarCliente(String nif){
        //comprobarKeyClientes(nif);
        clientes.remove(nif);
        return clientes;
    }

    public void cambiarTarifaCliente(String nif, double tarifa) throws TarifaException{
        //comprobarKeyClientes(nif);
        Tarifa nuevaTarifa = new Tarifa(tarifa);
        clientes.get(nif).setTarifa(nuevaTarifa);
    }

    public void checkContainsClient(String nif) throws IllegalArgumentException{
        if (!clientes.containsKey(nif)) throw new IllegalArgumentException("Ya hay una cuenta asociada a ese NIF.");
    }

    public void checkNotContainsClient(String nif) throws IllegalArgumentException{
        if (!clientes.containsKey(nif)) throw new IllegalArgumentException("El cliente no exixte.");
    }

}
