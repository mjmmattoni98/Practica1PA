package empresa.telefonia;

public enum OpcionesCliente {
    CREAR_CUENTA("Crear una cuenta nueva."),
    BORRAR_CUENTA("Borrar una cuenta."),
    CAMBIAR_TARIFA("Cambiar la tarifa de su contrato."),
    MOSTRAR_DATOS_CLIENTE("Mostrar los datos de un cliente."),
    MOSTRAR_CLIENTES("Mostrar toda la lista de clientes."),
    MOSTRAR_CLIENTES_ENTRE_FECHAS("Mostrar clientes en un intervalo de tiempo."),
    SALIR("Salir de la aplicación.");

    private String descripcion;

    OpcionesCliente(String descripcion){
        this.descripcion = descripcion;
    }

    private String getDescripcion(){
        return descripcion;
    }

    public static OpcionesCliente getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(OpcionesCliente opcion: OpcionesCliente.values()) {
            sb.append(opcion.ordinal() + ".- " + opcion.getDescripcion() + "\n");
        }
        return sb.toString();
    }

}
