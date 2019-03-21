package entrada.salida;

import java.util.Scanner;

public class ObtencionDato {
    private String consultaDato;
    private String mensajeError;

    public ObtencionDato(){
        super();
    }

    public ObtencionDato withConsulta(String consulta){
        this.consultaDato = consulta;
        return this;
    }

    public ObtencionDato withMensajeError(String mensajeError){
        this.mensajeError = mensajeError;
        return this;
    }

    public String comprobarDato(ComprobarDato comprobacion, Scanner scanner){
        String dato;
        boolean okay;
        do{
            System.out.println(consultaDato);
            dato = scanner.nextLine();
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
            dato = scanner.nextLine();
            okay = comprobacion1.comprobarDato(dato) || comprobacion2.comprobarDato(dato);
            if (!okay) {
                System.out.println(mensajeError);
                System.out.println("Por favor, vuelva a introducir el dato.");
            }
        }while (!okay);
        return dato;
    }
}
