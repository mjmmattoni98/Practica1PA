package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaMenuFacturas {

    public void ejecuta() {
        JFrame ventana = new JFrame("Vista facturas.");

        Container container = ventana.getContentPane();

        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        container.add(new JButton("Boton de facturas"));

//        ventana.setSize(200, 200);
        ventana.pack();
        ventana.setVisible(true);
//        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Hasta luego desde facturas!!!!");
//                System.exit(0);
            }
        });
    }
}
