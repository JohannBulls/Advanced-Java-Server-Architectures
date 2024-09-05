package co.edu.escuelaing;

import static org.junit.Assert.assertTrue;

import co.edu.escuelaing.Services.CurrentTimeService;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimeServiceTest {

    @Test
    public void testCurrentTime() {
        CurrentTimeService service = new CurrentTimeService();

        // Setup expected time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String expectedTime = LocalTime.now().format(formatter);

        // Call the method and verify the output
        String result = service.currentTime();
        String expected = String.format("Current time is: %s", expectedTime);

        assertTrue(result.contains(expectedTime));
    }
}
