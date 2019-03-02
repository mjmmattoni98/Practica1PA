package empresaTelefonia;

import java.time.LocalDateTime;
import java.time.Period;

public class Fecha {
    private LocalDateTime fecha;

    public Fecha (){
        this.fecha = LocalDateTime.now();
    }

    /*public Fecha (LocalDateTime fecha){
        this.fecha = fecha;
    }*/

    public LocalDateTime getFecha(){
        return fecha;
    }

    public Period getPeriodo (Fecha otraFecha){
        return  Period.between(fecha.toLocalDate(), otraFecha.getFecha().toLocalDate());
    }

    @Override
    public String toString(){
        return fecha.toString();
    }

}
