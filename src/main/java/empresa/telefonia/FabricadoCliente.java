package empresa.telefonia;

public class FabricadoCliente implements FabricaCliente {

    @Override
    public ClienteParticular getClienteParticular(Usuario usuario, Direccion direccion, Tarifa tarifa, String apellidos) {
        return new ClienteParticular(usuario, direccion, tarifa, apellidos);
    }

    @Override
    public ClienteEmpresa getClienteEmpresa(Usuario usuario, Direccion direccion, Tarifa tarifa) {
        return new ClienteEmpresa(usuario, direccion, tarifa);
    }
}
