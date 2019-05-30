package entrada.salida;

import controlador.ImplementacionControlador;
import modelo.ImplementacionModelo;

import vista.VistaMenuGeneral;


public class ImplementacionSwing {

    /*public static void main(String[] args){
        new VistaMenuGeneral().ejecutaGUI();
    }
*/
    public static void main(String args[]) {
        ImplementacionControlador controlador = new ImplementacionControlador();
        ImplementacionModelo modelo = new ImplementacionModelo();
        VistaMenuGeneral vista = new VistaMenuGeneral();
//        modelo.setVista(vista);
//        controlador.setVista(vista);
        controlador.setModelo(modelo);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        vista.ejecutaGUI();
    }

}
