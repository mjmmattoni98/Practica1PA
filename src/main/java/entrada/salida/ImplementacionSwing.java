package entrada.salida;

import controlador.ImplementacionControlador;
import modelo.ImplementacionModelo;

import vista.VistaMenuCliente;
import vista.VistaMenuFacturas;
import vista.VistaMenuGeneral;
import vista.VistaMenuLlamadas;


public class ImplementacionSwing {

    /*public static void main(String[] args){
        new VistaMenuGeneral().ejecutaGUI();
    }
*/
    public static void main(String args[]) {
        ImplementacionControlador controlador = new ImplementacionControlador();
        ImplementacionModelo modelo = new ImplementacionModelo();
        VistaMenuGeneral vista = new VistaMenuGeneral(modelo, controlador);
        /*VistaMenuCliente vistaCliente = new VistaMenuCliente();
        VistaMenuFacturas vistaFacturas = new VistaMenuFacturas();
        VistaMenuLlamadas vistaLlamadas = new VistaMenuLlamadas();
        vista.setVistaMenuCliente(vistaCliente);
        vista.setVistaMenuFacturas(vistaFacturas);
        vista.setVistaMenuLlamadas(vistaLlamadas);
        */modelo.setVista(vista);
//        controlador.setVistaCliente(vista);
        controlador.setModelo(modelo);
        vista.ejecutaGUI();
    }

}
