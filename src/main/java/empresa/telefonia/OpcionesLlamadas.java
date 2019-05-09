package empresa.telefonia;

public enum OpcionesLlamadas {
    DAR_ALTA_LLAMADA("Agregar una llamada realizada."),
    MOSTRAR_LLAMADAS_CLIENTE("Mostrar las llamadas realizadas por un cliente."),
    MOSTRAR_LLAMADAS_ENTRE_FECHAS("Mostrar llamadas de un cliente en un intervalo de tiempo."),
    SALIR("Salir de la aplicación.");

    private String descripcion;

    OpcionesLlamadas(String descripcion){
        this.descripcion = descripcion;
    }

    private String getDescripcion(){
        return descripcion;
    }

    public static OpcionesLlamadas getOpcion(int posicion) {
        return values()[posicion];
    }

    public static String getMenu() {
        StringBuilder sb = new StringBuilder();
        for(OpcionesLlamadas opcion: OpcionesLlamadas.values()) {
            sb.append(opcion.ordinal() + ".- " + opcion.getDescripcion() + "\n");
        }
        return sb.toString();
    }

}
