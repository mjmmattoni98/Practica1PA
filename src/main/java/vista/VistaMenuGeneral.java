package vista;

import controlador.Controlador;
import controlador.ImplementacionControlador;
import modelo.ImplementacionModelo;
import modelo.InterrogaModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// todo La vista debe implementar una interfaz, o varias, con los métodos que necesita el controlador y el modelo
public class VistaMenuGeneral {
    InterrogaModelo modelo;
    Controlador controlador;

    public  VistaMenuGeneral(){
        super();
    }

    public VistaMenuGeneral(InterrogaModelo modelo, Controlador controlador){
        this.modelo = modelo;
        this.controlador = controlador;
    }

    public void ejecutaGUI() {
        SwingUtilities.invokeLater(this::ejecuta);
    }

    private void ejecuta(){
        JFrame ventana = new JFrame("Primera prueba.");

        Container container = ventana.getContentPane();

        JPanel jPanel = new JPanel();
        JButton jbClientes = new JButton("Prueba a menu cliente.");
        jbClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yendo al menu del cliente.");
                new VistaMenuCliente(modelo, controlador).ejecutaGUI();
                ventana.dispose();
            }
        });

        JButton jbFacturas = new JButton("Prueba a menu facturas.");
        jbFacturas.addActionListener(e -> {
            System.out.println("Yendo al menu del facturas.");
            new VistaMenuFacturas(modelo, controlador).ejecutaGUI();
            ventana.dispose();
        });

        JButton jbLlamadas = new JButton("Prueba a menu llamadas.");
        jbLlamadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Yendo al menu del llamadas.");
                new VistaMenuLlamadas(modelo, controlador).ejecutaGUI();
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

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }
}
