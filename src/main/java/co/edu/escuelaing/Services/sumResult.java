package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RequestParam;
import co.edu.escuelaing.Annotations.RestController;

/**
 * A REST controller for calculating the sum of two numbers.
 * This service handles HTTP requests to compute the sum of two integers and return the result.
 */
@RestController
@RequestMapping("/app")
public class sumResult {

    /**
     * Handles GET requests to calculate the sum of two numbers.
     * This method responds to requests at the "/app/random" endpoint. 
     * It accepts two query parameters "a" and "b", calculates their sum, and returns the result.
     * If the "a" parameter is greater than the "b" parameter, their values are swapped before computing the sum.
     * If the parameters are not provided, defaults are used: "a" defaults to 0 and "b" defaults to 1.
     * 
     * @param a The first integer to sum. Defaults to 0 if not provided.
     * @param b The second integer to sum. Defaults to 1 if not provided.
     * @return A string containing the sum of the two integers in the format "Sum of a and b: sum".
     */
    @GetMapping("/random")
    public static String random(@RequestParam(value = "a", defaultValue = "0") int a,
                                @RequestParam(value = "b", defaultValue = "1") int b) {

        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int sum = a + b;
        return String.format("Sum of %d and %d: %d", a, b, sum);
    }
}
