package empresaTelefonia;


public class ClienteParticular extends  Cliente {
    private String apellidos;

    public ClienteParticular(String nombre, String nif, Direccion direccion, String correoElectronico, Tarifa tarifa, String apellidos) {
        super(nombre, nif, direccion, correoElectronico, tarifa);
        this.apellidos = apellidos;
    }

    public String getApellidos(){
        return apellidos;
    }

    @Override
    public String toString(){
        return super.toString() + "\n-Apellidos: " + apellidos;
    } //Va mal, no imprime apellidos.

}
