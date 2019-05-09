package empresa.telefonia;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String nif;
    private String correoElectronico;

    public Usuario(String nombre, String nif, String correoElectronico){
        this.nombre = nombre;
        this.nif = nif;
        this.correoElectronico = correoElectronico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }
}
