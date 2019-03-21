package gestion.datos;

import empresa.telefonia.*;
import excepciones.NIFException;
import excepciones.TarifaException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestionClientes extends BaseDatosClientes implements Serializable {

    public GestionClientes(){
        super();
    }

    public void addClienteParticular(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa, String apellidos) throws TarifaException, NIFException {
        //comprobarKeyClientes(nif);
        Tarifa miTarifa = new Tarifa(tarifa);
        Cliente cliente = new ClienteParticular(nombre, nif, new Direccion(cp, provincia, poblacion), correoElectronico, /*new Tarifa(tarifa)*/ miTarifa, apellidos);
        clientes.put(nif, cliente);
    }

    public void addClienteEmpresa(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa) throws TarifaException, NIFException{
       // comprobarKeyClientes(nif);
        Cliente cliente = new ClienteEmpresa(nombre, nif, new Direccion(cp, provincia, poblacion), correoElectronico, new Tarifa(tarifa));
        clientes.put(nif, cliente);
    }

    public Cliente getCliente(String nif){
        //comprobarKeyClientes(nif);
        return clientes.get(nif);
    }

    public Map<String, Cliente> getClientes(){
        return clientes;
    }

    public Map<Integer, Factura> getFacturas(){
        return facturas;
    }

    public int getCodigoFactura(){return codigoFactura;}

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

    public Factura emitirFacturaCliente(String nif) throws TarifaException{
        //comprobarKeyClientes(nif);
        Factura factura = clientes.get(nif).emitirFactura(codigoFactura);
        facturas.put(codigoFactura, factura);
        codigoFactura++;
        return factura;
    }

    public Llamada añadirLlamada(String nif, int numero, double duracion) {
        //comprobarKeyClientes(nif);
        Llamada llamada = clientes.get(nif).añadirLlamada(numero, duracion);
        return llamada;
    }

    public Factura getFactura(int codigo){
        //comprobarKeyFacturas(codigo);
        return facturas.get(codigo);
    }

    public Map<Integer, Factura> getFacturasCliente(String nif) {
        //comprobarKeyClientes(nif);
        return clientes.get(nif).getFacturas();
    }

    public Map<Periodo, List<Llamada>> getLlamadasCliente(String nif) {
        //comprobarKeyClientes(nif);
        return clientes.get(nif).getLlamadas();
    }

    public <T extends Cliente> List<T> getFecha(List<T> conjuntoObjetos, LocalDateTime fechaInicio, LocalDateTime fechaFin){
        ArrayList<T> listaAux = new ArrayList<>();
        for(T obj : conjuntoObjetos){
            if (obj.getFecha().isAfter(fechaInicio) && obj.getFecha().isBefore(fechaFin)) {
                listaAux.add(obj);
            }
        }
        return listaAux;
    }



}