import gestion.datos.GestionClientes;
import excepciones.*;
import gestion.datos.GestionFacturas;
import gestion.datos.GestionLlamadas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class GestionClientesTest {
    private GestionClientes gestionClientes = new GestionClientes();
    private GestionFacturas gestionFacturas = new GestionFacturas();
    private GestionLlamadas gestionLlamadas = new GestionLlamadas();

    @Before
    public void beforeTest() throws TarifaException, NIFException{
        gestionClientes.addClienteParticular("12345678A", "ana", 12345, "Valencia", "Valencia", "anaprueba@correo.com", 15.0, "Bachueca Gimenez");
    }

    @After
    public void afterTest() {
        gestionClientes.getClientes().remove("12345678A");
    }

    @Test
    public void testCodigoFactura() {
        assertThat(gestionFacturas.getCodigoFactura(), is(0));
    }

    @Test
    public void testGetCliente() throws TarifaException{
        assertThat(gestionClientes.getCliente("12345678A").getNif(), is("12345678A"));
    }

    @Test
    public void testEmitirFacturaCliente() throws TarifaException{
        assertThat(gestionFacturas.emitirFacturaCliente("12345678A").getTarifa(), is(15.0));
        assertThat(gestionFacturas.getCodigoFactura(), is(1));
    }

    @Test
    public void testBorrarCliente() throws TarifaException{
        assertThat(gestionClientes.borrarCliente("12345678A").containsKey("12345678A"), is(false));
    }

    @Test
    public void testGetFacturasCliente() throws TarifaException{
        gestionFacturas.emitirFacturaCliente("12345678A");
        assertThat(gestionFacturas.getFacturasCliente("12345678A").get(gestionFacturas.getCodigoFactura()-1).getTarifa(), is(15.0));
    }
    @Test
    public void testGetLlamadasCliente() {
        gestionLlamadas.añadirLlamada("12345678A", 123456789, 12);
        assertThat(gestionLlamadas.getLlamadasCliente("12345678A").values().iterator().next().listIterator().next().getNumero(), is(123456789));
    }
}