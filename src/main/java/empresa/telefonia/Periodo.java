package empresa.telefonia;



import java.io.Serializable;
import java.time.LocalDateTime;

public class Periodo implements Serializable {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Periodo(LocalDateTime fechaInicio, LocalDateTime fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public LocalDateTime getFechaInicio(){
        return fechaInicio;
    }

    public LocalDateTime getFechaFin(){
        return fechaFin;
    }

    @Override
    public String toString(){
        return "(" + fechaInicio.toLocalDate() + " - " + fechaFin.toLocalDate() + ")";
    }

    @Override
    public boolean equals(Object o){
        Periodo otroPeriodo = (Periodo) o;
        return fechaInicio.equals(otroPeriodo.getFechaInicio()) && fechaFin.equals(otroPeriodo.getFechaFin());
    }
}
