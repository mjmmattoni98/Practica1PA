package empresaTelefonia;

import clasesDescartadas.CorreoElectronicoException;

public class ClienteEmpresa extends Cliente {

    public ClienteEmpresa(String nombre, String nif, Direccion direccion, String correoElectronico, Tarifa tarifa) {
        super(nombre, nif, direccion, correoElectronico, tarifa);
    }

}
