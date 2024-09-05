package co.edu.escuelaing;

import static org.junit.Assert.assertEquals;

import co.edu.escuelaing.Services.PiService;
import org.junit.Test;

public class PiServiceTest {

    @Test
    public void testPiWithDefaultDecimals() {
        PiService service = new PiService();

        // Call the method and verify the output
        String result = service.pi(2);
        String expected = String.format("%.2f", Math.PI);

        assertEquals(expected, result);
    }

    @Test
    public void testPiWithNoDecimals() {
        PiService service = new PiService();

        // Call the method and verify the output
        String result = service.pi(-1);
        String expected = String.format("%.0f", Math.PI);

        assertEquals(expected, result);
    }

    @Test
    public void testPiWithCustomDecimals() {
        PiService service = new PiService();

        // Call the method and verify the output
        String result = service.pi(5);
        String expected = String.format("%.5f", Math.PI);

        assertEquals(expected, result);
    }
}
