package empresaTelefonia;


import java.time.LocalDateTime;
import java.util.List;

public class Factura {
    private Tarifa tarifa; //El cambio de tarifa se verá reflejado en la siguiente factura
    private int codigo;
    private LocalDateTime fechaEmision;
    private Periodo periodoFacturacion;
    private double importe;


    public Factura (Tarifa tarifa, int codigo, Periodo periodoFacturacion){
        this.tarifa = tarifa;
        this.codigo = codigo;
        this.periodoFacturacion = periodoFacturacion;
        this.fechaEmision = LocalDateTime.now();
    }

    public LocalDateTime getFecha(){
        return fechaEmision;
    }

    public Tarifa getTarifa(){
        return tarifa;
    }

    public int getCodigo(){
        return codigo;
    }

    public Periodo getPeriodoFacturacion(){
        return periodoFacturacion;
    }

    public  double getImporte(){
        return importe;
    }

    public void setTarifa(Tarifa nuevaTarifa){
        this.tarifa = nuevaTarifa;
    }

    public double calcularImporte(List<Llamada> llamadas){
        double tiempoLlamada = 0;
        for(Llamada llamada : llamadas)
            tiempoLlamada += llamada.getDuración();
        this.importe = tiempoLlamada * tarifa.getTarifa();
        return this.importe;
    }

}
