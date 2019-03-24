package empresa.telefonia;


import java.io.Serializable;
import java.time.LocalDateTime;

public class Llamada extends Cliente implements Comparable<Llamada>, Serializable{
    private int numero;
    private LocalDateTime fechaYHora;
    private double duración;

    public Llamada (int numero, LocalDateTime fechaYHora, double duración) {
        this.numero = numero;
        this.fechaYHora = fechaYHora;
        this.duración = duración;
    }

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
        if (this.fechaYHora.compareTo(otraLlamada.getFecha()) < 0) return -1;
        if (this.fechaYHora.compareTo(otraLlamada.getFecha()) > 0) return 1;
        return 0;
    }

    @Override
    public String toString(){
        return "Llamada al número: " + numero + " con fecha: " + fechaYHora + " y duración: " + duración + " minutos.\n";
    }


}
