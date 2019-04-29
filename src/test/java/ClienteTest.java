import empresa.telefonia.*;
import gestion.datos.GestionClientes;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ClienteTest {

    private ClienteParticular cliente = null;

    @Before
    public void beforeTest() {
        cliente = new ClienteParticular("pepe", "57440683Q", new Direccion(11111, "Castellón", "Castellón"), "peperamirez@correo.es", Tarifa.tarifaBasica, "garcia");
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
        ClienteParticular clienteprueba = new ClienteParticular("pepe", "03456456Z", new Direccion(12567, "Castellón", "Almazora"), "clienteprueba@correo.com", Tarifa.tarifaBasica, "garcia");

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
        FabricadoCliente fabrica = new FabricadoCliente("03456456Z", "pepe", 12530 , "Albacete", "Reus", "alkdkm@pep.com", Tarifa.tarifaBasica, "Sapee");
        TipoCliente tipo = TipoCliente.PARTICULAR;
        Cliente cliente = fabrica.getCliente(tipo);
        GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.addCliente(cliente);
        assertThat(cliente.getNif(),is("03456456Z"));
        assertThat(gestionClientes.getClientes().containsKey("03456456Z"),is(true));
    }

    @Test
    public void testFabricaClienteEmpresa() {
        FabricadoCliente fabrica = new FabricadoCliente("03456756Z", "Ibai", 12530 , "Albacete","Reus","alkdkm@pep.com", Tarifa.tarifaBasica,"Sapee");
        TipoCliente tipo = TipoCliente.getOpcion(1);
        Cliente cliente = fabrica.getCliente(tipo);
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
