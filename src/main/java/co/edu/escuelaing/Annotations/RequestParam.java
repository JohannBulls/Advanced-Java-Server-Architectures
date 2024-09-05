package co.edu.escuelaing.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Binds a method parameter to a web request parameter.
 * This annotation is used to extract parameters from the request and bind them to the method's parameters.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    /**
     * The name of the request parameter to bind to.
     * 
     * @return The name of the request parameter.
     */
    String value();

    /**
     * The default value to use if the request parameter is not provided.
     * 
     * @return The default value for the request parameter. Defaults to an empty string if not specified.
     */
    String defaultValue() default "";
}
