package co.edu.escuelaing.reflexionlab;

import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintWriter;

public class MicroSpringBoot {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Debe proporcionar el nombre de la clase POJO");
            return;
        }

        Class<?> pojoClass = Class.forName(args[0]);
        Method[] methods = pojoClass.getDeclaredMethods();

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    InputStream input = clientSocket.getInputStream();
                    OutputStream output = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);

                    // Simplemente lee la primera línea de la solicitud HTTP
                    byte[] buffer = new byte[1024];
                    input.read(buffer);
                    String requestLine = new String(buffer).split("\r\n")[0];
                    String path = requestLine.split(" ")[1];

                    boolean routeFound = false;

                    for (Method method : methods) {
                        if (method.isAnnotationPresent(RequestMapping.class)) {
                            RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                            if (requestMapping.value().equals(path)) {
                                String response = (String) method.invoke(pojoClass.newInstance());
                                writer.println("HTTP/1.1 200 OK");
                                writer.println("Content-Type: text/html");
                                writer.println();
                                writer.println(response);
                                routeFound = true;
                                break;
                            }
                        }
                    }

                    if (!routeFound) {
                        writer.println("HTTP/1.1 404 Not Found");
                        writer.println();
                        writer.println("404 Not Found");
                    }

                    writer.flush();
                }
            }
        }
    }
}
