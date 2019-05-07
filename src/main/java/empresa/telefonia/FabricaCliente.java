package empresa.telefonia;


public interface FabricaCliente {
//    Cliente getCliente(TipoCliente tipo);
    ClienteParticular getClienteParticular(Usuario usuario, Direccion direccion, Tarifa tarifa, String apellidos);
    ClienteEmpresa getClienteEmpresa(Usuario usuario, Direccion direccion, Tarifa tarifa);
}


