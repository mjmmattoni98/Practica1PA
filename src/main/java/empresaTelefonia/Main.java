package empresaTelefonia;

import es.uji.www.GeneradorDatosINE;
import excepciones.TarifaException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Main {
    public static void main(String[] args) throws TarifaException {
        InterfazUsuario interfazUsuario = new InterfazUsuario();
        interfazUsuario.menu();
        //GeneradorDatosINE a = new GeneradorDatosINE();

    }
}
