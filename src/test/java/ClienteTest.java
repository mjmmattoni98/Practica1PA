import empresa.telefonia.*;
import gestion.datos.GestionClientes;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class ClienteTest {

    private ClienteParticular cliente = null;

    @Before
    public void beforeTest() {
        cliente = new ClienteParticular("pepe","57440683Q", new Direccion(11111,"Castellón","Castellón"),"peperamirez@correo.es",new TarifaBasica(15.0),"garcia");
    }

    @Test
    public void testSetTarifa() {
        cliente.setTarifa(new TarifaBasica(15.0));
        assertThat(Double.parseDouble(cliente.getTarifa().toString()),is(15.0));

    }
    /*@Test(expected = TarifaException.class)
    public void testExceptionSetTarifaMenorCero() throws TarifaException {
        cliente.setTarifa(new TarifaBasica());
    }
    */
    @Test
    public void testAñadirLlamada(){
        cliente.añadirLlamada(123456789,10);
        assertThat(cliente.getLlamadas().values().iterator().next().listIterator().next().getNumero(), is(123456789));
    }

    @Test
    public void testCrearCliente() {
        ClienteParticular clienteprueba = null;
        //try {
        clienteprueba = new ClienteParticular("pepe", "03456456Z", new Direccion(12567, "Castellón", "Almazora"), "clienteprueba@correo.com", new TarifaBasica(15.0), "garcia");
//        } catch (TarifaException e) {
//            e.printStackTrace();
//        }

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
        FabricadoCliente fabrica = new FabricadoCliente("03456456Z", "pepe", 12530 , "Albacete","Reus","alkdkm@pep.com",12.3,"Sapee");
        TipoCliente tipo = TipoCliente.PARTICULAR;
        Cliente cliente = fabrica.getCliente(tipo);
        GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.addCliente(cliente);
        assertThat(cliente.getNif(),is("03456456Z"));
        assertThat(gestionClientes.getClientes().containsKey("03456456Z"),is(true));
    }

    @Test
    public void testFabricaClienteEmpresa() {
        FabricadoCliente fabrica = new FabricadoCliente("03456756Z", "Ibai", 12530 , "Albacete","Reus","alkdkm@pep.com",12.3,"Sapee");
        TipoCliente tipo = TipoCliente.getOpcion(1);
        Cliente cliente = fabrica.getCliente(tipo);
        GestionClientes gestionClientes = new GestionClientes();
        gestionClientes.addCliente(cliente);
        assertThat(cliente.getNombre(),is("Ibai"));
        assertThat(gestionClientes.getClientes().containsKey("03456456Z"),is(true));
    }

    @Test
    public void testFabricaTarifa(){
        FabricadoTarifa fabrica = new FabricadoTarifa(10,5.0);
        TipoTarifa tipo = TipoTarifa.TARDES_REDUCIDAS;
        Tarifa tarifa = fabrica.getTarifa(tipo);
        assertThat(Double.parseDouble(tarifa.toString()),is(5.0));
    }
}
