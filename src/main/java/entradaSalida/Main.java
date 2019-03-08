package entradaSalida;

import entradaSalida.InterfazUsuario;
import excepciones.TarifaException;

public class Main {
    public static void main(String[] args) throws TarifaException {
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.menu();
        //GeneradorDatosINE a = new GeneradorDatosINE();

    }
}
