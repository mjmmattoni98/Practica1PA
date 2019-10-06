package vista;

import controlador.Controlador;
import modelo.InterrogaModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaMenuGeneral implements InformaVista{
    InterrogaModelo modelo;
    Controlador controlador;
    VistaMenuLlamadas vistaMenuLlamadas;
    VistaMenuFacturas vistaMenuFacturas;
    VistaMenuCliente vistaMenuCliente;

    public VistaMenuGeneral(InterrogaModelo modelo, Controlador controlador){
        this.modelo = modelo;
        this.controlador = controlador;
        this.vistaMenuCliente = new VistaMenuCliente(modelo, controlador);
        this.vistaMenuFacturas = new VistaMenuFacturas(modelo, controlador);
        this.vistaMenuLlamadas = new VistaMenuLlamadas(modelo, controlador);
    }

    public void ejecutaGUI() {
        SwingUtilities.invokeLater(this::ejecuta);
    }

    private void ejecuta(){
        JFrame ventana = new JFrame("Primera prueba.");

        Container container = ventana.getContentPane();

        JPanel jPanel = new JPanel();
        JButton jbClientes = new JButton("Acciones con clientes.");
        jbClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yendo al menu del cliente.");
                vistaMenuCliente.ejecutaGUI();
                ventana.dispose();
            }
        });

        JButton jbFacturas = new JButton("Acciones con facturas.");
        jbFacturas.addActionListener(e -> {
            System.out.println("Yendo al menu del facturas.");
            vistaMenuFacturas.ejecutaGUI();
            ventana.dispose();
        });

        JButton jbLlamadas = new JButton("Acciones con llamadas.");
        jbLlamadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yendo al menu del llamadas.");
                vistaMenuLlamadas.ejecutaGUI();
                ventana.dispose();
            }
        });

        jPanel.add(jbClientes);
        jPanel.add(jbFacturas);
        jPanel.add(jbLlamadas);

        container.add(jPanel);

        ventana.pack();
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        ventana,
                        "¿Estás seguro de que quieres cerrar la aplicación?",
                        "Confirmación de cierre",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (option == JOptionPane.YES_OPTION) {
                    System.out.println("Hasta luego desde general!!!!");
                    modelo.escribirDatos();
                    System.exit(0);
                }
            }
        });
    }

    @Override
    public void addedClient(String nif){
        System.out.println("Añadido cliente " + nif);
    }

    @Override
    public void removedClient(String nif){
        System.out.println("Cliente " + nif + " borrado.");
    }

    @Override
    public void addedCall(String nif, int numero){
        System.out.println("Agregada la llamada al número " + numero + " para el cliente " + nif);
    }

    @Override
    public void modifiedFee(String nif, String tipo){
        System.out.println("Agregada la tarifa " + tipo + " para el cliente " + nif);
    }

    @Override
    public void generatedBill(String nif){
        System.out.println("Emitida la factura del mes para el cliente " + nif);
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

}
