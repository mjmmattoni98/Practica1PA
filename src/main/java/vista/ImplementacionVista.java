package vista;

import controlador.Controlador;
import modelo.InterrogaModelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImplementacionVista implements InterrogaVista, InformaVista {
    private Controlador controlador; //Controlador es un interface
    private InterrogaModelo modelo; //Modelo es un interface

    class Escuchador implements ActionListener {
        @Override
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

    public void nuevaEntrada() {
//        String infoEstadoEntradas = "Numero de entradas: " +
//                modelo.getPoscionEntradaActual() + " de " +
//                modelo.getNumeroEntradas();
        // Muestra nueva información.
    }


}