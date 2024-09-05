package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RequestParam;
import co.edu.escuelaing.Annotations.RestController;

@RestController
@RequestMapping("/app")
public class sumResult {

    @GetMapping("/random")
    public static String random(@RequestParam(value = "min", defaultValue = "0") int min,
                                @RequestParam(value = "max", defaultValue = "1") int max) {

        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }

        int randomValue = min + (int)(Math.random() * (max - min + 1));

        int sum = min + max;
        return String.format("Sum of %d and %d: %d", min, max, sum);
    }
}
