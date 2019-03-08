import clasesDescartadas.CorreoElectronicoException;
import clasesDescartadas.NumeroLlamadaException;
import empresaTelefonia.*;
import excepciones.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class EmpresaTest {
    private Empresa empresa = new Empresa();

    @Before
    public void beforeTest() throws TarifaException, CorreoElectronicoException {
        empresa.añadirClienteParticular("12345678A", "ana", 12345, "Valencia", "Valencia", "anaprueba@correo.com", 15.0, "Bachueca Gimenez");
    }

    @After
    public void afterTest() {
        empresa.getClientes().remove("12345678A");
    }

    @Test
    public void testCodigoFactura() {
        assertThat(empresa.getCodigoFactura(), is(0));
    }

    @Test
    public void testGetCliente() throws TarifaException, CorreoElectronicoException {
        assertThat(empresa.getCliente("12345678A").getNif(), is("12345678A"));
    }

    @Test
    public void testEmitirFacturaCliente() throws TarifaException, CorreoElectronicoException {
        assertThat(empresa.emitirFacturaCliente("12345678A").getTarifa().getTarifa(), is(15.0));
    }

    @Test
    public void testBorrarCliente() throws TarifaException, CorreoElectronicoException {
        assertThat(empresa.borrarCliente("12345678A").containsKey("12345678A"), is(false));
    }

    @Test
    public void testGetFacturasCliente() throws TarifaException, CorreoElectronicoException {
        empresa.emitirFacturaCliente("12345678A");
        assertThat(empresa.getFacturasCliente("12345678A").get(empresa.getCodigoFactura()-1).getTarifa().getTarifa(), is(15.0));
    }
    @Test
    public void testGetLlamadasCliente() throws NumeroLlamadaException {
        empresa.añadirLlamada("12345678A", 123456789, 12);
        assertThat(empresa.getLlamadasCliente("12345678A").values().iterator().next().listIterator().next().getNumero(), is(123456789));
    }
}