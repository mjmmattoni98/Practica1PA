package entrada.salida;

import vista.VistaMenuGeneral;

import javax.swing.*;

public class ImplementacionSwing {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VistaMenuGeneral().ejecuta();
            }
        });
    }

}
