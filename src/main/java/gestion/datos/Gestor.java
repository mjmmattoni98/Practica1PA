package gestion.datos;

import java.io.Serializable;

public class Gestor implements Serializable {
    GestionLlamadas gestionLlamadas;
    GestionClientes gestionClientes;
    GestionFacturas gestionFacturas;

    //TODO problema cuando se llama desde un gestor a otro gestor.
    //TODO probar a tener una clase donde solo se guarden los datos, y que sea esa clase la que se serialice
    //y despues cuando se lea se sobreescriban los datos.
    public Gestor(){
        this.gestionClientes = new GestionClientes();
        this.gestionLlamadas = new GestionLlamadas();
        this.gestionFacturas = new GestionFacturas();
    }

}
