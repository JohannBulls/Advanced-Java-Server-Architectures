package co.edu.escuelaing.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a method is mapped to a GET HTTP request.
 * This annotation can be used to mark methods that should handle GET requests in a web application.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetMapping {
    /**
     * The path or URL pattern to which the method should respond.
     * 
     * @return The URL pattern for the GET request.
     */
    public String value();
}
