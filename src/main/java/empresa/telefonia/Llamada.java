package empresa.telefonia;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Llamada implements Comparable<Llamada>, Serializable, Fecha{
    private int numero;
    private LocalDateTime fechaYHora;
    private double duración;

    public Llamada (int numero, LocalDateTime fechaYHora, double duración) {
        this.numero = numero;
        this.fechaYHora = fechaYHora;
        this.duración = duración;
    }

    @Override
    public LocalDateTime getFecha(){
        return fechaYHora;
    }

    public double getDuración(){
        return duración;
    }

    public int getNumero(){
        return numero;
    }

    @Override
    public int compareTo(Llamada otraLlamada){
        if (this.numero < otraLlamada.getNumero()) return -1;
        if (this.numero > otraLlamada.getNumero()) return 1;
        return 0;
    }

    @Override
    public String toString(){
        return "Llamada al número: " + numero + " con fecha: " + fechaYHora + " y duración: " + duración + " minutos.\n";
    }


}
