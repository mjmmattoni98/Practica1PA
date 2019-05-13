package controlador;

import modelo.CambioModelo;
import vista.InterrogaVista;

public class ImplementacionControlador implements Controlador {
    private CambioModelo modelo; //Modelo es un interface
    private InterrogaVista vista; //Vista es un interface

    @Override
    public void anyadeEntrada() {
//        String entrada = vista.getEntrada();
//        modelo.anyadeEntrada(entrada);
    }

    @Override
    public void adelante(){

    }

    @Override
    public void atras(){

    }
}
