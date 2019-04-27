package empresa.telefonia;

public enum TipoTarifa {
    TARDES_REDUCIDAS("Tardes reducidas"),
    DOMINGO_GRATIS("Domingo gratis");

        private String descripcion;

        private TipoTarifa(String descripcion) {
            this.descripcion = descripcion;
        }

        public static String opciones() {
            StringBuilder sb = new StringBuilder();
            for(TipoTarifa tipo: values())
                sb.append(tipo.ordinal() + ".- " + tipo.descripcion + "\n");
            return sb.toString();
        }
        public static TipoTarifa getOpcion(int posicion) {
            return values()[posicion];
        }
}
