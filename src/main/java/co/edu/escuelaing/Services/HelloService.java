package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RequestParam;
import co.edu.escuelaing.Annotations.RestController;

@RestController
@RequestMapping("/app")
public class HelloService {

    @GetMapping("/hello")
    public static String hello(@RequestParam(value = "name", defaultValue = "World")String name){
        return "Hello " + name;
    }
}

  

