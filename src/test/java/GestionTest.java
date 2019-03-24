import empresa.telefonia.Cliente;
import empresa.telefonia.Factura;
import empresa.telefonia.Llamada;
import gestion.datos.GestionClientes;
import excepciones.*;
import gestion.datos.GestionFacturas;
import gestion.datos.GestionLlamadas;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;

public class GestionTest {
    private GestionClientes gestionClientes = new GestionClientes();
    private GestionFacturas gestionFacturas = new GestionFacturas();
    private GestionLlamadas gestionLlamadas = new GestionLlamadas();

    @Before
    public void beforeTest() throws TarifaException{
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
    public void testGetCliente(){
        assertThat(gestionClientes.getCliente("12345678A").getNif(), is("12345678A"));
    }

    @Test
    public void testEmitirFacturaCliente() throws TarifaException{
        assertThat(gestionFacturas.emitirFacturaCliente("12345678A").getTarifa(), is(15.0));
        assertThat(gestionFacturas.getCodigoFactura(), is(1));
    }

    @Test
    public void testBorrarCliente(){
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

    @Test
    public void testIntervaloClientes() throws TarifaException{
        gestionClientes.addClienteParticular("93456872D", "marcos", 12345, "Valencia", "Valencia", "marcosprueba@correo.com", 1.34, "Apelli2");
        gestionClientes.addClienteParticular("48567392B", "broh", 12345, "Alicante", "Marte", "broh@correo.com", 10.0, "quepasa brohh");
        LocalDateTime fechaInicio = LocalDateTime.parse("2018-11-03T10:15:30");
        LocalDateTime fechaFinal = LocalDateTime.parse("2019-12-03T10:15:30");
        Set<Cliente> setClientes = new HashSet<>();
        setClientes.addAll(gestionClientes.getClientes().values());
        Iterator<Cliente> iter= gestionClientes.filtrarEntreFechas(setClientes,fechaInicio,fechaFinal).iterator();

        ArrayList<String> listaNifs= new ArrayList<>();

        while(iter.hasNext()){
            listaNifs.add(iter.next().getNif());
        }
        Collections.sort(listaNifs);

        assertThat(listaNifs.get(0), is("12345678A"));
        assertThat(listaNifs.get(1), is("48567392B"));
        assertThat(listaNifs.get(2), is("93456872D"));
    }
    @Test
    public void testIntervaloLlamadasCliente(){
        gestionLlamadas.añadirLlamada("12345678A",235642245,12);
        gestionLlamadas.añadirLlamada("12345678A",123456789,23);

        LocalDateTime fechaInicio = LocalDateTime.parse("2018-11-03T10:15:30");
        LocalDateTime fechaFinal = LocalDateTime.parse("2019-12-03T10:15:30");
        Set<Llamada> setLlamadas = new HashSet<>();
        for ( List<Llamada> listaLlamadas : gestionLlamadas.getLlamadasCliente("12345678A").values()){
            setLlamadas.addAll(listaLlamadas);
        }
        setLlamadas = gestionClientes.filtrarEntreFechas(setLlamadas,fechaInicio,fechaFinal);
        List <Llamada> listaLlamadas= new ArrayList<>();
        listaLlamadas.addAll(setLlamadas);
        Collections.sort(listaLlamadas);
        assertThat(listaLlamadas.get(0).getNumero(), is(123456789));
        assertThat(listaLlamadas.get(1).getNumero(), is(235642245));
    }
    @Test
    public void testIntervaloFacturasCliente() throws TarifaException{
        gestionLlamadas.añadirLlamada("12345678A",235642245,12);
        gestionLlamadas.añadirLlamada("12345678A",123456789,23);
        gestionFacturas.emitirFacturaCliente("12345678A");
        gestionFacturas.emitirFacturaCliente("12345678A");

        LocalDateTime fechaInicio = LocalDateTime.parse("2018-11-03T10:15:30");
        LocalDateTime fechaFinal = LocalDateTime.parse("2019-12-03T10:15:30");
        Set<Factura> setFacturas = new HashSet<>();
        setFacturas.addAll(gestionFacturas.getFacturasCliente("12345678A").values());

        Iterator<Factura> iter = gestionClientes.filtrarEntreFechas(setFacturas,fechaInicio,fechaFinal).iterator();
        List<Integer> listaCodigos= new ArrayList<>();

        while(iter.hasNext()){
            listaCodigos.add(iter.next().getCodigo());
        }

        Collections.sort(listaCodigos);

        assertThat(listaCodigos.get(0), is(0));
        assertThat(listaCodigos.get(1), is(1));
    }
}