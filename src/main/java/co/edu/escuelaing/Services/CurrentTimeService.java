package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RestController;

@RestController
@RequestMapping("/app")
public class CurrentTimeService {

    @GetMapping("/current-time")
    public static String currentTime() {
        return String.format("Current time is: %s", java.time.LocalTime.now().toString());
    }
}