package vista;

import controlador.ImplementacionControlador;
import modelo.CambioModelo;
import modelo.ImplementacionModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;

public class VistaMenuFacturas {
    ImplementacionModelo modelo = new ImplementacionModelo();
    ImplementacionControlador controlador = new ImplementacionControlador();

    public void ejecuta() {
        JFrame ventana = new JFrame("Vista facturas.");

        Container container = ventana.getContentPane();

        container.setLayout(new FlowLayout());

        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Emitir factura", new EmitirFactura());
        pestanyas.add("Mostrar datos factura", new MostrarDatosFactura());
        pestanyas.add("Mostrar facturas cliente", new MostrarFacturasCliente());
        pestanyas.add("Mostrar facturas entre fechas", new MostrarFacturasEntreFechas());

        container.add(pestanyas);

        ventana.pack();
        ventana.setVisible(true);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Hasta luego desde facturas!!!!");
            }
        });
    }

    private class EmitirFactura extends JPanel {
        public EmitirFactura() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif = new JTextField(20);
            JLabel nifLabel = new JLabel("NIF: ");
            JButton jbCrearCliente = new JButton("Emitir factura");
            jbCrearCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controlador.emitirFactura(jtfNif.getText());
                    System.out.println("Factura emitida con exito.");
                }
            });
            this.add(nifLabel);
            this.add(jtfNif);
            this.add(jbCrearCliente);
        }
    }

    private class MostrarDatosFactura extends JPanel {
        public MostrarDatosFactura() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfFactura = new JTextField(20);
            JLabel facturaLabel = new JLabel("Factura: ");
            JButton jbBorrarCliente = new JButton("Mostrar datos");
            jbBorrarCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel facturas= new JLabel(modelo.mostrarDatosFactura(Integer.parseInt(jtfFactura.getText())));
                    JFrame mostrarFactura= new JFrame("Mostrar datos factura");
                    mostrarFactura.add(facturas);
                    mostrarFactura.pack();
                    mostrarFactura.setVisible(true);
                    System.out.println("Mostrando datos de la factura"+jtfFactura.getText());
                }
            });
            this.add(facturaLabel);
            this.add(jtfFactura);
            this.add(jbBorrarCliente);
        }
    }

    private class MostrarFacturasCliente extends JPanel {
        public MostrarFacturasCliente() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfNif1 = new JTextField(20);
            JLabel nif1Label = new JLabel("NIF: ");
            JButton jbMostrarFacturasCliente = new JButton("Mostrar facturas");
            jbMostrarFacturasCliente.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel mostrarFacturas = new JLabel(modelo.mostrarFacturasCliente(jtfNif1.getText()));
                    JFrame ventana = new JFrame("Mostrar facturas cliente");
                    ventana.add(mostrarFacturas);
                    ventana.pack();
                    ventana.setVisible(true);
                    System.out.println("Mostrando facturas del cliente con Nif "+ jtfNif1.getText());
                }
            });
            this.add(nif1Label);
            this.add(jtfNif1);
            this.add(jbMostrarFacturasCliente);
        }
    }

    private class MostrarFacturasEntreFechas extends JPanel {
        public MostrarFacturasEntreFechas() {
            this.ejecuta();
        }

        private void ejecuta(){
            JTextField jtfFecha1 = new JTextField(20);
            JLabel Fecha1Label = new JLabel("Fecha Inicio (yyyy-mm-ddThh:mm:ss): ");
            JTextField jtfFecha2 = new JTextField(20);
            JLabel Fecha2Label = new JLabel("Fecha Final (yyyy-mm-ddThh:mm:ss): ");
            JButton jbMostrarFacturas = new JButton("Mostrar facturas");
            jbMostrarFacturas.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel facturas= new JLabel(modelo.mostrarClientesEntreFechas(LocalDateTime.parse(jtfFecha1.getText()),LocalDateTime.parse(jtfFecha2.getText())));
                    JFrame mostrarFactura= new JFrame("Mostrar facturas entre fecha");
                    mostrarFactura.add(facturas);
                    mostrarFactura.pack();
                    mostrarFactura.setVisible(true);
                    System.out.println("Mostrando facturas entre "+jtfFecha1.getText()+" y "+jtfFecha2.getText());
                }
            });
            this.add(Fecha1Label);
            this.add(jtfFecha1);
            this.add(Fecha2Label);
            this.add(jtfFecha2);
            this.add(jbMostrarFacturas);
        }
    }

}
