package empresaTelefonia;


import javafx.util.Pair;

import java.time.Period;

public class Factura {
    private Tarifa tarifa; //El cambio de tarifa se verá reflejado en la siguiente factura
    private int codigo;
    private Fecha fechaEmision;
    private Period periodoFacturacion;
    private double importe;


    public Factura (Tarifa tarifa, int codigo){
        this.tarifa = tarifa;
        this.codigo = codigo;
    }

    public void setTarifa(Tarifa nuevaTarifa){
        this.tarifa = nuevaTarifa;
    }

    public Fecha getFecha(){
        return fechaEmision;
    }

    private void calcularImporte(){

    }

}
