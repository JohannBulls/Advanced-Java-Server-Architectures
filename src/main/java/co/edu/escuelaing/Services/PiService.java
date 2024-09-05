package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RequestParam;
import co.edu.escuelaing.Annotations.RestController;

@RestController
@RequestMapping("/app")
public class PiService {
    
    @GetMapping("/pi")
    public static String pi(@RequestParam(value = "decimals", defaultValue = "2") int decimals) {
        if (decimals < 0) decimals = 0;
        String format = "%." + decimals + "f";
        return String.format(format, Math.PI);
    }
}