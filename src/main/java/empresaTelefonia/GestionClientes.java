package empresaTelefonia;

import excepciones.NIFException;
import excepciones.TarifaException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionClientes implements Serializable {
    private Map<String, Cliente> clientes;
    private Map<Integer, Factura> facturas;
    private int codigoFactura;

    public GestionClientes(){
        this.clientes = new HashMap<>();
        this.facturas = new HashMap<>();
        this.codigoFactura = 0;
    }

    public void addClienteParticular(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa, String apellidos) throws TarifaException, NIFException {
        //comprobarKeyClientes(nif);
        //if (tarifa < 0) throw new TarifaException();
        Tarifa miTarifa = new Tarifa(tarifa);
        Cliente cliente = new ClienteParticular(nombre, nif, new Direccion(cp, provincia, poblacion), correoElectronico, /*new Tarifa(tarifa)*/ miTarifa, apellidos);
        clientes.put(nif, cliente);
    }

    public void addClienteEmpresa(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa) throws TarifaException, NIFException{
       // comprobarKeyClientes(nif);
        Cliente cliente = new ClienteEmpresa(nombre, nif, new Direccion(cp, provincia, poblacion), correoElectronico, new Tarifa(tarifa));
        clientes.put(nif, cliente);
    }

    /*public void comprobarKeyClientes(String nif) throws IllegalArgumentException{
        if (!clientes.containsKey(nif)) throw new IllegalArgumentException("El cliente no exixte o ya está la cuenta creada.");
    }
    public void comprobarKeyFacturas(int codigo) throws IllegalArgumentException{
        if (!facturas.containsKey(codigo)) throw new IllegalArgumentException("Codigo erróneo.");
    }*/

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
