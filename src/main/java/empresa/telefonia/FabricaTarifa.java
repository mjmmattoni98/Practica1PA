package empresa.telefonia;

import excepciones.TarifaException;

public interface FabricaTarifa {
    Tarifa getTarifa(TipoTarifa tipo) throws TarifaException;
}