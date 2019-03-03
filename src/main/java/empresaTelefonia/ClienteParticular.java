package empresaTelefonia;

public class ClienteParticular extends  Cliente {
    private String apellidos;

    public ClienteParticular(String nombre, String nif, Direccion direccion, String correoElectronico, Tarifa tarifa, String apellidos){
        super(nombre, nif, direccion, correoElectronico, tarifa);
        this.apellidos = apellidos;
    }

    @Override
    public String toString(){
        return super.toString() + "Apellidos: " + apellidos;
    }

}
