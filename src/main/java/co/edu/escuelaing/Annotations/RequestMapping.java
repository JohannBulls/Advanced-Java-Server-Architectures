package co.edu.escuelaing.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Maps a class or method to a specific URL pattern for HTTP requests.
 * This annotation can be used at both the class level and method level to define 
 * the path that the annotated element should respond to.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /**
     * The path or URL pattern to which the class or method should respond.
     * If not specified, defaults to "/" (root path).
     * 
     * @return The URL pattern for the request mapping.
     */
    String value() default "/";
}
