package empresa.telefonia;

public enum TipoTarifa {
    TARDES_REDUCIDAS("Tardes reducidas."),
    DOMINGO_GRATIS("Domingo gratis."),
    TARIFA_BASICA("Tarifa básica.\nSi elige está opción, se reseteará a esta tarifa y se perderán todas las tarifas que tenía contratado.");

    private String descripcion;

    TipoTarifa(String descripcion) {
        this.descripcion = descripcion;
    }

    public static String opciones() {
        StringBuilder sb = new StringBuilder();
        for (TipoTarifa tipo : values())
            sb.append(tipo.ordinal() + ".- " + tipo.descripcion + "\n");
        return sb.toString();
    }

    public static TipoTarifa getOpcion(int posicion) {
        return values()[posicion];
    }
}
