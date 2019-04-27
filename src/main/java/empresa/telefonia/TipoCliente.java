package empresa.telefonia;

public enum TipoCliente {
    PARTICULAR("Cliente particular"),
    EMPRESA("Cliente empresa");
    private String descripcion;

    private TipoCliente(String descripcion) {
        this.descripcion = descripcion;
    }

    public static String opciones() {
        StringBuilder sb = new StringBuilder();
        for(TipoCliente tipo: values())
            sb.append(tipo.ordinal() + ".- " + tipo.descripcion + "\n");
        return sb.toString();
    }
    public static TipoCliente getOpcion(int posicion) {
        return values()[posicion];
    }
}
