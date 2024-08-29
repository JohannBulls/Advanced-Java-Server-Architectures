package co.edu.escuelaing.reflexionlab;

public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }
}
