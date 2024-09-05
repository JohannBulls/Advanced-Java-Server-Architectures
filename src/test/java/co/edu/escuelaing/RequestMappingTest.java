package co.edu.escuelaing;
import co.edu.escuelaing.Annotations.RequestMapping;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RequestMappingTest {

    @RequestMapping("/class-level")
    public static class ExampleClass {

        @RequestMapping("/method-level")
        public void exampleMethod() {
        }
    }

    @Test
    public void testClassLevelRequestMapping() {
        // Act
        RequestMapping classAnnotation = ExampleClass.class.getAnnotation(RequestMapping.class);

        // Assert
        assertNotNull("RequestMapping annotation should be present at the class level", classAnnotation);
        assertEquals("The value of the class-level annotation should be '/class-level'", "/class-level", classAnnotation.value());
    }

    @Test
    public void testMethodLevelRequestMapping() throws NoSuchMethodException {
        // Act
        Method method = ExampleClass.class.getMethod("exampleMethod");
        RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);

        // Assert
        assertNotNull("RequestMapping annotation should be present at the method level", methodAnnotation);
        assertEquals("The value of the method-level annotation should be '/method-level'", "/method-level", methodAnnotation.value());
    }

}
