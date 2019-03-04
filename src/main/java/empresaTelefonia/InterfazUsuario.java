package empresaTelefonia;


import excepciones.TarifaException;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InterfazUsuario {
    Scanner scanner;
    Empresa empresa;

    public InterfazUsuario(){
        this.scanner = new Scanner(System.in);
        this.empresa = new Empresa();
    }

    public void menu(){
        System.out.println(OpcionesMenu.getMenu());
        System.out.println("Elija una opción: ");
        int opción = scanner.nextInt();
        ejecutarAccion(opción);
    }

    private void ejecutarAccion(int opcion) {
        OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
        System.out.println("Si va a mostrar los clientes o los datos de una factura, escriba cualquier palabra.");
        System.out.println("NIF: ");
        String nif = scanner.next();
        switch (opcionMenu){
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
        System.out.println("Desea realizar alguna otra acción? ");
        String siNo = scanner.next().toLowerCase();
        if (siNo.equals("si"))
            menu();
        else
            System.out.println("Gracias y hasta pronto!!!");
    }

    private void crearCuenta(String nif){
        System.out.println("Empresa o Particular? ");
        boolean particular = scanner.next().toLowerCase().equals("particular");
        StringBuilder nombre = new StringBuilder();
        System.out.println("Primer nombre: ");
        nombre.append(scanner.next());
        System.out.println("Segundo nombre (si no tiene escriba no): ");
        String sNombre = scanner.next().toLowerCase();
        nombre.append((sNombre.equals("no"))?"":" " + sNombre);
        StringBuilder apellidos = new StringBuilder();
        if (particular){
            System.out.println("Primer apellido: ");
            apellidos.append(scanner.next());
            System.out.println("Segundo apellido (si no tiene escriba no): ");
            String sApellido = scanner.next().toLowerCase();
            apellidos.append((sApellido.equals("no"))?"":" " + sApellido);
        }
        System.out.println("CP: ");
        int cp = scanner.nextInt();
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
                empresa.añadirClienteParticular(nif, nombre.toString(), cp, provincia, poblacion, correoElectronico, tarifa, apellidos.toString());
            }
            catch (TarifaException e){
                e.printStackTrace();
            }
        }
        try{
            empresa.añadirClienteEmpresa(nif, nombre.toString(), cp, provincia, poblacion, correoElectronico, tarifa);
        }
        catch (TarifaException e){
            e.printStackTrace();
        }
        System.out.println("El cliente se ha añadido correctamente.");
    }

    private void borrarCuenta(String nif){
        empresa.borrarCliente(nif);
        System.out.println("Cliente borrado con éxito.");
    }

    private void cambiarTarifa(String nif){
        System.out.println("Introduzca la nueva tarifa: ");
        double tarifa = Double.parseDouble(scanner.next());
        try{
            empresa.cambiarTarifaCliente(nif, tarifa);
        }
        catch (TarifaException e){
            e.printStackTrace();
        }

    }

    private void emitirFactura(String nif) {
        Factura factura = null;
        try{
            factura = empresa.emitirFacturaCliente(nif);
            System.out.println("Hecho");
            System.out.println("Desea ver la factura? ");
            mostrarInformacion(factura);
        }
        catch (TarifaException e){
            e.printStackTrace();
        }
    }

    private void guardarLlamada(String nif){
        System.out.println("Número al que ha llamado: ");
        int numero = scanner.nextInt();
        System.out.println("Duración de la llamada: ");
        int duracion = scanner.nextInt();
        Llamada llamada = empresa.añadirLlamada(nif, numero, duracion);
        System.out.println("Desea ver la información de la llamada? ");
        mostrarInformacion(llamada);
    }

    private void mostrarClientes(){
        int i = 1;
        for (String nif : empresa.getClientes().keySet()){
            System.out.println("Cliente " + i++ + ":");
            System.out.println(empresa.getCliente(nif));
        }
        if (i == 1)
            System.out.println("No hay clientes aún guardados.");
    }

    private void mostrarDatosCliente(String nif){
        System.out.println(empresa.getCliente(nif));
    }

    private void mostrarDatosFactura(){
        System.out.println("Codigo de factura: ");
        int codigo = scanner.nextInt();
        System.out.println(empresa.getFactura(codigo));
    }

    private void mostrarFacturasCliente(String nif){
        int i = 1;
        for (Integer codigo : empresa.getFacturasCliente(nif).keySet()){
            System.out.println("Factura " + i++ + ":");
            System.out.println(empresa.getFactura(codigo));
        }
        if (i == 1)
            System.out.println("No hay facturas asociadas aún al cliente " + empresa.getCliente(nif) + ".");
    }

    private void mostrarLlamadasCliente(String nif){
        Map<Periodo, List<Llamada>> llamadas = empresa.getLlamadasCliente(nif);
        for (Periodo periodo : llamadas.keySet()){
            System.out.println("Llamadas hechas en el periodo " + periodo + ":");
            for (Llamada llamada : llamadas.get(periodo))
                System.out.println(llamada);
        }
    }

    private void mostrarInformacion(Object o){
        String opcion = scanner.next().toLowerCase();
        if ("si".equals(opcion))
            System.out.println(o);
    }

}
