package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaMenuLlamadas {

    public void ejecuta() {
        JFrame ventana = new JFrame("Vista llamadas.");

        Container container = ventana.getContentPane();

        container.setLayout(new FlowLayout());

        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Crear cliente", new DarAltaLlamada());
        pestanyas.add("Borrar Cliente", new MostrarLlamadasCliente());
        pestanyas.add("Cambiar tarifa", new MostrarLlamadasEntreFechas());

        container.add(pestanyas);

        ventana.pack();
        ventana.setVisible(true);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Hasta luego desde llamadas!!!!");
            }
        });
    }

    private class DarAltaLlamada extends JPanel {
        public DarAltaLlamada() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbCrearCliente = new JButton("Nuevo cliente");
            jbCrearCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nif = jtfNif.getText();
                    System.out.println("NIF: " + nif);
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbCrearCliente);
        }
    }

    private class MostrarLlamadasCliente extends JPanel {
        public MostrarLlamadasCliente() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbBorrarCliente = new JButton("Borrar cliente");
            jbBorrarCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nif = jtfNif.getText();
                    System.out.println("Cliente " + nif + " borrado.");
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbBorrarCliente);
        }
    }

    private class MostrarLlamadasEntreFechas extends JPanel {
        public MostrarLlamadasEntreFechas() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbBorrarCliente = new JButton("Borrar cliente");
            jbBorrarCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nif = jtfNif.getText();
                    System.out.println("Cliente " + nif + " borrado.");
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbBorrarCliente);
        }
    }

}
