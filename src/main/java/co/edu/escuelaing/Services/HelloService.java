package co.edu.escuelaing.Services;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestMapping;
import co.edu.escuelaing.Annotations.RequestParam;
import co.edu.escuelaing.Annotations.RestController;

/**
 * A REST controller for handling greeting requests.
 * This service provides an endpoint to return a greeting message based on a provided name parameter.
 */
@RestController
@RequestMapping("/app")
public class HelloService {

    /**
     * Handles GET requests to return a greeting message.
     * This method responds to requests at the "/app/hello" endpoint. 
     * It accepts a query parameter "name" and returns a greeting message.
     * If the "name" parameter is not provided, it defaults to "World".
     * 
     * @param name The name to include in the greeting message. Defaults to "World" if not provided.
     * @return A greeting message in the format "Hello {name}".
     */
    @GetMapping("/hello")
    public static String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }
}
