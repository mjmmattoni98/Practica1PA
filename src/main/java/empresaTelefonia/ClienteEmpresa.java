package empresaTelefonia;

public class ClienteEmpresa extends Cliente {

    public ClienteEmpresa(String nombre, String nif, Direccion direccion, String correoElectronico, Tarifa tarifa) throws TarifaException {
        super(nombre, nif, direccion, correoElectronico, tarifa);
    }

}
