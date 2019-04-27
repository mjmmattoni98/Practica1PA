package empresa.telefonia;

import excepciones.TarifaException;

public interface FabricaCliente {
    Cliente getCliente(TipoCliente tipo) throws TarifaException;
}


