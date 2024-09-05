package co.edu.escuelaing;
import co.edu.escuelaing.Services.SumResult;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SumResultTest {

    @Test
    public void testRandomWithParameters() {
        String result = SumResult.random(5, 3);
        assertEquals("Sum of 3 and 5: 8", result);
    }

    @Test
    public void testRandomWithDefaultParameters() {
        String result = SumResult.random(0, 1);
        assertEquals("Sum of 0 and 1: 1", result);
    }

    @Test
    public void testRandomWithSwapParameters() {
        String result = SumResult.random(-20, -10);
        assertEquals("Sum of -20 and -10: -30", result);
    }

    @Test
    public void testRandomWithResetParameters() {
        String result = SumResult.random(-20, 10);
        assertEquals("Sum of -20 and 10: -10", result);
    }
}
