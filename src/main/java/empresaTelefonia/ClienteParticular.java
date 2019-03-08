package empresaTelefonia;


public class ClienteParticular extends  Cliente {
    private String apellidos;

    public ClienteParticular(String nombre, String nif, Direccion direccion, String correoElectronico, Tarifa tarifa, String apellidos) throws TarifaException{
        super(nombre, nif, direccion, correoElectronico, tarifa);
        this.apellidos = apellidos;
    }

    public String getApellidos(){
        return apellidos;
    }

}
