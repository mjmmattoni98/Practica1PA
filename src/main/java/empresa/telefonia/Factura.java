package empresa.telefonia;


import excepciones.TarifaException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Factura implements Serializable, Fecha {
    private TarifaBasica tarifa; //El cambio de tarifa se verá reflejado en la siguiente factura
    private int codigo;
    private LocalDateTime fechaEmision;
    private Periodo periodoFacturacion;
    private double importe;


    public Factura (TarifaBasica tarifa, int codigo, Periodo periodoFacturacion){
        this.tarifa = tarifa;
        this.codigo = codigo;
        this.periodoFacturacion = periodoFacturacion;
        this.fechaEmision = LocalDateTime.now();
    }

    @Override
    public LocalDateTime getFecha(){
        return fechaEmision;
    }

    //TODO hacer bien el método.
    public double getTarifa(){
        return tarifa.getTarifa(LocalDateTime.now());
    }

    public int getCodigo(){
        return codigo;
    }

    public Periodo getPeriodoFacturacion(){
        return periodoFacturacion;
    }

    public double getImporte(){
        return importe;
    }

    //TODO hacer bien el metodo.
    public double calcularImporte(List<Llamada> llamadas) throws TarifaException {
        double tiempoLlamada = 0;
        for(Llamada llamada : llamadas)
            tiempoLlamada += llamada.getDuración();
        this.importe = tiempoLlamada * tarifa.getTarifa(LocalDateTime.now());
        return this.importe;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\t-TarifaBasica aplicada: " + tarifa + "\n");
        sb.append("\t-El código de la factura es: " + codigo + "\n");
        sb.append("\t-Fecha de emisión: " + fechaEmision + "\n");
        sb.append("\t-Periodo de facturación: " + periodoFacturacion + "\n");
        sb.append("\t-El importe final de la factura sería: " + importe/100 + " euros.\n");
        return sb.toString();
    }

}
