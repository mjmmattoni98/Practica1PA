package modelo;
import java.util.*;

import empresa.telefonia.*;
import gestion.datos.GestionClientes;
import gestion.datos.GestionFacturas;
import gestion.datos.GestionLlamadas;
import vista.InformaVista;


public class ImplementacionModelo implements CambioModelo, InterrogaModelo {
    private InformaVista vista;
    private GestionClientes gestionClientes = new GestionClientes();
    private GestionLlamadas gestionLlamadas = new GestionLlamadas();
    private GestionFacturas gestionFacturas = new GestionFacturas();


    public ImplementacionModelo(){}

    public void setVista(InformaVista vista) {
        this.vista = vista;
    }

	/*@Override
	public void addCuenta(Cliente cliente) {
		gestionClientes.addCliente(cliente);
	}*/

    @Override
    public void addClienteParticular(Usuario usuario, Direccion direccion, String apellidos) {
        gestionClientes.addClienteParticular(usuario, direccion, apellidos);
    }

    @Override
    public void addClienteEmpresa(Usuario usuario, Direccion direccion) {
        gestionClientes.addClienteEmpresa(usuario, direccion);
    }

    @Override
    public void delCuenta(String nif) {
        try {
            gestionClientes.borrarCliente(nif);
            System.out.println("Cliente borrado con éxito.");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addLlamada(String nif, int numero, double duracion) {
        gestionLlamadas.añadirLlamada(nif,numero,duracion);
    }

    @Override
    public void modificarTarifa(String nif, Tarifa tarifa){
        gestionClientes.cambiarTarifaCliente(nif, tarifa);
    }

    /*@Override
    public void modTarifaTardesReducidas(String nif){
        gestionClientes.cambiarTarifaTardesReducidas(nif);
    }
    */
    @Override
    public void emitirFactura(String nif){
        gestionFacturas.emitirFacturaCliente(nif);
    }

    //InterrogaModelo

    @Override
    public void mostrarClientes(){
        try {
            int i = 1;
            for (String nif : gestionClientes.getClientes().keySet()) {
                System.out.println("Cliente " + i++ + ":");
                System.out.println(gestionClientes.getCliente(nif));
            }
            if (i == 1)
                System.out.println("No hay clientes aún guardados.");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void mostrarDatosCliente(String nif){
        try {
            System.out.println(gestionClientes.getCliente(nif));
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void mostrarDatosFactura(){
//        ComprobarDato codigoNumerico = dato -> isNum(dato);
//        datoAObtener.withConsulta("Codigo de factura: ").withMensajeError("El codigo de la factura tiene que ser numerico.");
//        int codigo = Integer.parseInt(datoAObtener.comprobarDato(codigoNumerico, scannerPalabra));
        int codigo = 1;
        try {
            System.out.println(gestionFacturas.getFactura(codigo));
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void mostrarFacturasCliente(String nif){
        try {
            int i = 1;
            for (Integer codigo : gestionFacturas.getFacturasCliente(nif).keySet()) {
                System.out.println("Factura " + i++ + ":");
                System.out.println(gestionFacturas.getFactura(codigo));
            }
            if (i == 1)
                System.out.println("No hay facturas asociadas aún al cliente " + gestionClientes.getCliente(nif) + ".");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void mostrarLlamadasCliente(String nif){
        try {
            Map<Periodo, List<Llamada>> llamadas = gestionLlamadas.getLlamadasCliente(nif);
            for (Periodo periodo : llamadas.keySet()) {
                System.out.println("Llamadas hechas en el periodo " + periodo + ":");
                for (Llamada llamada : llamadas.get(periodo))
                    System.out.println(llamada);
            }
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void mostrarClientesEntreFechas() {
        //        Periodo periodo = getPeriod();
        Periodo periodo = null;
        Set<Cliente> setClientes = new HashSet<>();
        setClientes.addAll(gestionClientes.getClientes().values());

        Set<Cliente> setClientesIntervalo = gestionClientes.filterClientsByDate(setClientes, periodo);

        int i = 0;
        for (Cliente cliente : setClientesIntervalo) {
            System.out.println(cliente);
            i++;
        }
        if (i == 0) System.out.println("No se agregaron clientes en ese periodo.");
    }

    public void mostrarLlamadasEntreFechas(String nif) {
//        Periodo periodo = getPeriod();
        Periodo periodo = null;
        Set<Llamada> setLlamadas = new HashSet<>();
        try {
            for (List<Llamada> listaLlamadas : gestionLlamadas.getLlamadasCliente(nif).values()) {
                setLlamadas.addAll(listaLlamadas);
            }

            Set<Llamada> setLlamadasIntervalo = gestionLlamadas.filterCallsByDate(setLlamadas, periodo);

            int i = 0;
            for (Llamada llamada : setLlamadasIntervalo) {
                System.out.println(llamada);
                i++;
            }

            if (i == 0) System.out.println("No se agregaron clientes en ese periodo.");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public void mostrarFacturasEntreFechas(String nif) {
//        Periodo periodo = getPeriod();
        Periodo periodo = null;
        Set<Factura> setFacturas = new HashSet<>();
        try {
            setFacturas.addAll(gestionFacturas.getFacturasCliente(nif).values());
            Set<Factura> setFacturasIntervalo = gestionFacturas.filterBillsByDate(setFacturas, periodo);

            int i = 0;
            for (Factura factura : setFacturasIntervalo) {
                System.out.println(factura);
                i++;
            }

            if (i == 0) System.out.println("No se agregaron clientes en ese periodo.");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}