package entradaSalida;

import java.util.Scanner;

public class ObtencionDato {
    private String consultaDato;
    private String mensajeError;

    public ObtencionDato(String consultaDato, String mensajeError){
        this.consultaDato = consultaDato;
        this.mensajeError = mensajeError;
    }

    public String comprobarDato(ComprobarDato comprobacion, Scanner scanner){
        String dato;
        boolean okay;
        do{
            System.out.println(consultaDato);
            dato = scanner.next();
            okay = comprobacion.comprobarDato(dato);
            if (!okay) {
                System.out.println(mensajeError);
                System.out.println("Por favor, vuelva a introducir el dato.");
            }
        }while (!okay);
        return dato;
    }

    public String comprobarDato(ComprobarDato comprobacion1, ComprobarDato comprobacion2, Scanner scanner){
        String dato;
        boolean okay;
        do{
            System.out.println(consultaDato);
            dato = scanner.next();
            okay = comprobacion1.comprobarDato(dato) || comprobacion2.comprobarDato(dato);
            if (!okay) {
                System.out.println(mensajeError);
                System.out.println("Por favor, vuelva a introducir el dato.");
            }
        }while (!okay);
        return dato;
    }
}
