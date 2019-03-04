
import empresaTelefonia.Cliente;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class ClienteTest {
    private static Cliente cliente;

    /*@BeforeClass
    public static void init() {
        aritmetica = new Aritmetica();
    }

    @AfterClass
    public static void finish() {
        aritmetica = null;
    }*/

   /* @Test
    public void testSuma() {
        fail();
        //assertThat(cliente, notNullValue());
        //assertThat(aritmetica.suma(2, 3), is(5.0));
    }

   /* @Test
    public void testResta() {
        assertEquals(3, aritmetica.resta(4, 1), 0);
    }

    @Test
    public void testMultiplicacion() {
        assertEquals(6, aritmetica.multiplicacion(2, 3), 0);
    }

    @Test
    public void testDivision() {
        assertEquals(5, aritmetica.division(10, 2), 0);
    }

    @Test(/*expected = ArithmeticException.class)*/
    /*public void divisionPorCero(){
        try {
            System.out.println(1 / 0);
            fail("Aqui no debe llegar");
        } catch (ArithmeticException e){

        }

    }*/

}
