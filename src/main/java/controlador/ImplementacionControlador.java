package controlador;

import modelo.CambioModelo;
import vista.InterrogaVista;

public class ImplementacionControlador implements Controlador {
    private InterrogaVista vista;
    private CambioModelo modelo;

    public ImplementacionControlador() {}

    public void anyadeEntrada() {
        String entrada = vista.getEntrada();
        modelo.anyadeEntrada(entrada);
    }

    public void adelante() {
        modelo.incrementaPosicionActual();
    }

    public void atras() {
        modelo.decrementaPosicionActual();
    }

    public void setModelo(CambioModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(InterrogaVista vista) {
        this.vista = vista;
    }
}
