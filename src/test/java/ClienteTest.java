import clasesDescartadas.CorreoElectronicoException;
import clasesDescartadas.NumeroLlamadaException;
import empresaTelefonia.*;
import excepciones.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ClienteTest {

    private ClienteParticular cliente = null;

    @Before
    public void beforeTest() throws TarifaException, CorreoElectronicoException {
        cliente = new ClienteParticular("pepe","57440683Q", new Direccion(11111,"Castellón","Castellón"),"peperamirez@correo.es",new Tarifa(10.0),"garcia");
    }

    @Test
    public void testEmitirFactura() throws TarifaException{
        assertThat(cliente.emitirFactura(0).getCodigo(),is(0));

    }
    @Test
    public void testSetTarifa() throws TarifaException {
        cliente.setTarifa(new Tarifa(5.0d));
        assertThat(cliente.getTarifa(),is(5.0d));
    }
    @Test(expected = TarifaException.class)
    public void testSetTarifaMenorCero() throws TarifaException {
        cliente.setTarifa(new Tarifa(-1));
    }
    @Test
    public void testAñadirLlamada() throws NumeroLlamadaException {
        Llamada llamada = cliente.añadirLlamada(222222222,10);
        List<Llamada> listaLlamadas= cliente.getLlamadas().get(llamada.getFecha());
        assertThat(listaLlamadas.get(listaLlamadas.size()-1).getNumero(),is(222222222));
    }

    @Test
    public void testCrearCliente() throws TarifaException, CorreoElectronicoException {
        ClienteParticular clienteprueba = null;
        try {
            clienteprueba = new ClienteParticular("pepe", "03456456Z", new Direccion(12567, "Castellón", "Almazora"), "clienteprueba@correo.com", new Tarifa(15.0), "garcia");
        } catch (TarifaException e) {
            e.printStackTrace();
        }

        assertThat(clienteprueba.getCorreoElectronico(),is("clienteprueba@correo.com"));
        assertThat(clienteprueba.getNif(),is("03456456Z"));
        assertThat(clienteprueba.getNombre(),is("pepe"));
        assertThat(clienteprueba.getApellidos(),is("garcia"));
        assertThat(clienteprueba.getDireccion().getCp(),is("12567"));
        assertThat(clienteprueba.getDireccion().getPoblacion(),is("Almazora"));
        assertThat(clienteprueba.getDireccion().toString(),is("Castellón"));
        assertThat(clienteprueba.getTarifa().getTarifa(),is((double) 15.0));
    }
}
