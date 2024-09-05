package co.edu.escuelaing;
import co.edu.escuelaing.Annotations.GetMapping;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetMappingTest {

    @Test
    public void testGetMappingAnnotation() throws NoSuchMethodException {
        // Dada una clase de ejemplo con la anotación
        class ExampleController {
            @GetMapping("/example")
            public void exampleMethod() {
            }
        }

        // Act
        Method method = ExampleController.class.getMethod("exampleMethod");
        GetMapping getMapping = method.getAnnotation(GetMapping.class);

        // Assert
        assertNotNull("GetMapping annotation should be present", getMapping);
        assertEquals("The value of the annotation should be '/example'", "/example", getMapping.value());
    }
}
