package empresaTelefonia;


import java.time.LocalDateTime;

public class Fecha implements Comparable<Fecha>{
    private LocalDateTime fecha;

    public Fecha (){
        this.fecha = LocalDateTime.now();
    }

    public LocalDateTime getFecha(){
        return fecha;
    }

    public static Periodo getPeriodo(LocalDateTime fecha, LocalDateTime otraFecha){
        return new Periodo(fecha, otraFecha);
    }

    public void masDias(int dias){
        this.fecha = fecha.plusDays(dias);
    }

    public void masMeses(int meses){
        this.fecha = fecha.plusMonths(meses);
    }

    public void masAños(int años){
        this.fecha = fecha.plusYears(años);
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
