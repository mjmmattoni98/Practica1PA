import empresa.telefonia.ClienteParticular;
import empresa.telefonia.Direccion;
import empresa.telefonia.Llamada;
import empresa.telefonia.Tarifa;
import excepciones.NIFException;
import excepciones.TarifaException;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ClienteTest {

    private ClienteParticular cliente = null;

    @Before
    public void beforeTest() throws TarifaException, NIFException {
        cliente = new ClienteParticular("pepe","57440683Q", new Direccion(11111,"Castellón","Castellón"),"peperamirez@correo.es",new Tarifa(10.0),"garcia");
    }

    @Test
    public void testEmitirFactura() throws TarifaException{
        assertThat(cliente.emitirFactura(0).getCodigo(),is(0));

    }
    @Test
    public void testSetTarifa() throws TarifaException {
        cliente.setTarifa(new Tarifa(5.0));
        assertThat(cliente.getTarifa().getTarifa(),is(5.0));
        //assertThat(cliente.getTarifa(),is(5.0));
    }
    @Test(expected = TarifaException.class)
    public void testExceptionSetTarifaMenorCero() throws TarifaException {
        //ClienteParticular aux = new ClienteParticular("pepe","57440683Q", new Direccion(11111,"Castellón","Castellón"),"peperamirez@correo.es",new Tarifa(-10.0),"garcia");
        cliente.setTarifa(new Tarifa(-1.0));
    }
    @Test
    public void testAñadirLlamada(){
        Llamada llamada = cliente.añadirLlamada(123456789,10);
        assertThat(cliente.getLlamadas().values().iterator().next().listIterator().next().getNumero(), is(123456789));
    }

    @Test
    public void testCrearCliente() throws TarifaException, NIFException {
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
        assertThat(clienteprueba.getDireccion().getCp(),is(12567));
        assertThat(clienteprueba.getDireccion().getPoblacion(),is("Almazora"));
        assertThat(clienteprueba.getDireccion().getProvincia(),is("Castellón"));
        assertThat(clienteprueba.getTarifa().getTarifa(),is((double) 15.0));
    }
}
