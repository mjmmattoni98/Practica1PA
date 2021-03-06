package empresa.telefonia;

public enum OpcionesMenu {
    ACCIONES_CLIENTE("Realizar acciones con clientes."),
    ACCIONES_FACTURAS("Realizar acciones con facturas."),
    ACCIONES_LLAMADAS("Realizar acciones con llamadas."),
    SALIR("Salir de la aplicación.");

    private String descripcion;

    OpcionesMenu(String descripcion){
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
            sb.append(opcion.ordinal() + ".- " + opcion.getDescripcion() + "\n");
        }
        return sb.toString();
    }

}
