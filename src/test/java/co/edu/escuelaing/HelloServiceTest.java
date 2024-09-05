package co.edu.escuelaing;

import static org.junit.Assert.assertEquals;

import co.edu.escuelaing.Services.HelloService;
import org.junit.Test;

public class HelloServiceTest {

    @Test
    public void testHelloWithName() {
        HelloService service = new HelloService();

        // Call the method and verify the output
        String result = service.hello("Johann");
        String expected = "Hello Johann";

        assertEquals(expected, result);
    }

    @Test
    public void testHelloWithoutName() {
        HelloService service = new HelloService();

        // Call the method and verify the output
        String result = service.hello(null);
        String expected = "Hello null";

        assertEquals(expected, result);
    }
}
