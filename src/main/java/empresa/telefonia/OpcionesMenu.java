package empresa.telefonia;

public enum OpcionesMenu {
    CREAR_CUENTA("Crear una cuenta nueva."),
    BORRAR_CUENTA("Borrar una cuenta."),
    CAMBIAR_TARIFA("Cambiar la tarifa de su contrato."),
    MOSTRAR_DATOS_CLIENTE("Mostrar los datos de un cliente."),
    MOSTRAR_CLIENTES("Mostrar toda la lista de clientes."),
    DAR_ALTA_LLAMADA("Agregar una llamada realizada."),
    MOSTRAR_LLAMADAS_CLIENTE("Mostrar las llamadas realizadas por un cliente."),
    EMITIR_FACTURA("Emitir la factura para un cliente."),
    MOSTRAR_DATOS_FACTURA("Mostrar la informacion de una factura."),
    MOSTRAR_FACTURAS_CLIENTE("Mostrar las facturas de un cliente.");
//    SALIR("Salir de la aplicación.");

    private String descripcion;

    private OpcionesMenu(String descripcion){
        this.descripcion = descripcion;
    }

    private String getDescripcion(){
        return descripcion;
    }

    public static OpcionesMenu getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(OpcionesMenu opcion: OpcionesMenu.values()) {
            sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.getDescripcion());
            sb.append("\n");
        }
        return sb.toString();
    }
}
