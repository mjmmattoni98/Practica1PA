package vista;

public interface InformaVista {
    void addedClient(String nif);
    void removedClient(String nif);
    void addedCall(String nif, int numero);
    void modifiedFee(String nif, String tipo);
    void generatedBill(String nif);
}
