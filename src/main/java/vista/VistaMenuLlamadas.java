package vista;

import controlador.Controlador;
import modelo.InterrogaModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;

public class VistaMenuLlamadas extends VistaMenuGeneral{

    public VistaMenuLlamadas(){
        super();
    }

    public  VistaMenuLlamadas(InterrogaModelo modelo, Controlador controlador){
        super(modelo, controlador);
    }

    public void ejecutaGUI() {
        SwingUtilities.invokeLater(this::ejecuta);
    }

    private void ejecuta() {
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
        ventana.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Se pide una confirmación antes de finalizar el programa
                modelo.escribirDatos();
                VistaMenuGeneral vista= new VistaMenuGeneral(modelo, controlador);
                vista.ejecutaGUI();
                ventana.dispose();
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
                    controlador.addLlamada(jtfNif.getText().toUpperCase(),Integer.parseInt(jtfNumero.getText()),Double.parseDouble(jtfDuracion.getText()));
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
                    String nif = jtfNif.getText().toUpperCase();
                    JLabel llamadas = new JLabel(modelo.mostrarLlamadasCliente(nif));
                    JScrollPane jspLlamadas = new JScrollPane(llamadas);
                    JFrame mostrarLlamadas = new JFrame("Mostrar llamadas cliente");
                    mostrarLlamadas.add(jspLlamadas);
                    mostrarLlamadas.add(llamadas);
                    mostrarLlamadas.pack();
                    mostrarLlamadas.setVisible(true);
                    System.out.println("Mostrando llamadas del cliente con NIF " + nif);
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
                    String nif = jtfNif.getText().toUpperCase();
                    JLabel llamadas = new JLabel(modelo.mostrarLlamadasEntreFechas(nif, LocalDateTime.parse(jtfFecha1.getText()), LocalDateTime.parse(jtfFecha2.getText())));
                    JScrollPane jspLlamadas = new JScrollPane(llamadas);
                    JFrame mostrarLlamadas= new JFrame("Mostrar facturas entre fecha");
                    mostrarLlamadas.add(jspLlamadas);
                    mostrarLlamadas.add(llamadas);
                    mostrarLlamadas.pack();
                    mostrarLlamadas.setVisible(true);
                    System.out.println("Mostrando llamadas para el cliente " + nif + " entre " + jtfFecha1.getText() + " y " + jtfFecha2.getText());
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
