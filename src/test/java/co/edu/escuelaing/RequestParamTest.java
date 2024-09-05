package co.edu.escuelaing;

import co.edu.escuelaing.Annotations.RequestParam;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RequestParamTest {

    // Clase de ejemplo con métodos anotados con @RequestParam
    public static class ExampleClass {

        public void exampleMethod(@RequestParam(value = "paramA", defaultValue = "defaultA") String a,
                                   @RequestParam(value = "paramB") String b) {
        }
    }

    @Test
    public void testRequestParamAnnotation() throws NoSuchMethodException {
        // Obtener el método de ejemplo
        Method method = ExampleClass.class.getMethod("exampleMethod", String.class, String.class);

        // Obtener las anotaciones de los parámetros
        Annotation[][] annotations = method.getParameterAnnotations();

        // Verificar la anotación en el primer parámetro
        RequestParam paramAAnnotation = (RequestParam) annotations[0][0];
        assertNotNull("RequestParam annotation should be present on the first parameter", paramAAnnotation);
        assertEquals("The value of the first parameter should be 'paramA'", "paramA", paramAAnnotation.value());
        assertEquals("The default value of the first parameter should be 'defaultA'", "defaultA", paramAAnnotation.defaultValue());

        // Verificar la anotación en el segundo parámetro
        RequestParam paramBAnnotation = (RequestParam) annotations[1][0];
        assertNotNull("RequestParam annotation should be present on the second parameter", paramBAnnotation);
        assertEquals("The value of the second parameter should be 'paramB'", "paramB", paramBAnnotation.value());
        assertEquals("The default value of the second parameter should be an empty string", "", paramBAnnotation.defaultValue());
    }
}
