package gestion.datos;

import java.io.Serializable;

public class Gestor implements Serializable {
    GestionLlamadas gestionLlamadas;
    GestionClientes gestionClientes;
    GestionFacturas gestionFacturas;

    public Gestor(){
        this.gestionClientes = new GestionClientes();
        this.gestionLlamadas = new GestionLlamadas();
        this.gestionFacturas = new GestionFacturas();
    }

}
