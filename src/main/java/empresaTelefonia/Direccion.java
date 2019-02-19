package empresaTelefonia;

public class Direccion {
    private int cp;
    private String provincia;
    private String poblacion;

    public Direccion(){
        super();
    }

    public Direccion(int cp, String provincia, String poblacion){
        this.cp = cp;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public String getDireccion(){
        return "Código postal: " + cp + "\nProvincia: " + provincia + "\nPoblación: " + poblacion ;
    }
}
