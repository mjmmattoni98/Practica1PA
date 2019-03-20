package entradaSalida;


import empresaTelefonia.*;
import excepciones.NIFException;
import excepciones.TarifaException;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InterfazUsuario {
    Scanner scannerLinea;
    Scanner scannerPalabra;
    GestionClientes gestionClientes;
    ObtencionDato datoAObtener;

    public InterfazUsuario(){
        this.datoAObtener = new ObtencionDato();
        this.scannerLinea = new Scanner(System.in);
        this.scannerPalabra = new Scanner(System.in);
        this.gestionClientes = new GestionClientes();
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream("empresaTelefonia.bin");
            ois = new ObjectInputStream(fis);
            this.gestionClientes = (GestionClientes) ois.readObject();
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
            }
        }
    }

    private void repeatMenu() throws NIFException{
        System.out.println("Desea realizar alguna otra acción? SI/NO");
        String siNo = scannerPalabra.next();
        if (siNo.equalsIgnoreCase("si"))
            menu();
        else {
            FileOutputStream fos;
            ObjectOutputStream oos;
            try {
                fos = new FileOutputStream("empresaTelefonia.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(this.gestionClientes);
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
        boolean particular = datoAObtener.comprobarDato(soyEmpresa, soyParticular, scannerPalabra).equalsIgnoreCase("particular");
        System.out.println("Nombre: ");
        String nombre = scannerLinea.nextLine();
        String apellidos = "";
        if (particular){
            System.out.println("Apellidos: ");
            apellidos = scannerLinea.nextLine();
        }
        ComprobarDato cpNumerico = dato -> dato.length() == 5;
        datoAObtener.withConsulta("CP: ").withMensajeError("El código postal tiene que estar compuesto por 5 números");
        int cp = Integer.parseInt(datoAObtener.comprobarDato(cpNumerico, scannerPalabra));
        System.out.println("Provincia: ");
        String provincia = scannerLinea.nextLine();
        System.out.println("Población: ");
        String poblacion = scannerLinea.nextLine();
        System.out.println("Correo electrónico: ");
        String correoElectronico = scannerPalabra.next();
        System.out.println("Tarifa: ");
        double tarifa = Double.parseDouble(scannerPalabra.next());
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
            factura = gestionClientes.emitirFacturaCliente(nif);
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
        Llamada llamada = gestionClientes.añadirLlamada(nif, numero, duracion);
        System.out.println("Desea ver la información de la llamada? SI/NO");
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
        System.out.println(gestionClientes.getFactura(codigo));
        repeatMenu();
    }

    private void mostrarFacturasCliente(String nif)throws NIFException{
        int i = 1;
        for (Integer codigo : gestionClientes.getFacturasCliente(nif).keySet()){
            System.out.println("Factura " + i++ + ":");
            System.out.println(gestionClientes.getFactura(codigo));
        }
        if (i == 1)
            System.out.println("No hay facturas asociadas aún al cliente " + gestionClientes.getCliente(nif) + ".");
        repeatMenu();
    }

    private void mostrarLlamadasCliente(String nif)throws NIFException{
        Map<Periodo, List<Llamada>> llamadas = gestionClientes.getLlamadasCliente(nif);
        for (Periodo periodo : llamadas.keySet()){
            System.out.println("Llamadas hechas en el periodo " + periodo + ":");
            for (Llamada llamada : llamadas.get(periodo))
                System.out.println(llamada);
        }
        repeatMenu();
    }

    private void mostrarInformacion(Object o)throws NIFException{
        String opcion = scannerPalabra.next();
        if ("si".equalsIgnoreCase(opcion))
            System.out.println(o);
        repeatMenu();
    }

}
