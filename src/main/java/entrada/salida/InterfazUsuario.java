package entrada.salida;


import empresa.telefonia.*;
import excepciones.NIFException;
import excepciones.TarifaException;
import gestion.datos.BaseDatos;
import gestion.datos.GestionClientes;
import gestion.datos.GestionFacturas;
import gestion.datos.GestionLlamadas;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InterfazUsuario {
    Scanner scannerLinea;
    Scanner scannerPalabra;
    ObtencionDato datoAObtener;
    GestionLlamadas gestionLlamadas;
    GestionFacturas gestionFacturas;
    GestionClientes gestionClientes;

    public InterfazUsuario(){
        this.datoAObtener = new ObtencionDato();
        this.scannerLinea = new Scanner(System.in);
        this.scannerPalabra = new Scanner(System.in);
        this.gestionClientes = new GestionClientes();
        this.gestionFacturas = new GestionFacturas();
        this.gestionLlamadas = new GestionLlamadas();
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream("clientes.bin");
            ois = new ObjectInputStream(fis);
            gestionClientes = (GestionClientes) ois.readObject();
            fis = new FileInputStream("llamadas.bin");
            ois = new ObjectInputStream(fis);
            gestionLlamadas = (GestionLlamadas) ois.readObject();
            fis = new FileInputStream("facturas.bin");
            ois = new ObjectInputStream(fis);
            gestionFacturas = (GestionFacturas) ois.readObject();
            fis.close();
            ois.close();
        } catch (ClassNotFoundException | IOException e){
            System.out.println("No hay clientes aun.");
        }
    }

    public void menu() throws NIFException{
        System.out.println(OpcionesMenu.getMenu());
        System.out.println("Elija una opción: ");
        int opcion = scannerPalabra.nextInt();
        ejecutarAccion(opcion);
    }

    private void ejecutarAccion(int opcion) throws NIFException{
        if(opcion < 0 || opcion > OpcionesMenu.values().length){
            System.out.println("Opción incorrecta. Asegurese de elegir una opción entre 0 y " + (OpcionesMenu.values().length - 1));
            menu();
        }
        else {
            OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
            System.out.println("NIF (si va a mostrar los clientes o los datos de una factura, escriba cualquier palabra): ");
            String nif = scannerPalabra.next();
            switch (opcionMenu) {
                case MOSTRAR_CLIENTES:
                    mostrarClientes();
                    break;
                case MOSTRAR_DATOS_FACTURA:
                    mostrarDatosFactura();
                    break;
                case CREAR_CUENTA:
                    crearCuenta(nif);
                    break;
                case BORRAR_CUENTA:
                    borrarCuenta(nif);
                    break;
                case CAMBIAR_TARIFA:
                    cambiarTarifa(nif);
                    break;
                case EMITIR_FACTURA:
                    emitirFactura(nif);
                    break;
                case DAR_ALTA_LLAMADA:
                    guardarLlamada(nif);
                    break;
                case MOSTRAR_FACTURAS_CLIENTE:
                    mostrarFacturasCliente(nif);
                    break;
                case MOSTRAR_LLAMADAS_CLIENTE:
                    mostrarLlamadasCliente(nif);
                    break;
                case MOSTRAR_DATOS_CLIENTE:
                    mostrarDatosCliente(nif);
                    break;
                case MOSTRAR_CLIENTES_ENTRE_FECHAS:
                    mostrarClientesEntreFechas();
                    break;
                case MOSTRAR_LLAMADAS_ENTRE_FECHAS:
                    mostrarLlamadasEntreFechas(nif);
                    break;
                case MOSTRAR_FACTURAS_ENTRE_FECHAS:
                    mostrarFacturasEntreFechas(nif);
                    break;
            }
        }
    }

    private void repeatMenu() throws NIFException{
        System.out.println("Desea realizar alguna otra acción? (SI/NO)");
        String siNo = scannerPalabra.next();
        if (siNo.equalsIgnoreCase("si"))
            menu();
        else {
            FileOutputStream fos;
            ObjectOutputStream oos;
            try {
                fos = new FileOutputStream("clientes.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(this.gestionClientes);
                fos = new FileOutputStream("llamadas.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(this.gestionLlamadas);
                fos = new FileOutputStream("facturas.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(this.gestionFacturas);
                fos.close();
                oos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Gracias y hasta pronto!!!");
        }
    }

    private void crearCuenta(String nif) throws NIFException {
        ComprobarDato soyEmpresa = dato -> dato.equalsIgnoreCase("empresa");
        ComprobarDato soyParticular = dato -> dato.equalsIgnoreCase("particular");
        datoAObtener.withConsulta("Empresa o Particular?").withMensajeError("No le he entendido.");
        boolean particular = datoAObtener.comprobarDato(soyEmpresa, soyParticular, scannerLinea).equalsIgnoreCase("particular");
        System.out.println("Nombre: ");
        String nombre = scannerLinea.nextLine();
        String apellidos = "";
        if (particular){
            System.out.println("Apellidos: ");
            apellidos = scannerLinea.nextLine();
        }
        ComprobarDato cpNumerico = dato -> dato.length() == 5;
        datoAObtener.withConsulta("CP: ").withMensajeError("El código postal tiene que estar compuesto por 5 números");
        int cp = Integer.parseInt(datoAObtener.comprobarDato(cpNumerico, scannerLinea));
        System.out.println("Provincia: ");
        String provincia = scannerLinea.nextLine();
        System.out.println("Población: ");
        String poblacion = scannerLinea.nextLine();
        System.out.println("Correo electrónico: ");
        String correoElectronico = scannerPalabra.next();
        ComprobarDato tarifaPositiva = dato -> Double.parseDouble(dato) > 0;
        datoAObtener.withConsulta("Tarifa: ").withMensajeError("La tarifa no puede ser negativa.");
        double tarifa = Double.parseDouble(datoAObtener.comprobarDato(tarifaPositiva, scannerLinea));
        if (particular){
            try{
                gestionClientes.addClienteParticular(nif, nombre, cp, provincia, poblacion, correoElectronico, tarifa, apellidos);
                System.out.println("El cliente se ha añadido correctamente.");
            }
            catch (TarifaException e){
                e.printStackTrace();
            }
            finally {
                repeatMenu();
            }
        }
        else {
            try {
                gestionClientes.addClienteEmpresa(nif, nombre, cp, provincia, poblacion, correoElectronico, tarifa);
                System.out.println("El cliente se ha añadido correctamente.");
            } catch (TarifaException e) {
                e.printStackTrace();
            }
            finally {
                repeatMenu();
            }
        }
    }

    private void borrarCuenta(String nif)throws NIFException{
        gestionClientes.borrarCliente(nif);
        System.out.println("Cliente borrado con éxito.");
        repeatMenu();
    }

    private void cambiarTarifa(String nif)throws NIFException{
        System.out.println("Introduzca la nueva tarifa: ");
        double tarifa = Double.parseDouble(scannerPalabra.next());
        try{
            gestionClientes.cambiarTarifaCliente(nif, tarifa);
        }
        catch (TarifaException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private void emitirFactura(String nif) throws NIFException{
        Factura factura;
        try{
            factura = gestionFacturas.emitirFacturaCliente(nif);
            System.out.println("Hecho");
            System.out.println("Desea ver la factura? ");
            mostrarInformacion(factura);
        }
        catch (TarifaException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private void guardarLlamada(String nif)throws NIFException{
        System.out.println("Número al que ha llamado: ");
        int numero = scannerPalabra.nextInt();
        System.out.println("Duración de la llamada: ");
        double duracion = Double.parseDouble(scannerPalabra.next());
        Llamada llamada = gestionLlamadas.añadirLlamada(nif, numero, duracion);
        System.out.println("Desea ver la información de la llamada? (SI/NO)");
        mostrarInformacion(llamada);
        repeatMenu();
    }

    private void mostrarClientes()throws NIFException{
        int i = 1;
        for (String nif : gestionClientes.getClientes().keySet()){
            System.out.println("Cliente " + i++ + ":");
            System.out.println(gestionClientes.getCliente(nif));
        }
        if (i == 1)
            System.out.println("No hay clientes aún guardados.");
        repeatMenu();
    }

    private void mostrarDatosCliente(String nif)throws NIFException{
        System.out.println(gestionClientes.getCliente(nif));
        repeatMenu();
    }

    private void mostrarDatosFactura()throws NIFException{
        System.out.println("Codigo de factura: ");
        int codigo = scannerPalabra.nextInt();
        System.out.println(gestionFacturas.getFactura(codigo));
        repeatMenu();
    }

    private void mostrarFacturasCliente(String nif)throws NIFException{
        int i = 1;
        for (Integer codigo : gestionFacturas.getFacturasCliente(nif).keySet()){
            System.out.println("Factura " + i++ + ":");
            System.out.println(gestionFacturas.getFactura(codigo));
        }
        if (i == 1)
            System.out.println("No hay facturas asociadas aún al cliente " + gestionClientes.getCliente(nif) + ".");
        repeatMenu();
    }

    private void mostrarLlamadasCliente(String nif)throws NIFException{
        Map<Periodo, List<Llamada>> llamadas = gestionLlamadas.getLlamadasCliente(nif);
        for (Periodo periodo : llamadas.keySet()){
            System.out.println("Llamadas hechas en el periodo " + periodo + ":");
            for (Llamada llamada : llamadas.get(periodo))
                System.out.println(llamada);
        }
        repeatMenu();
    }

    private void mostrarClientesEntreFechas() {
        LocalDateTime[] intervaloFechas = intervaloFecha();
        Set<Cliente> setClientes= null;
        for(Cliente cliente : gestionClientes.getClientes().values()) {
            setClientes.add(cliente);
        }

        Set<Cliente> setClientesIntervalo = gestionClientes.getConjuntoObjetosEntreFechas(setClientes,intervaloFechas[0],intervaloFechas[1]);

        for (Cliente cliente : setClientesIntervalo) {
            System.out.println(cliente.getNombre());
        }

    }

    private void mostrarLlamadasEntreFechas(String nif) {
        LocalDateTime[] intervaloFechas = intervaloFecha();
        Set<Llamada> setLlamadas=null;
        for(List<Llamada> listaLlamadas : gestionLlamadas.getLlamadasCliente(nif).values()) {
            for (Llamada llamada : listaLlamadas) {
                setLlamadas.add(llamada);
            }
        }
        Set<Llamada> setLlamadasIntervalo = gestionLlamadas.getConjuntoObjetosEntreFechas(setLlamadas,intervaloFechas[0],intervaloFechas[1]);

        for (Llamada llamada : setLlamadasIntervalo) {
            System.out.println(llamada.toString());
        }
    }

    private void mostrarFacturasEntreFechas(String nif) {
        LocalDateTime[] intervaloFechas = intervaloFecha();
        Set<Cliente> setFacturas= null;
        for(Cliente factura : gestionFacturas.getFacturas().values()) {
            setFacturas.add(factura);
        }
        Set<Cliente> setFacturasIntervalo = gestionFacturas.getConjuntoObjetosEntreFechas(setFacturas,intervaloFechas[0],intervaloFechas[1]);

        for (Cliente cliente : setFacturasIntervalo) {
            System.out.println(cliente.getNombre());
        }
    }

    private <T> void mostrarInformacion(T o)throws NIFException{
        String opcion = scannerPalabra.next();
        if ("si".equalsIgnoreCase(opcion))
            System.out.println(o);
        repeatMenu();
    }

    private LocalDateTime[] intervaloFecha() {
        String fecha;
        LocalDateTime[] intervaloFechas = new LocalDateTime[2];
        DateTimeFormatter formatter;
        System.out.println("Fecha inicial, dd-MM-yyyy HH:mm:ss:");
        fecha = scannerPalabra.next();
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        intervaloFechas[0] = LocalDateTime.parse(fecha, formatter);
        System.out.println("Fecha final, dd-MM-yyyy HH:mm:ss:");
        fecha = scannerPalabra.next();
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        intervaloFechas[1] = LocalDateTime.parse(fecha, formatter);
        return intervaloFechas;
    }
}
