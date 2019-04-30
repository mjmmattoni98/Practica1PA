import empresa.telefonia.*;
import gestion.datos.GestionClientes;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ClienteTest {

    private FabricadoCliente fabricadoCliente = null;
    ClienteParticular cliente = null;

    @Before
    public void beforeTest() {
        fabricadoCliente = new FabricadoCliente("57440683Q", "pepe",11111, "Castellón", "Castellón", "peperamirez@correo.es", Tarifa.tarifaBasica, "garcia");
        cliente = (ClienteParticular) fabricadoCliente.getCliente(TipoCliente.PARTICULAR);
    }

    @Test
    public void testSetTarifa() {
        cliente.setTarifa(Tarifa.tarifaBasica);
        assertThat(Double.parseDouble(cliente.getTarifa().toString()),is(15.0));

    }

    @Test
    public void testAñadirLlamada(){
        cliente.añadirLlamada(123456789,10);
        assertThat(cliente.getLlamadas().values().iterator().next().listIterator().next().getNumero(), is(123456789));
    }

    @Test
    public void testCrearCliente() {
        fabricadoCliente = new FabricadoCliente("03456456Z", "pepe",12567, "Castellón", "Almazora", "clienteprueba@correo.com", Tarifa.tarifaBasica, "garcia");
        ClienteParticular clienteprueba = (ClienteParticular) fabricadoCliente.getCliente(TipoCliente.PARTICULAR);
        assertThat(clienteprueba.getCorreoElectronico(),is("clienteprueba@correo.com"));
        assertThat(clienteprueba.getNif(),is("03456456Z"));
        assertThat(clienteprueba.getNombre(),is("pepe"));
        assertThat(clienteprueba.getApellidos(),is("garcia"));
        assertThat(clienteprueba.getDireccion().getCp(),is(12567));
        assertThat(clienteprueba.getDireccion().getPoblacion(),is("Almazora"));
        assertThat(clienteprueba.getDireccion().getProvincia(),is("Castellón"));
        assertThat(Double.parseDouble(clienteprueba.getTarifa().toString()),is(15.0));
    }
    @Test
    public void testFabricaClienteParticular() {
        fabricadoCliente = new FabricadoCliente("03456456Z", "pepe", 12530 , "Albacete", "Reus", "alkdkm@pep.com", Tarifa.tarifaBasica, "Sapee");
        Cliente cliente = fabricadoCliente.getCliente(TipoCliente.PARTICULAR);
        GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.addCliente(cliente);
        assertThat(cliente.getNif(),is("03456456Z"));
        assertThat(gestionClientes.getClientes().containsKey("03456456Z"),is(true));
    }

    @Test
    public void testFabricaClienteEmpresa() {
        fabricadoCliente = new FabricadoCliente("03456756Z", "Ibai", 12530 , "Albacete","Reus","alkdkm@pep.com", Tarifa.tarifaBasica,"Sapee");
        Cliente cliente = fabricadoCliente.getCliente(TipoCliente.PARTICULAR);
        GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.addCliente(cliente);
        assertThat(cliente.getNombre(),is("Ibai"));
        assertThat(gestionClientes.getClientes().containsKey("03456456Z"),is(true));
    }

    @Test
    public void testFabricaTarifa(){
        LocalDateTime fecha = LocalDateTime.parse("2019-04-07T16:00:00");
        FabricadoTarifa fabrica = new FabricadoTarifa(Tarifa.tarifaBasica,5.0);
        TipoTarifa tipo = TipoTarifa.TARDES_REDUCIDAS;
        Tarifa tarifa = fabrica.getTarifa(tipo);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = fabrica.getTarifa(TipoTarifa.DOMINGO_GRATIS);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
    }
}
