import empresa.telefonia.DomingoGratis;
import empresa.telefonia.TardesReducidas;
import empresa.telefonia.Tarifa;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;


public class TarifasTest {

    @Test
    public void testTarifa() {
        Tarifa tarifa = Tarifa.tarifaBasica;
        LocalDateTime fecha = LocalDateTime.parse("2019-04-07T16:00:00");
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
        tarifa = Tarifa.tarifaBasica;
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(0.0));

        tarifa = Tarifa.tarifaBasica;
        fecha = LocalDateTime.parse("2019-04-07T16:00:00");
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
        tarifa = Tarifa.tarifaBasica;
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(0.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(0.0));

        tarifa = Tarifa.tarifaBasica;
        fecha = LocalDateTime.parse("2019-04-08T16:00:00");
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(5.0));
        tarifa = Tarifa.tarifaBasica;
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(5.0));

        tarifa = Tarifa.tarifaBasica;
        fecha = LocalDateTime.parse("2019-04-08T14:00:00");
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 5.0);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = Tarifa.tarifaBasica;
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new DomingoGratis(tarifa);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
        tarifa = new TardesReducidas(tarifa, 15.0);
        assertThat(tarifa.getTarifa(fecha), is(15.0));
    }
}
