import empresa.telefonia.DomingoGratis;
import empresa.telefonia.FabricadoTarifa;
import empresa.telefonia.TardesReducidas;
import empresa.telefonia.Tarifa;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;


public class TarifasTest {
    Tarifa tarifa;
    LocalDateTime fecha;
    FabricadoTarifa fabricadoTarifa = new FabricadoTarifa();

    @Test
    public void testTarifaDomingoTarde() {
        tarifa = fabricadoTarifa.getTarifaBasica();
        fecha = LocalDateTime.parse("2019-04-07T16:00:00");
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
        tarifa = fabricadoTarifa.getTarifaBasica();
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(0.0));

        tarifa = fabricadoTarifa.getTarifaBasica();
        fecha = LocalDateTime.parse("2019-04-07T16:00:00");
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
        tarifa = fabricadoTarifa.getTarifaBasica();
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
    }

    @Test
    public void testTarifaLunesTarde() {
        tarifa = fabricadoTarifa.getTarifaBasica();
        fecha = LocalDateTime.parse("2019-04-08T16:00:00");
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = fabricadoTarifa.getTarifaBasica();
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
    }

    @Test
    public void testTarifaLunes() {
        tarifa = fabricadoTarifa.getTarifaBasica();
        fecha = LocalDateTime.parse("2019-04-08T14:00:00");
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = fabricadoTarifa.getTarifaBasica();
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 15.0);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
    }
}
