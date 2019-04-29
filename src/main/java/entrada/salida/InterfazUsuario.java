package entrada.salida;

import empresa.telefonia.*;
import excepciones.NIFException;
import excepciones.PeriodoException;

import gestion.datos.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import org.apache.commons.lang3.text.WordUtils;

//TODO ver como no hacer superposicion de metodos.
public class InterfazUsuario {
    private Scanner scannerLinea;
    private Scanner scannerPalabra;
    private ObtencionDato datoAObtener;
    private GestionLlamadas gestionLlamadas;
    private GestionFacturas gestionFacturas;
    private GestionClientes gestionClientes;
    private Datos datos;

    public InterfazUsuario(){
        this.datoAObtener = new ObtencionDato();
        this.scannerLinea = new Scanner(System.in);
        this.scannerPalabra = new Scanner(System.in);
        this.gestionClientes = new GestionClientes();
        this.gestionFacturas = new GestionFacturas();
        this.gestionLlamadas = new GestionLlamadas();
        this.datos = new Datos();
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream("datos.bin");
            ois = new ObjectInputStream(fis);
            datos = (Datos) ois.readObject();
            BaseDatos.setClientesBD(datos.getClientes());
            BaseDatos.setFacturasBD(datos.getFacturas());
            gestionFacturas.setCodigoFactura(datos.getCodigoFactura());
            fis.close();
            ois.close();
        } catch (ClassNotFoundException | IOException e){
            System.out.println("No hay clientes aun.");
            e.printStackTrace();
        }
    }

    //TODO eliminar switch.
    public void menu(){
        System.out.println(OpcionesMenu.getMenu());
        System.out.println("Elija una opción: ");
        int opcion = scannerPalabra.nextInt();
        ejecutarAccion(opcion);
    }

    private boolean comprobarNif(String nif) throws NIFException {
        boolean nifCorrecto;
        if (nif.length() == 9 && Character.isLetter(nif.charAt(8))) {
            try {
                Integer.parseInt(nif.substring(0, 8));
                nifCorrecto = true;
            } catch (NumberFormatException e) {
                throw new NIFException();
            }
        } else
            throw new NIFException();

        return nifCorrecto;
    }

    private void ejecutarAccion(int opcion){
        if(opcion < 0 || opcion > OpcionesMenu.values().length){
            System.out.println("Opción incorrecta. Asegurese de elegir una opción entre 0 y " + (OpcionesMenu.values().length - 1));
            menu();
        }
        else {
            String nif;
            OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
            boolean nifCorrecto = false;
            do {
                System.out.println("Introduzca un NIF válido: ");
                nif = scannerPalabra.next().toUpperCase();
                try {
                    nifCorrecto = comprobarNif(nif);
                }
                catch (NIFException e){
                    System.out.println(e);
                    System.out.println("Vuelva a introducir el NIF.");
                }
            }while (!nifCorrecto);

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

    //TODO hacer mas seguro que dice si o no.
    private void repeatMenu(){
        System.out.println("Desea realizar alguna otra acción? (SI/NO)");
        String siNo = scannerPalabra.next();
        if (siNo.equalsIgnoreCase("si"))
            menu();
        else {
            FileOutputStream fos;
            ObjectOutputStream oos;
            try {
                datos.setClientes(BaseDatos.getClientesBD());
                datos.setFacturas(BaseDatos.getFacturasBD());
                datos.setCodigoFactura(gestionFacturas.getCodigoFactura());
                fos = new FileOutputStream("datos.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(this.datos);
                fos.close();
                oos.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Gracias y hasta pronto!!!");
        }
    }

    private void crearCuenta(String nif) {
        System.out.println("Qué tipo de cliente desea añadir?");
        System.out.println(TipoCliente.opciones());
        TipoCliente tipo;
        int opcion = scannerPalabra.nextInt();
        tipo = TipoCliente.getOpcion(opcion);
        System.out.println("Nombre: ");
        String nombre = WordUtils.capitalizeFully(scannerLinea.nextLine());
        String apellidos = "";
        if (tipo == TipoCliente.PARTICULAR){
            System.out.println("Apellidos: ");
            apellidos = WordUtils.capitalizeFully(scannerLinea.nextLine());
        }
        ComprobarDato cpLongitud = dato -> dato.length() == 5 && isNum(dato);
        datoAObtener.withConsulta("CP: ").withMensajeError("El código postal tiene que estar compuesto por 5 números y ser numerico.");
        int cp = Integer.parseInt(datoAObtener.comprobarDato(cpLongitud, scannerPalabra));
        System.out.println("Provincia: ");
        String provincia = WordUtils.capitalizeFully(scannerLinea.nextLine());
        System.out.println("Población: ");
        String poblacion = WordUtils.capitalizeFully(scannerLinea.nextLine());
        ComprobarDato formatoCorreoElectronico = dato -> dato.contains("@");
        datoAObtener.withConsulta("Correo electronico: ").withMensajeError("El correo electronico tiene que tener el simbolo '@'.");
        String correoElectronico = datoAObtener.comprobarDato(formatoCorreoElectronico, scannerPalabra);
        //TODO hacer que al crear la cuenta, el cliente decida si decide quedarse con la tarifa básica o desea modificarla.
        try{
            FabricadoCliente fabrica = new FabricadoCliente(nif,nombre,cp,provincia,poblacion,correoElectronico, Tarifa.tarifaBasica, apellidos);
            Cliente cliente = fabrica.getCliente(tipo);
            gestionClientes.addCliente(cliente);
            System.out.println("El cliente se ha añadido correctamente.");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private void borrarCuenta(String nif){
        try {
            gestionClientes.borrarCliente(nif);
            System.out.println("Cliente borrado con éxito.");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private void cambiarTarifa(String nif) {
        double nuevaTarifa = 0;
        System.out.println("Qué tipo de tarifa desea aplicar?");
        System.out.println(TipoTarifa.opciones());
        TipoTarifa tipo;
        int opcion = scannerPalabra.nextInt();
        tipo = TipoTarifa.getOpcion(opcion);
        if (tipo == TipoTarifa.TARDES_REDUCIDAS){
            nuevaTarifa = 5;
        }
        try{
            FabricadoTarifa fabrica = new FabricadoTarifa(GestionClientes.getClientesBD().get(nif).getTarifa(),nuevaTarifa);
            Tarifa tarifa = fabrica.getTarifa(tipo);
            gestionClientes.cambiarTarifaCliente(nif, tarifa);
            System.out.println("La tarifa ha sido modificada con éxito.");
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            repeatMenu();
        }
    }

    private void emitirFactura(String nif){
        Factura factura;
        try{
            factura = gestionFacturas.emitirFacturaCliente(nif);
            System.out.println("Hecho");
            System.out.println("Desea ver la factura? (SI/NO)");
            mostrarInformacion(factura);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private void guardarLlamada(String nif){
        ComprobarDato numeroLongitud = dato -> dato.length() == 9 && isNum(dato);
        datoAObtener.withConsulta("Numero al que ha llamado: ").withMensajeError("El numero tiene que estar compuesto por 9 números y ser numerico.");
        int numero = Integer.parseInt(datoAObtener.comprobarDato(numeroLongitud, scannerPalabra));
        ComprobarDato duracionNumerica = dato -> isNum(dato) && Double.parseDouble(dato) > 0;
        datoAObtener.withConsulta("Duracion de la llamada (en minutos): ").withMensajeError("La duracion tiene que ser numerica.");
        double duracion = Double.parseDouble(datoAObtener.comprobarDato(duracionNumerica, scannerPalabra));
        try {
            Llamada llamada = gestionLlamadas.añadirLlamada(nif, numero, duracion);
            System.out.println("Desea ver la información de la llamada? (SI/NO)");
            mostrarInformacion(llamada);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private void mostrarClientes(){
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
        finally {
            repeatMenu();
        }
    }

    private void mostrarDatosCliente(String nif){
        try {
            System.out.println(gestionClientes.getCliente(nif));
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private void mostrarDatosFactura(){
        ComprobarDato codigoNumerico = dato -> isNum(dato);
        datoAObtener.withConsulta("Codigo de factura: ").withMensajeError("El codigo de la factura tiene que ser numerico.");
        int codigo = Integer.parseInt(datoAObtener.comprobarDato(codigoNumerico, scannerPalabra));
        try {
            System.out.println(gestionFacturas.getFactura(codigo));
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private void mostrarFacturasCliente(String nif){
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
        finally {
            repeatMenu();
        }
    }

    private void mostrarLlamadasCliente(String nif){
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
        finally {
            repeatMenu();
        }
    }

    private void mostrarClientesEntreFechas() {
        Periodo periodo = getPeriod();
        Set<Cliente> setClientes = new HashSet<>();
        setClientes.addAll(gestionClientes.getClientes().values());

        Set<Cliente> setClientesIntervalo = gestionClientes.filterClientsByDate(setClientes, periodo);

        int i = 0;
        for (Cliente cliente : setClientesIntervalo) {
            System.out.println(cliente);
            i++;
        }

        if (i == 0)
            System.out.println("No se agregaron clientes en ese periodo.");

        repeatMenu();
    }

    private void mostrarLlamadasEntreFechas(String nif) {
        Periodo periodo = getPeriod();
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

            if (i == 0)
                System.out.println("No se agregaron clientes en ese periodo.");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private void mostrarFacturasEntreFechas(String nif) {
        Periodo periodo = getPeriod();
        Set<Factura> setFacturas = new HashSet<>();
        try {
            setFacturas.addAll(gestionFacturas.getFacturasCliente(nif).values());
            Set<Factura> setFacturasIntervalo = gestionFacturas.filterBillsByDate(setFacturas, periodo);

            int i = 0;
            for (Factura factura : setFacturasIntervalo) {
                System.out.println(factura);
                i++;
            }

            if (i == 0)
                System.out.println("No se agregaron clientes en ese periodo.");
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        finally {
            repeatMenu();
        }
    }

    private <T> void mostrarInformacion(T o){
        String opcion = scannerPalabra.next();
        if ("si".equalsIgnoreCase(opcion))
            System.out.println(o);
    }

    private boolean isNum(String number){
        boolean isNumber = true;
        try{
            Double.parseDouble(number);
        }
        catch (NumberFormatException e){
            isNumber = false;
        }
        return isNumber;
    }

    private Periodo getPeriod() {
        boolean fechasCorrectas = false;
        LocalDateTime fechaInicio = null;
        LocalDateTime fechaFin = null;
        do {
            try {
                System.out.println("Fecha inicial: (yyyy-MM-ddThh:mm:ss)");
                fechaInicio = LocalDateTime.parse(scannerPalabra.next());
                System.out.println("Fecha final: (yyyy-MM-ddThh:mm:ss)");
                fechaFin = LocalDateTime.parse(scannerPalabra.next());

                if (fechaInicio.isAfter(fechaFin))
                    throw new PeriodoException();
                fechasCorrectas = true;
            } catch (PeriodoException e) {
                System.out.println(e);
                System.out.println("Por favor, vuelva a introducir las fechas.");
            } catch (DateTimeParseException e){
                System.out.println("Lo siento, no le he entendido bien. Vuelva a introducir las fechas otra vez.");
            }
        } while (!fechasCorrectas);
        return new Periodo(fechaInicio, fechaFin);
    }
}
