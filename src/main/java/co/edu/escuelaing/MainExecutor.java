package co.edu.escuelaing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainExecutor {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (args.length < 3) {
            throw new IllegalArgumentException("At least 3 arguments are required");
        }

        Class<?> c = Class.forName(args[0]);
        Class<?>[] mainParamtypes = {String[].class};
        Method main = c.getDeclaredMethod("main", mainParamtypes);
        String[] params = {args[1], args[2]};

        main.invoke(null, (Object) params);
    }
}
