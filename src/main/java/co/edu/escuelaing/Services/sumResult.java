package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RequestParam;
import co.edu.escuelaing.Annotations.RestController;

@RestController
@RequestMapping("/app")
public class SumResult {

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
