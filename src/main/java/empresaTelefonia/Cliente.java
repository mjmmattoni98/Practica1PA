package empresaTelefonia;

import clasesDescartadas.NumeroLlamadaException;
import excepciones.TarifaException;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Cliente {
    private String nombre;
    private String nif;
    private Direccion direccion;
    private String correoElectronico;
    private LocalDateTime fechaDeAlta;
    private Tarifa tarifa;
    private Map<Periodo, List<Llamada>> llamadas;
    private Periodo actualPeriodoFacturacion;
    private Map<Integer, Factura> facturas; //Key -> código de la factura.

    public Cliente () {
        super();
    }

    public Cliente (String nombre, String nif, Direccion direccion, String correoElectronico, Tarifa tarifa) {
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correoElectronico = correoElectronico;
        this.fechaDeAlta = LocalDateTime.now();
        this.tarifa = tarifa;
        this.llamadas = new HashMap<>();
        this.actualPeriodoFacturacion = new Periodo(LocalDateTime.now(), LocalDateTime.now().plusDays(30));
        this.llamadas.put(actualPeriodoFacturacion, new LinkedList<>());
        this.facturas = new HashMap<>();
    }

    public void setTarifa(Tarifa nuevaTarifa){
        this.tarifa = nuevaTarifa;
    }

    public Tarifa getTarifa(){
        return tarifa;
    }

    public String getNombre(){
        return nombre;
    }

    public String getCorreoElectronico(){
        return correoElectronico;
    }

    public Direccion getDireccion(){
        return direccion;
    }

    public LocalDateTime getFecha (){
        return fechaDeAlta;
    }

    public Factura getFactura(int codigo){
        return facturas.get(codigo);
    }

    public Map<Integer, Factura> getFacturas(){
        return facturas;
    }

    public Map<Periodo, List<Llamada>> getLlamadas(){
        return llamadas;
    }

    public String getNif(){
        return nif;
    }

    public Llamada añadirLlamada(int numero, int duracion) {
        Llamada llamada = new Llamada(numero, LocalDateTime.now(), duracion);
        List<Llamada> listaLlamadas = llamadas.get(actualPeriodoFacturacion);
        listaLlamadas.add(llamada);
        llamadas.put(actualPeriodoFacturacion, listaLlamadas);
        return llamada;
    }

    private void añadirFactura(int codigo, Factura factura){
        facturas.put(codigo, factura);
    }

    public Factura emitirFactura(int codigoFactura) throws TarifaException {
        Factura factura = new Factura(tarifa, codigoFactura, actualPeriodoFacturacion);
        factura.calcularImporte(llamadas.get(actualPeriodoFacturacion));
        añadirFactura(codigoFactura, factura);
        setActualPeriodoFacturacion();
        return factura;
    }

    private void setActualPeriodoFacturacion(){
        LocalDateTime fechaFin = actualPeriodoFacturacion.getFechaFin();
        this.actualPeriodoFacturacion = new Periodo(fechaFin, fechaFin.plusDays(30));
        llamadas.put(actualPeriodoFacturacion, new LinkedList<>());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-Nombre: " + nombre + "\n");
        sb.append("-NIF: " + nif + "\n");
        sb.append("-Direción:\n" + direccion + "\n");
        sb.append("-Correo electrónico: " + correoElectronico + "\n");
        sb.append("-Fecha de alta: " + fechaDeAlta + "\n");
        sb.append("-Tarifa: " + tarifa + "\n");
        sb.append("-Listado de llamadas:\n");
        Iterator<Periodo> iterLlamadas = llamadas.keySet().iterator();
        while (iterLlamadas.hasNext())
            for (Llamada llamada : llamadas.get(iterLlamadas.next()))
                sb.append("\t-" + llamada);
        sb.append("-Listado de facturas:\n");
        Collection<Factura> colFacturas = facturas.values();
        for(Factura factura : colFacturas)
            sb.append(factura);
        return sb.toString();
    }
}
