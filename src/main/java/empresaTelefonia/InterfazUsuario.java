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
        String nif = "";
        switch (opcionMenu){
            case MOSTRAR_CLIENTES:
                mostrarClientes();
                break;
            case MOSTRAR_DATOS_FACTURA:
                System.out.println("Codigo de factura: ");
                int codigo = scanner.nextInt();
                mostrarDatosFactura(codigo);
                break;
            default:
                System.out.println("NIF: ");
                nif = scanner.next();
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

    private void crearCuenta(String nif){
        System.out.println("Empresa o particular? ");
        boolean particular = scanner.next().equals("particular");
        System.out.println("Nombre: ");
        String nombre = scanner.next();
        String apellidos = "";
        if (particular){
            System.out.println("Apellidos: ");
            apellidos = scanner.next();
        }
        System.out.println("CP: ");
        int cp = scanner.nextInt();
        System.out.println("Provincia: ");
        String provincia = scanner.next();
        System.out.println("Población: ");
        String poblacion = scanner.next();
        System.out.println("Correo electrónico: ");
        String correoElectronico = scanner.next();
        System.out.println("Tarifa: ");
        double tarifa = scanner.nextDouble();
        if (particular)
            empresa.añadirCliente(nif, nombre, cp, provincia, poblacion, correoElectronico, tarifa, apellidos);
        empresa.añadirCliente(nif, nombre, cp, provincia, poblacion, correoElectronico, tarifa);
        System.out.println("El cliente se ha añadido correctamente.");
    }

    private void borrarCuenta(String nif){
        empresa.borrarCliente(nif);
        System.out.println("Cliente borrado con éxito.");
    }

    private void cambiarTarifa(String nif){
        System.out.println("Introduzca la nueva tarifa: ");
        double tarifa = scanner.nextDouble();
    }

    private void emitirFactura(String nif){

    }

    private void guardarLlamada(String nif){
        
    }

    private void mostrarClientes(){
        empresa.getClientes();
    }

    private void mostrarDatosCliente(String nif){
        empresa.getCliente(nif);
    }

    private void mostrarDatosFactura(int codigo){
        empresa.getFactura(codigo);
    }

    private void mostrarFacturasCliente(String nif){
        empresa.getFacturasCliente(nif);
    }

    private void mostrarLlamadasCliente(String nif){
        empresa.getLlamadasCliente(nif);
    }


}
