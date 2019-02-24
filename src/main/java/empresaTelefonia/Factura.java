package empresaTelefonia;


public class Factura {
    private Tarifa tarifaActual; //El cambio de tarifa se verá reflejado en la siguiente factura
    private int unicCode;
    private Fecha fechaEmision;
    private Fecha periodoFacturacion;
    private double importe;


    public Factura (Tarifa tarifaActual, String codigo){
        this.tarifaActual = tarifaActual;
        this.unicCode = codigo.hashCode();
    }

    public void setTarifaActual (Tarifa nuevaTarifa){
        this.tarifaActual = nuevaTarifa;
    }

    public Fecha getFecha(){
        return fechaEmision;
    }

    private void calcularImporte(){

    }

}
