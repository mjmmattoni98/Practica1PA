import empresa.telefonia.*;
import gestion.datos.BaseDatos;
import gestion.datos.GestionClientes;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ClienteTest {

    private FabricadoCliente fabricadoCliente = new FabricadoCliente();
    FabricadoTarifa fabricadoTarifa = new FabricadoTarifa();
    ClienteParticular cliente = null;

    @Before
    public void beforeTest() {
        GestionClientes.resetClients();
        Usuario usuario = new Usuario("pepe", "57440683Q", "peperamirez@correo.es");
        Direccion direccion = new Direccion(11111, "Castellón", "Castellón");
        cliente = fabricadoCliente.getClienteParticular(usuario, direccion, fabricadoTarifa.getTarifaBasica(), "garcia");
    }

    @Test
    public void testSetTarifa() {
        cliente.setTarifa(fabricadoTarifa.getTarifaBasica());
        assertThat(Double.parseDouble(cliente.getTarifa().toString()),is(15.0));

    }

    @Test
    public void testAñadirLlamada(){
        cliente.añadirLlamada(123456789,10);
        assertThat(cliente.getLlamadas().values().iterator().next().listIterator().next().getNumero(), is(123456789));
    }

    @Test
    public void testCrearCliente() {
        fabricadoCliente = new FabricadoCliente();
        Usuario usuario = new Usuario("pepe", "03456456Z", "clienteprueba@correo.com");
        Direccion direccion = new Direccion(12567, "Castellón", "Almazora");
        ClienteParticular clienteprueba = fabricadoCliente.getClienteParticular(usuario, direccion, fabricadoTarifa.getTarifaBasica(), "garcia");
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
        fabricadoCliente = new FabricadoCliente();
        Usuario usuario = new Usuario("pepe", "03456456Z", "alkdkm@pep.com");
        Direccion direccion = new Direccion(12530 , "Albacete", "Reus");
        Cliente cliente = fabricadoCliente.getClienteParticular(usuario, direccion, fabricadoTarifa.getTarifaBasica(), "Sapee");
        GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.addCliente(cliente);
        assertThat(cliente.getNif(),is("03456456Z"));
        assertThat(gestionClientes.getClientes().containsKey("03456456Z"),is(true));
    }

    @Test
    public void testFabricaClienteEmpresa() {
        fabricadoCliente = new FabricadoCliente();
        Usuario usuario = new Usuario("Ibai", "03456456Z", "alkdkm@pep.com");
        Direccion direccion = new Direccion(12530 , "Albacete", "Reus");
        Cliente cliente = fabricadoCliente.getClienteEmpresa(usuario, direccion, fabricadoTarifa.getTarifaBasica());
        GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.addCliente(cliente);
        assertThat(cliente.getNombre(),is("Ibai"));
        assertThat(gestionClientes.getClientes().containsKey("03456456Z"),is(true));
    }

    @Test
    public void testFabricaTarifa(){
        Tarifa tarifa = fabricadoTarifa.getTarifaBasica();
        LocalDateTime fecha = LocalDateTime.parse("2019-04-07T16:00:00");
        tarifa = fabricadoTarifa.getTarifaTardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = fabricadoTarifa.getTarifaDomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
    }
}
