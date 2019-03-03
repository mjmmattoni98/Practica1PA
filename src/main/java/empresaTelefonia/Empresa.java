package empresaTelefonia;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Empresa {
    private Map<String, Cliente> clientes;
    private Map<Integer, Factura> facturas;
    private int codigoFactura;

    public Empresa(){
        this.clientes = new HashMap<>();
        this.codigoFactura = 0;
    }

    public void añadirCliente(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa, String apellidos){
        Cliente cliente = new ClienteParticular(nombre, nif, new Direccion(cp, provincia, poblacion), correoElectronico, new Tarifa(tarifa), apellidos);
        clientes.put(nif, cliente);
    }

    public void añadirCliente(String nif, String nombre, int cp, String provincia, String poblacion, String correoElectronico, double tarifa){
        Cliente cliente = new ClienteEmpresa(nombre, nif, new Direccion(cp, provincia, poblacion), correoElectronico, new Tarifa(tarifa));
        clientes.put(nif, cliente);
    }

    public Cliente getCliente(String nif){
        return clientes.get(nif);
    } //modificar

    public Map<String, Cliente> getClientes(){
        return clientes;
    }//modificar

    public Map<String, Cliente> borrarCliente(String nif){
        clientes.remove(nif);
        return clientes;
    }

    public void cambiarTarifaCliente(String nif, double tarifa){
        Tarifa nuevaTarifa = new Tarifa(tarifa);
        clientes.get(nif).setTarifa(nuevaTarifa);
    }

    public void emitirFacturaCliente(String nif){
        Cliente cliente = clientes.get(nif);
        cliente.emitirFactura(codigoFactura);
        facturas.put(codigoFactura, cliente.getFactura(codigoFactura));
        codigoFactura++;
    }

    public void añadirLlamada(String nif, int numero, int duración){
        Llamada llamada = new Llamada(numero, LocalDateTime.now(), duración);
        clientes.get(nif).añadirLlamada(llamada);
    }

    public Factura getFactura(int codigo){
        return facturas.get(codigo);
    }

    public Map<Integer, Factura> getFacturasCliente(String nif){
        return clientes.get(nif).getFacturas();
    }

    public Map<Periodo, List<Llamada>> getLlamadasCliente(String nif){
        return clientes.get(nif).getLlamadas();
    }



}
