package empresa.telefonia;

import java.io.Serializable;

public class Direccion implements Serializable {
    private int cp;
    private String provincia;
    private String poblacion;

    public Direccion(){ super(); }

    public Direccion(int cp, String provincia, String poblacion){
        this.cp = cp;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public int getCp(){
        return cp;
    }

    public String getProvincia(){
        return provincia;
    }

    public String getPoblacion(){
        return poblacion;
    }

    @Override
    public String toString(){
        return "\t-Código postal: " + cp + "\n\t-Provincia: " + provincia + "\n\t-Población: " + poblacion ;
    }

    public String toStringHtml(){
        return "\t-Código postal: " + cp + "<br>\t-Provincia: " + provincia + "<br>\t-Población: " + poblacion ;
    }

}
