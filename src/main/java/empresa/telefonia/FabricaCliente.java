package empresa.telefonia;


public interface FabricaCliente {
    ClienteParticular getClienteParticular(Usuario usuario, Direccion direccion, Tarifa tarifa, String apellidos);
    ClienteEmpresa getClienteEmpresa(Usuario usuario, Direccion direccion, Tarifa tarifa);
}


