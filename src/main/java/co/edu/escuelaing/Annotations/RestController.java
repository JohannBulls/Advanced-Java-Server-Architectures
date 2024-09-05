package co.edu.escuelaing.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a class is a REST controller.
 * This annotation is used to mark a class as a RESTful controller that handles HTTP requests 
 * and provides responses, typically in a web application.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestController {

}
