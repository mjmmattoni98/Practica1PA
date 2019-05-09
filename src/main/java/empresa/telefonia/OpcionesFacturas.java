package empresa.telefonia;

public enum OpcionesFacturas {
    EMITIR_FACTURA("Emitir la factura para un cliente."),
    MOSTRAR_DATOS_FACTURA("Mostrar la informacion de una factura."),
    MOSTRAR_FACTURAS_CLIENTE("Mostrar las facturas de un cliente."),
    MOSTRAR_FACTURAS_ENTRE_FECHAS("Mostrar fecturas de un cliente en un intervalo de tiempo."),
    SALIR("Salir de la aplicación.");

    private String descripcion;

    OpcionesFacturas(String descripcion){
        this.descripcion = descripcion;
    }

    private String getDescripcion(){
        return descripcion;
    }

    public static OpcionesFacturas getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(OpcionesFacturas opcion: OpcionesFacturas.values()) {
            sb.append(opcion.ordinal() + ".- " + opcion.getDescripcion() + "\n");
        }
        return sb.toString();
    }

}
