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
            dato = scanner.next();
            okay = comprobacion.comprobarDato(dato);
            if (!okay) {
                System.out.println(mensajeError);
                System.out.println("Por favor, vuelva a introducir el dato.");
            }
        }while (!okay);
        return dato;
    }

}
