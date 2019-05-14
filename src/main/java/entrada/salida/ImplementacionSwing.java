package entrada.salida;

import controlador.ImplementacionControlador;
import modelo.ImplementacionModelo;
import vista.ImplementacionVista;
import vista.VistaMenuGeneral;


public class ImplementacionSwing {

    public static void main(String[] args){
        new VistaMenuGeneral().ejecutaGUI();
    }

    /*public static void main(String args[]) {
        ImplementacionControlador controlador = new ImplementacionControlador();
        ImplementacionVista vista = new ImplementacionVista();
        ImplementacionModelo modelo = new ImplementacionModelo();
        modelo.setVista(vista);
        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.creaGUI();
    }*/

}
