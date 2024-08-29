package co.edu.escuelaing;

import java.lang.reflect.Method;

public class JUnitECI {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> c = Class.forName(args[0]);
        
        Method[] methods = c.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(null);
                    System.out.println(method.getName() + " passed");
                } catch (Exception e) {
                    System.out.println(method.getName() + " failed: " + e.getCause());
                }
            }
        }
    }
}
