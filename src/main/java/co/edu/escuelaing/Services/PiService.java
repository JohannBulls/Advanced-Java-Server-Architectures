package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RequestParam;
import co.edu.escuelaing.Annotations.RestController;

/**
 * A REST controller for providing the value of Pi.
 * This service handles HTTP requests to return the value of Pi formatted to a specified number of decimal places.
 */
@RestController
@RequestMapping("/app")
public class PiService {

    /**
     * Handles GET requests to retrieve the value of Pi.
     * This method responds to requests at the "/app/pi" endpoint and returns the value of Pi formatted to the specified number of decimal places.
     * If the "decimals" parameter is not provided, it defaults to 2 decimal places.
     * If a negative number is provided, it defaults to 0 decimal places.
     * 
     * @param decimals The number of decimal places to format Pi to. Defaults to 2 if not provided. 
     *                 If the value is negative, it defaults to 0.
     * @return A string containing the value of Pi formatted to the specified number of decimal places.
     */
    @GetMapping("/pi")
    public static String pi(@RequestParam(value = "decimals", defaultValue = "2") int decimals) {
        if (decimals < 0) decimals = 0;
        String format = "%." + decimals + "f";
        return String.format(format, Math.PI);
    }
}
