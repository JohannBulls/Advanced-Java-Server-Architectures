package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A REST controller for providing the current time.
 * This service handles HTTP requests related to time and responds with the current time in a formatted string.
 */
@RestController
@RequestMapping("/app")
public class CurrentTimeService {

    /**
     * Handles GET requests to retrieve the current time.
     * This method responds to requests at the "/app/current-time" endpoint and returns the current time formatted as "HH:mm:ss".
     * 
     * @return A string containing the current time in the format "Current time is: HH:mm:ss".
     */
    @GetMapping("/current-time")
    public static String currentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = LocalTime.now().format(formatter);
        return String.format("Current time is: %s", formattedTime);
    }
}
