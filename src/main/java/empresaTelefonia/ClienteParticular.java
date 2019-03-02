package empresaTelefonia;

public class ClienteParticular extends  Cliente {
    private String apellidos;

    public ClienteParticular(String nombre, String apellidos, String nif, Direccion direccion, String correoElectronico, Fecha fechaDeAlta, Tarifa tarifa){
        super(nombre, nif, direccion, correoElectronico, fechaDeAlta, tarifa);
        this.apellidos = apellidos;
    }

}
