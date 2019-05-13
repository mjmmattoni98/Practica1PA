package modelo;

import vista.InformaVista;

public class ImplementacionModelo implements CambioModelo, InterrogaModelo {
    private InformaVista vista; //Vista es un interface

    public void anyadeEntrada(String entrada) {
//        entradas.add(entrada);// ← El Modelo se actualiza
//        posicionActual++;// ← El Modelo se actualiza
        vista.nuevaEntrada();// ← El Modelo notifica a la Vista
    }
}