package vista;

import controlador.ImplementacionControlador;
import modelo.CambioModelo;
import modelo.ImplementacionModelo;
//import modelo.ImplementacionModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;

public class VistaMenuLlamadas {
    private ImplementacionModelo modelo = new ImplementacionModelo();
    private ImplementacionControlador controlador = new ImplementacionControlador();

    public void ejecuta() {
        JFrame ventana = new JFrame("Vista llamadas.");
        Container container = ventana.getContentPane();
        container.setLayout(new FlowLayout());
        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Añadir llamada", new DarAltaLlamada());
        pestanyas.add("Mostrar llamadas cliente", new MostrarLlamadasCliente());
        pestanyas.add("Mostrar llamadas entre fechas", new MostrarLlamadasEntreFechas());

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
            JTextField jtfNumero = new JTextField(20);
            JLabel numeroLabel = new JLabel("Numero: ");
            JTextField jtfDuracion = new JTextField(20);
            JLabel duracionLabel = new JLabel("Duracion: ");
            JButton jbAddLlamada = new JButton("Añadir llamada");
            jbAddLlamada.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controlador.addLlamada(jtfNif.getText(),Integer.parseInt(jtfNumero.getText()),Double.parseDouble(jtfDuracion.getText()));
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(numeroLabel);
            this.add(jtfNumero);
            this.add(duracionLabel);
            this.add(jtfDuracion);
            this.add(jbAddLlamada);
        }
    }


    private class MostrarLlamadasCliente extends JPanel {
        public MostrarLlamadasCliente() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbMostrarLlamadas = new JButton("Mostrar llamadas");
            jbMostrarLlamadas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel llamadas= new JLabel(modelo.mostrarLlamadasCliente(jtfNif.getText()));
                    JFrame mostrarLlamadas = new JFrame("Mostrar llamadas cliente");
                    mostrarLlamadas.add(llamadas);
                    mostrarLlamadas.pack();
                    mostrarLlamadas.setVisible(true);
                    System.out.println("Mostrando llamadas del cliente con NIF "+jtfNif.getText());
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbMostrarLlamadas);
        }
    }

    private class MostrarLlamadasEntreFechas extends JPanel {
        public MostrarLlamadasEntreFechas() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JTextField jtfFecha1 = new JTextField(20);
            JLabel Fecha1Label = new JLabel("Fecha Inicio (yyyy-mm-ddThh:mm:ss): ");
            JTextField jtfFecha2 = new JTextField(20);
            JLabel Fecha2Label = new JLabel("Fecha Final (yyyy-mm-ddThh:mm:ss): ");
            JButton jbMostrarFacturas = new JButton("Mostrar Llamadas");
            jbMostrarFacturas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel llamadas= new JLabel(modelo.mostrarLlamadasEntreFechas(jtfNif.getText(),LocalDateTime.parse(jtfFecha1.getText()),LocalDateTime.parse(jtfFecha2.getText())));
                    JFrame mostrarLlamadas= new JFrame("Mostrar facturas entre fecha");
                    mostrarLlamadas.add(llamadas);
                    mostrarLlamadas.pack();
                    mostrarLlamadas.setVisible(true);
                    System.out.println("Mostrando llamadas entre "+jtfFecha1.getText()+" y "+jtfFecha2.getText());
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(Fecha1Label);
            this.add(jtfFecha1);
            this.add(Fecha2Label);
            this.add(jtfFecha2);
            this.add(jbMostrarFacturas);
        }
    }

}
