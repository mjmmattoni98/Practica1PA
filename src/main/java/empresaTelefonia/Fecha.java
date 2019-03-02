package empresaTelefonia;

import java.time.LocalDateTime;
import java.time.Period;

public class Fecha implements Comparable<Fecha>{
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

    @Override
    public int compareTo(Fecha otraFecha){
        if (this.fecha.compareTo(otraFecha.getFecha()) < 0) return -1;
        if (this.fecha.compareTo(otraFecha.getFecha()) > 0) return 1;
        return 0;
    }

}
