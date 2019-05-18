package vista;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import controlador.Controlador;

import modelo.InterrogaModelo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImplementacionVista /*implements InterrogaVistaCliente, InformaVistaCliente */{
    private Controlador controlador;
    private InterrogaModelo modelo;
    /*private JTextField jtfNombre;
    private JLabel jlContador;

    public ImplementacionVista() {}

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    private void GUI() {
        JFrame ventana = new JFrame("Modelo/Vista/Controlador");
        Container contenedor = ventana.getContentPane();
        JPanel jpEntrada = new JPanel();
        JPanel jpContador = new JPanel();
        jtfNombre = new JTextField(20);
        Escuchador escuchador = new Escuchador();
        JButton jbNuevo = new JButton("Nuevo");
        jbNuevo.addActionListener(escuchador);
        JButton jbAtras = new JButton("Atras");
        jbAtras.addActionListener(escuchador);
        JButton jbAdelante = new JButton("Adelante");
        jbAdelante.addActionListener(escuchador);
        jlContador = new JLabel(infoEstadoEntradas());
        jpEntrada.add(jtfNombre);
        jpEntrada.add(jbNuevo);
        jpEntrada.add(jbAtras);
        jpEntrada.add(jbAdelante);
        jpContador.add(jlContador);
        contenedor.add(jpEntrada, BorderLayout.NORTH);
        contenedor.add(jpContador, BorderLayout.SOUTH);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack();
        ventana.setVisible(true);
    }

    public void creaGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI();
            }
        });
    }

    public void cambiaNombre(String nombre) {
        jtfNombre.setText(nombre);
    }

    @Override
    public void nuevaEntrada() {
        jlContador.setText(infoEstadoEntradas());
    }

    class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton)e.getSource();
            String texto = boton.getText();
            if(texto.equals("Nuevo"))
                controlador.anyadeEntrada();
            else if(texto.equals("Atras"))
                controlador.atras();
            else if(texto.equals("Adelante"))
                controlador.adelante();
        }
    }

    @Override
    public void entradaActualCambiada() {
        jtfNombre.setText(modelo.getEntradaActual());
        jlContador.setText(infoEstadoEntradas());
    }

    @Override
    public String getEntrada() {
        return jtfNombre.getText();
    }

    private String infoEstadoEntradas() {
        return "Numero de entradas: " +
                modelo.getPoscionEntradaActual() + " de " +
                modelo.getNumeroEntradas();
    }*/
}