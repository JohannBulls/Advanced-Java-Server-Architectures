package co.edu.escuelaing;
import co.edu.escuelaing.Annotations.RestController;
import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertTrue;

public class RestControllerTest {

    // Clase de ejemplo anotada con @RestController
    @RestController
    public static class ExampleController {
    }

    @Test
    public void testRestControllerAnnotation() {
        // Verificar si la clase ExampleController está anotada con @RestController
        boolean isRestController = ExampleController.class.isAnnotationPresent(RestController.class);
        
        // Asegurarse de que la clase esté anotada con @RestController
        assertTrue("ExampleController should be annotated with @RestController", isRestController);
    }
}
