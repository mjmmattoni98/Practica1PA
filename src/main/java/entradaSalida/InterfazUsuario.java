package entradaSalida;


import empresaTelefonia.*;
import excepciones.NIFException;
import excepciones.TarifaException;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InterfazUsuario {
    Scanner scanner;
    Scanner scannerPalabra;
    GestionClientes gestionClientes;

    public InterfazUsuario(){
        this.scanner = new Scanner(System.in);
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
//        System.out.println("GestionClientes o Particular? ");
//        boolean particular = scanner.next().equalsIgnoreCase("particular");
        ComprobarDato soyEmpresa = dato -> dato.equalsIgnoreCase("empresa");
        ComprobarDato soyParticular = dato -> dato.equalsIgnoreCase("particular");
        ObtencionDato datoAObtener = new ObtencionDato("Empresa o Particular?", "No le he entendido.");
        boolean particular = datoAObtener.comprobarDato(soyEmpresa, soyParticular, scannerPalabra).equalsIgnoreCase("particular");
        /*StringBuilder nombre = new StringBuilder();
        System.out.println("Primer nombre: ");
        nombre.append(scanner.next());
        System.out.println("Segundo nombre (si no tiene escriba no): ");
        String sNombre = scanner.next();
        nombre.append((sNombre.equalsIgnoreCase("no"))?"":" " + sNombre);*/
        System.out.println("Nombre: ");
        String nombre = scanner.nextLine();
        StringBuilder apellidos = new StringBuilder();
        if (particular){
            System.out.println("Primer apellido: ");
            apellidos.append(scanner.next());
            System.out.println("Segundo apellido (si no tiene escriba no): ");
            String sApellido = scanner.next();
            apellidos.append((sApellido.equalsIgnoreCase("no"))?"":" " + sApellido);
        }
//        System.out.println("CP: ");
//        int cp = scanner.nextInt();
        // TODO Dos scanners: para lineas y para texto
        ComprobarDato cpNumerico = dato -> dato.length() == 5;
        datoAObtener = new ObtencionDato("CP: ", "El código postal tiene que estar compuesto por 5 números");
        int cp = Integer.parseInt(datoAObtener.comprobarDato(cpNumerico, scannerPalabra));
        System.out.println("Provincia (si está compuesta por varias palabras, escríbalas separadas por un guión bajo): ");
        String provincia = scanner.next();
        System.out.println("Población (si está compuesta por varias palabras, escríbalas separadas por un guión bajo): ");
        String poblacion = scanner.next();
        System.out.println("Correo electrónico: ");
        String correoElectronico = scanner.next();
        System.out.println("Tarifa: ");
        double tarifa = Double.parseDouble(scanner.next());
        if (particular){
            try{
                gestionClientes.addClienteParticular(nif, nombre.toString(), cp, provincia, poblacion, correoElectronico, tarifa, apellidos.toString());
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
                gestionClientes.addClienteEmpresa(nif, nombre.toString(), cp, provincia, poblacion, correoElectronico, tarifa);
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
        double tarifa = Double.parseDouble(scanner.next());
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
        int numero = scanner.nextInt();
        System.out.println("Duración de la llamada: ");
        double duracion = Double.parseDouble(scanner.next());
        Llamada llamada = gestionClientes.añadirLlamada(nif, numero, duracion);
        System.out.println("Desea ver la información de la llamada? ");
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
        int codigo = scanner.nextInt();
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
        String opcion = scanner.next();
        if ("si".equalsIgnoreCase(opcion))
            System.out.println(o);
        repeatMenu();
    }

}
