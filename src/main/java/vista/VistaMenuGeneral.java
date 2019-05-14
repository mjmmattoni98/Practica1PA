package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaMenuGeneral {

    public void ejecutaGUI() {
        SwingUtilities.invokeLater(() -> ejecuta());
    }

    private void ejecuta(){
        JFrame ventana = new JFrame("Primera prueba.");

        Container container = ventana.getContentPane();

//        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));

        JPanel jPanel = new JPanel();
        JButton jbClientes = new JButton("Prueba a menu cliente.");
        jbClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yendo al menu del cliente.");
                new VistaMenuCliente().ejecuta();
            }
        });

        JButton jbFacturas = new JButton("Prueba a menu facturas.");
        jbFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yendo al menu del facturas.");
                new VistaMenuFacturas().ejecuta();
            }
        });

        JButton jbLlamadas = new JButton("Prueba a menu llamadas.");
        jbLlamadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yendo al menu del llamadas.");
                new VistaMenuLlamadas().ejecuta();
            }
        });

        jPanel.add(jbClientes);
        jPanel.add(jbFacturas);
        jPanel.add(jbLlamadas);

        container.add(jPanel);
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
