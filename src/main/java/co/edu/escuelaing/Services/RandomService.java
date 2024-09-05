package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RequestParam;
import co.edu.escuelaing.Annotations.RestController;

@RestController
@RequestMapping("/app")
public class RandomService {
    @GetMapping("/random")
    public static String random(@RequestParam(value = "min", defaultValue = "0") double min,
                                @RequestParam(value = "max", defaultValue = "1") double max) {
        if (min > max) {
            double temp = min;
            min = max;
            max = temp;
        }
        double randomValue = min + (Math.random() * (max - min));
        return String.format("Random value between %.2f and %.2f: %f", min, max, randomValue);
    }
}