package empresaTelefonia;


import java.time.LocalDateTime;

public class Periodo {
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
}
