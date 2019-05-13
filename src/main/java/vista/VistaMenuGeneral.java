package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaMenuGeneral {

    public void ejecuta(){
        JFrame ventana = new JFrame("Primera prueba.");

        Container container = ventana.getContentPane();

        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.add(new JButton("Uno")/*.add(new ActionListener(){
            @Override
            public void actionPerfomed(ActionEvent e){
                System.out.println("Me pulsaste");
            }
        })*/);

        JButton jButton = new JButton("Prueba a menu cliente.");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yendo al menu del cliente.");
                new VistaMenuCliente().ejecuta();
            }
        });
        container.add(jButton);
        container.add(new JButton("Dos"));
        container.add(new JButton("Tres"));
        container.add(new JButton("Cuatro"));
        container.add(new JButton("Cinco"));

//        ventana.setSize(200, 200);
        ventana.pack();
        ventana.setVisible(true);
//        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Hasta luego desde general!!!!");
                System.exit(0);
            }
        });
    }
}
