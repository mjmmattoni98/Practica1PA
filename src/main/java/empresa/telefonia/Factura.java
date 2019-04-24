package empresa.telefonia;


import excepciones.TarifaException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Factura implements Serializable, Fecha {
    private Tarifa tarifa;
    private int codigo;
    private LocalDateTime fechaEmision;
    private Periodo periodoFacturacion;
    private double importe;


    public Factura (Tarifa tarifa, int codigo, Periodo periodoFacturacion){
        this.tarifa = tarifa;
        this.codigo = codigo;
        this.periodoFacturacion = periodoFacturacion;
        this.fechaEmision = LocalDateTime.now();
        this.importe = 0;
    }

    @Override
    public LocalDateTime getFecha(){
        return fechaEmision;
    }

    //TODO pensar como mejorar este método ya que el objeto está "decorado".
    public Tarifa getTarifa(){
        return tarifa;
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

    public double calcularImporte(List<Llamada> llamadas) {
        for(Llamada llamada : llamadas)
            importe += llamada.getDuración() * tarifa.getTarifa(llamada.getFecha());
        return this.importe;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\t-Tarifa aplicada: " + tarifa + "\n");
        sb.append("\t-El código de la factura es: " + codigo + "\n");
        sb.append("\t-Fecha de emisión: " + fechaEmision + "\n");
        sb.append("\t-Periodo de facturación: " + periodoFacturacion + "\n");
        sb.append("\t-El importe final de la factura sería: " + importe/100 + " euros.\n");
        return sb.toString();
    }

}
