package co.edu.escuelaing;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JUnitECI {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c = Class.forName("co.edu.escuelaing.ClassToBeTested");
        Method[] methods = c.getDeclaredMethods();
        List<Method> testMethods = new ArrayList<>();
        int passedTests = 0;
        int failedTests = 0;

        // Recoger métodos anotados con @Test
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }

        // Mostrar métodos anotados con @Test
        System.out.println("Métodos anotados con @Test:");
        for (Method testMethod : testMethods) {
            System.out.println(testMethod.getName());
        }

        // Ejecutar los métodos de prueba y contar resultados
        for (Method testMethod : testMethods) {
            try {
                testMethod.invoke(null); // Invoca el método estático
                System.out.println(testMethod.getName() + " pasó.");
                passedTests++;
            } catch (Exception e) {
                System.out.println(testMethod.getName() + " falló: " + e.getCause());
                failedTests++;
            }
        }

        // Mostrar resumen de resultados
        System.out.println("\nResumen de pruebas:");
        System.out.println("Total de pruebas: " + testMethods.size());
        System.out.println("Pruebas pasadas: " + passedTests);
        System.out.println("Pruebas fallidas: " + failedTests);
    }
}
