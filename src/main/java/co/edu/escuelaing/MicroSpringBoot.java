package co.edu.escuelaing;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MicroSpringBoot {
    public static void main(String[] args) throws Exception {
        // Cargar la clase HelloService
        Class<?> c = Class.forName("co.edu.escuelaing.HelloService");
        Map<String, Method> services = new HashMap<>();

        if (c.isAnnotationPresent(RestController.class)) {
            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(GetMapping.class)) {
                    GetMapping annotation = m.getAnnotation(GetMapping.class);
                    String key = annotation.value();
                    services.put(key, m);
                }
            }
        }

        // Simulación de solicitud HTTP
        URL serviceUrl = new URL("http://localhost:8080/hello");
        String path = serviceUrl.getPath();
        System.out.println("Path: " + path);
        String serviceName = path.substring(path.lastIndexOf('/'));
        System.out.println("Service name: " + serviceName);

        Method ms = services.get(serviceName);
        if (ms != null) {
            // Crear una instancia del servicio para invocar el método
            Object instance = c.getDeclaredConstructor().newInstance();
            System.out.println("respuesta servicio: " + ms.invoke(instance));
        } else {
            System.out.println("Servicio no encontrado");
        }
    }
}
