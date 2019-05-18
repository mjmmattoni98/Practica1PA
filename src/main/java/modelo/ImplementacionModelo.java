package modelo;
import java.time.LocalDateTime;
import java.util.*;

import empresa.telefonia.*;
import entrada.salida.ComprobarDato;
import gestion.datos.GestionClientes;
import gestion.datos.GestionFacturas;
import gestion.datos.GestionLlamadas;
import vista.InformaVista;


public class ImplementacionModelo implements CambioModelo, InterrogaModelo {
    private InformaVista vista;
    private GestionClientes gestionClientes = new GestionClientes();
    private GestionLlamadas gestionLlamadas = new GestionLlamadas();
    private GestionFacturas gestionFacturas = new GestionFacturas();
    private FabricadoTarifa fabricadoTarifa = new FabricadoTarifa();

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
    public void modTarifaDomingosGratis(String nif){
        Tarifa tarifa = gestionClientes.getCliente(nif).getTarifa();
        tarifa = fabricadoTarifa.getTarifaDomingoGratis(tarifa);
        gestionClientes.cambiarTarifaCliente(nif, tarifa);
    }

    @Override
    public void modTarifaTardesReducidas(String nif){
        Tarifa tarifa = gestionClientes.getCliente(nif).getTarifa();
        tarifa = fabricadoTarifa.getTarifaTardesReducidas(tarifa, 5);
        gestionClientes.cambiarTarifaCliente(nif, tarifa);
    }

    @Override
    public void emitirFactura(String nif){
        gestionFacturas.emitirFacturaCliente(nif);
    }

    //InterrogaModelo

    @Override
    public String mostrarClientes(){
        StringBuilder clientes = new StringBuilder();
        clientes.append("<html><body><H1>Mostrar Clientes</H1>");
        try {
            int i = 1;
            for (String nif : gestionClientes.getClientes().keySet()) {
                clientes.append("<H3>Cliente " + i++ + ": </H3>");
                clientes.append(gestionClientes.getCliente(nif).toStringHtml());
            }
            clientes.append("</body></html>");
            if (i == 1)
                return "No hay clientes aún guardados.";
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return clientes.toString();
    }
    @Override
    public String mostrarDatosCliente(String nif){
        StringBuilder cliente = new StringBuilder();
        cliente.append("<html><body><H1>Mostrar Datos Cliente</H1>");
        try {
            cliente.append(gestionClientes.getCliente(nif).toStringHtml());
            cliente.append("</html></body>");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return cliente.toString();
    }
    @Override
    public String mostrarDatosFactura(int codFac){
        StringBuilder factura= new StringBuilder();
        factura.append("<html><body><H1>Mostrar datos factura</H1>");

        try {
            factura.append(gestionFacturas.getFactura(codFac).toStringHtml());
            factura.append("</html></body>");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return factura.toString();
    }
    @Override
    public String mostrarFacturasCliente(String nif){
        StringBuilder facturas = new StringBuilder();
        try {
            int i = 1;
            facturas.append("<html><body><H1>Mostrar facturas cliente</H1>");
            for (Integer codigo : gestionFacturas.getFacturasCliente(nif).keySet()) {
                facturas.append("<H3>Factura " + i++ + ":</H3>");
                facturas.append(gestionFacturas.getFactura(codigo).toStringHtml());
            }
            facturas.append("</html></body>");
            if (i == 1)
                return ("No hay facturas asociadas aún al cliente " + gestionClientes.getCliente(nif) + ".");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return facturas.toString();
    }
    @Override
    public String mostrarLlamadasCliente(String nif){
        StringBuilder mostrarLlamadas = new StringBuilder();
        try {
            Map<Periodo, List<Llamada>> llamadas = gestionLlamadas.getLlamadasCliente(nif);
            mostrarLlamadas.append("<html><body><H1>Mostrar Llamadas Cliente</H1>");
            for (Periodo periodo : llamadas.keySet()) {
                mostrarLlamadas.append("Llamadas hechas en el periodo " + periodo + ":<br>");
                for (Llamada llamada : llamadas.get(periodo))
                    mostrarLlamadas.append(llamada+"<br>");
            }
            mostrarLlamadas.append("</html></body>");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return mostrarLlamadas.toString();
    }
    @Override
    public String mostrarClientesEntreFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        StringBuilder mostrarClientes = new StringBuilder();
        Set<Cliente> setClientes = new HashSet<>();
        setClientes.addAll(gestionClientes.getClientes().values());
        Periodo periodo = new Periodo(fechaInicio,fechaFin);
        Set<Cliente> setClientesIntervalo = gestionClientes.filterClientsByDate(setClientes, periodo);

        int i = 0;
        mostrarClientes.append("<html><h1>mostrarClientesEntreFechas</h1><body>");
        for (Cliente cliente : setClientesIntervalo) {
            mostrarClientes.append("<h3>"+cliente+"</h3>");
            i++;
        }
        mostrarClientes.append("</html></body>");
        if (i == 0) return "No se agregaron clientes en ese periodo.";
        return mostrarClientes.toString();
    }

    @Override
    public String mostrarLlamadasEntreFechas(String nif, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Periodo periodo = new Periodo(fechaInicio,fechaFin);
        StringBuilder llamadas = new StringBuilder();
        Set<Llamada> setLlamadas = new HashSet<>();
        try {
            for (List<Llamada> listaLlamadas : gestionLlamadas.getLlamadasCliente(nif).values()) {
                setLlamadas.addAll(listaLlamadas);
            }

            Set<Llamada> setLlamadasIntervalo = gestionLlamadas.filterCallsByDate(setLlamadas, periodo);

            int i = 0;
            llamadas.append("<html><h1>mostrarLlamadasEntreFechas</h1><body>");
            for (Llamada llamada : setLlamadasIntervalo) {
                llamadas.append("<h3>"+llamada+"</h3>");
                i++;
            }
            llamadas.append("</html></body>");


            if (i == 0) return "No se agregaron clientes en ese periodo.";
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return llamadas.toString();
    }
    @Override
    public String mostrarFacturasEntreFechas(String nif, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        Periodo periodo = new Periodo(fechaInicio,fechaFin);
        StringBuilder facturas=new StringBuilder();
        Set<Factura> setFacturas = new HashSet<>();
        try {
            setFacturas.addAll(gestionFacturas.getFacturasCliente(nif).values());
            Set<Factura> setFacturasIntervalo = gestionFacturas.filterBillsByDate(setFacturas, periodo);

            int i = 0;
            facturas.append("<html>mostrar Facturas Entre Fechas<body>");
            for (Factura factura : setFacturasIntervalo) {
                facturas.append("<h3>"+factura+"</h3>");
                i++;
            }
            facturas.append("</html></body>");

            if (i == 0) return "No se agregaron clientes en ese periodo.";
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        return facturas.toString();
    }
}
