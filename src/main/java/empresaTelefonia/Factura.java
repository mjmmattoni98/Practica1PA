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

    public double getImporte(){
        return importe;
    }

    public double calcularImporte(List<Llamada> llamadas) throws TarifaException {
        double tiempoLlamada = 0;
        for(Llamada llamada : llamadas)
            tiempoLlamada += llamada.getDuración();
        this.importe = tiempoLlamada * tarifa.getTarifa();
        return this.importe;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\t-Tarifa aplicada: " + tarifa + "\n");
        sb.append("\t-El código de la factura es: " + codigo + "\n");
        sb.append("\t-Fecha de emisión: " + fechaEmision + "\n");
        sb.append("\t-Periodo de facturación: " + periodoFacturacion + "\n");
        sb.append("\t-El importe final de la factura sería: " + importe + " euros.\n");
        return sb.toString();
    }

}
