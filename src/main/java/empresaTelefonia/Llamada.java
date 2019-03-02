package empresaTelefonia;

public class Llamada implements Comparable<Llamada>{
    private int numero;
    private Fecha fechaYHora;
    private int duración;

    public Llamada (int numero, Fecha fechaYHora, int duración){
        this.numero = numero;
        this.fechaYHora = fechaYHora;
        this.duración = duración;
    }

    public Fecha getFecha(){
        return fechaYHora;
    }

    @Override
    public int compareTo(Llamada otraLlamada){
        if (this.fechaYHora.compareTo(otraLlamada.getFecha()) < 0) return -1;
        if (this.fechaYHora.compareTo(otraLlamada.getFecha()) > 0) return 1;
        return 0;
    }

}
