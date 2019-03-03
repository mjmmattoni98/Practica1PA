package empresaTelefonia;

import java.time.LocalDateTime;

public class Llamada implements Comparable<Llamada>{
    private int numero;
    private LocalDateTime fechaYHora;
    private int duración;

    public Llamada (int numero, LocalDateTime fechaYHora, int duración){
        this.numero = numero;
        this.fechaYHora = fechaYHora;
        this.duración = duración;
    }

    public LocalDateTime getFecha(){
        return fechaYHora;
    }

    public int getDuración(){
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
        return "Llamada al número: " + numero + " con fecha: " + fechaYHora + " y duración: " + duración;
    }

}
