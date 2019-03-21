package entrada.salida;

import excepciones.NIFException;

public class Main {
    public static void main(String[] args) throws NIFException {
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.menu();
//        GeneradorDatosINE a = new GeneradorDatosINE();

    }
}
