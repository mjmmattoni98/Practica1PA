package empresaTelefonia;


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

    private void ejecutarAccion(int opcion){
        OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
        switch (opcionMenu){
            case CREAR_CUENTA:
                crearCuenta();
                break;
            case BORRAR_CUENTA:
                borrarCuenta();
                break;
            case CAMBIAR_TARIFA:
                cambiarTarifa();
                break;
            case EMITIR_FACTURA:
                emitirFactura();
                break;
            case DAR_ALTA_LLAMADA:
                guardarLlamada();
                break;
            case MOSTRAR_CLIENTES:
                mostrarClientes();
                break;
            case MOSTRAR_DATOS_CLIENTE:
                mostrarDatosCliente();
                break;
            case MOSTRAR_DATOS_FACTURA:
                mostrarDatosFactura();
                break;
            case MOSTRAR_FACTURAS_CLIENTE:
                mostrarFacturasCliente();
                break;
            case MOSTRAR_LLAMADAS_CLIENTE:
                mostrarLlamadasCliente();
                break;
             default:
                 System.out.println("Error en el switch.");
        }
    }

    private void crearCuenta(){
        System.out.println("Empresa o particular? ");
        boolean particular = scanner.next().equals("particular");
        System.out.println("Nombre: ");
        String nombre = scanner.next();
        String apellidos = "";
        if (particular){
            System.out.println("Apellidos: ");
            apellidos = scanner.next();
        }
        System.out.println("NIF: ");
        String nif = scanner.next();
        System.out.println("CP: ");
        int cp = scanner.nextInt();
        System.out.println("Provincia: ");
        String provincia = scanner.next();
        System.out.println("Población: ");
        String poblacion = scanner.next();
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        System.out.println("Correo electrónico: ");
        String correoElectronico = scanner.next();
        System.out.println("Tarifa: ");
        double tarifa = scanner.nextDouble();
        if (particular)
            empresa.añadirCliente(nif, new ClienteParticular(nombre, nif, direccion, correoElectronico, new Tarifa(tarifa), apellidos));
        empresa.añadirCliente(nif, new ClienteEmpresa(nombre, nif, direccion, correoElectronico, new Tarifa(tarifa)));
        System.out.println("El cliente se ha añadido correctamente.");
    }

    private void borrarCuenta(){
        System.out.println("NIF del cliente que desea borrar: ");
        String nif = scanner.next();
        empresa.borrarCliente(nif);
        System.out.println("Cliente borrado con éxito.");
    }

    private void cambiarTarifa(){

    }

    private void emitirFactura(){

    }

    private void guardarLlamada(){

    }

    private void mostrarClientes(){

    }

    private void mostrarDatosCliente(){

    }

    private void mostrarDatosFactura(){

    }

    private void mostrarFacturasCliente(){

    }

    private void mostrarLlamadasCliente(){

    }


}
