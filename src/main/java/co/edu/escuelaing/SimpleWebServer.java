package co.edu.escuelaing;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import co.edu.escuelaing.Annotations.GetMapping;
import co.edu.escuelaing.Annotations.RequestParam;
import co.edu.escuelaing.Annotations.RestController;

/**
 * A simple web server implementation that handles HTTP requests and serves files or dynamic content.
 * This server supports both static file serving and dynamic request handling using annotated service methods.
 */
public class SimpleWebServer {
    private static final int PORT = 8080;
    public static final String WEB_ROOT = "src/main/java/co/edu/escuelaing/resources/";
    public static Map<String, Method> services = new HashMap<>();
    private static boolean running = true;

    /**
     * The entry point for starting the web server.
     * It initializes services, sets up a thread pool for handling client connections, and starts listening for incoming requests.
     *
     * @param args Command line arguments (not used).
     * @throws IOException If an I/O error occurs when creating the server socket.
     * @throws ClassNotFoundException If a service class cannot be found.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        loadServices();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Ready to receive on port " + PORT + "...");
        while (running) {
            Socket clientSocket = serverSocket.accept();
            threadPool.submit(new ClientHandler(clientSocket));
        }
        serverSocket.close();
        threadPool.shutdown();
    }

    /**
     * Loads service classes from the specified directory and registers methods annotated with {@link RestController} and {@link GetMapping}.
     *
     * @throws ClassNotFoundException If a service class cannot be found.
     * @throws IOException If an I/O error occurs while reading service classes.
     */
    private static void loadServices() throws ClassNotFoundException, IOException {
        File servicesDir = new File("src/main/java/co/edu/escuelaing/Services");
        if (servicesDir.exists() && servicesDir.isDirectory()) {
            for (File file : servicesDir.listFiles()) {
                if (file.getName().endsWith(".java")) {
                    String className = "co.edu.escuelaing.Services." + file.getName().replace(".java", "");
                    Class<?> serviceClass = Class.forName(className);

                    if (serviceClass.isAnnotationPresent(RestController.class)) {
                        Method[] methods = serviceClass.getDeclaredMethods();
                        for (Method method : methods) {
                            if (method.isAnnotationPresent(GetMapping.class)) {
                                String path = method.getAnnotation(GetMapping.class).value();
                                services.put(path, method);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Stops the web server by setting the running flag to false.
     */
    public static void stop() {
        running = false;
    }
}

/**
 * Handles client requests by reading input, processing it, and sending appropriate responses.
 * This class processes HTTP GET and POST requests, handles static files, and invokes service methods.
 */
class ClientHandler implements Runnable {
    private Socket clientSocket;

    /**
     * Constructs a ClientHandler for a given client socket.
     *
     * @param socket The client socket to handle.
     */
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream())) {

            String requestLine = in.readLine();
            if (requestLine == null)
                return;
            String[] tokens = requestLine.split(" ");
            String method = tokens[0];
            String fileRequested = tokens[1];

            printRequestLine(requestLine, in);

            if (fileRequested.startsWith("/app")) {
                handleAppRequest(method, fileRequested, out);
            } else {
                if (method.equals("GET")) {
                    handleGetRequest(fileRequested, out, dataOut);
                } else if (method.equals("POST")) {
                    handlePostRequest(fileRequested, out, dataOut);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close(); 
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Prints the request line and headers to the console.
     *
     * @param requestLine The request line from the client.
     * @param in The BufferedReader for reading request headers.
     */
    private void printRequestLine(String requestLine, BufferedReader in) {
        System.out.println("Request line: " + requestLine);
        String inputLine;
        try {
            while ((inputLine = in.readLine()) != null && !inputLine.isEmpty()) {
                System.out.println("Header: " + inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles HTTP GET requests for static files.
     * Responds with the file content if it exists or a 404 Not Found error if the file does not exist.
     *
     * @param fileRequested The requested file path.
     * @param out The PrintWriter for sending the response headers.
     * @param dataOut The BufferedOutputStream for sending the file data.
     * @throws IOException If an I/O error occurs while reading the file or sending the response.
     */
    private void handleGetRequest(String fileRequested, PrintWriter out, BufferedOutputStream dataOut)
            throws IOException {
        File file = new File(SimpleWebServer.WEB_ROOT, fileRequested);
        int fileLength = (int) file.length();
        String content = getContentType(fileRequested);

        if (file.exists()) {
            byte[] fileData = readFileData(file, fileLength);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-type: " + content);
            out.println("Content-length: " + fileLength);
            out.println();
            out.flush();
            dataOut.write(fileData, 0, fileLength);
            dataOut.flush();
        } else {
            out.println("HTTP/1.1 404 Not Found");
            out.println("Content-type: text/html");
            out.println();
            out.flush();
            out.println("<html><body><h1>File Not Found</h1></body></html>");
            out.flush();
        }
    }

    /**
     * Handles HTTP POST requests by reading the payload and sending a response with the received data.
     *
     * @param fileRequested The requested file path (not used in this implementation).
     * @param out The PrintWriter for sending the response headers.
     * @param dataOut The BufferedOutputStream for sending the response body.
     * @throws IOException If an I/O error occurs while reading the request body or sending the response.
     */
    private void handlePostRequest(String fileRequested, PrintWriter out, BufferedOutputStream dataOut)
            throws IOException {
        StringBuilder payload = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                payload.append(line);
            }
        }

        out.println("HTTP/1.1 200 OK");
        out.println("Content-type: text/html");
        out.println();
        out.println("<html><body><h1>POST data received:</h1>");
        out.println("<p>" + payload.toString() + "</p>");
        out.println("</body></html>");
        out.flush();
    }

    /**
     * Handles requests to dynamic endpoints (starting with "/app").
     * Invokes the appropriate service method based on the request path and query parameters.
     *
     * @param method The HTTP method (e.g., GET).
     * @param fileRequested The requested path, including query parameters.
     * @param out The PrintWriter for sending the response headers and body.
     */
    private void handleAppRequest(String method, String fileRequested, PrintWriter out) {
        out.println("HTTP/1.1 200 OK");
        out.println("Content-type: text/html");
        out.println();

        String[] pathAndQuery = fileRequested.substring(fileRequested.indexOf("/app/") + 4).split("\\?");
        String path = pathAndQuery[0];
        String query = pathAndQuery.length > 1 ? pathAndQuery[1] : "";

        Method serviceMethod = SimpleWebServer.services.get(path);
        if (serviceMethod != null) {
            try {
                Map<String, String> queryParams = parseQueryParams(query);
                Object[] parameters = new Object[serviceMethod.getParameterCount()];
                Class<?>[] parameterTypes = serviceMethod.getParameterTypes();
                Annotation[][] annotations = serviceMethod.getParameterAnnotations();

                for (int i = 0; i < annotations.length; i++) {
                    for (Annotation annotation : annotations[i]) {
                        if (annotation instanceof RequestParam) {
                            RequestParam requestParam = (RequestParam) annotation;
                            String paramName = requestParam.value();
                            String paramValue = queryParams.get(paramName);

                            if (paramValue == null || paramValue.isEmpty()) {
                                parameters[i] = convertToType(parameterTypes[i], requestParam.defaultValue());
                            } else {
                                parameters[i] = convertToType(parameterTypes[i], paramValue);
                            }
                        } else {
                            parameters[i] = null; 
                        }
                    }
                }

                String response = (String) serviceMethod.invoke(null, parameters);
                out.println(response);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("Error executing service method: " + e.getMessage());
            }
        } else {
            out.println("<html><body><h1>Service Not Found</h1></body></html>");
        }

        out.flush();
    }

    /**
     * Parses query parameters from a query string.
     *
     * @param query The query string.
     * @return A map of query parameter names and values.
     */
    private Map<String, String> parseQueryParams(String query) {
        Map<String, String> queryParams = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    queryParams.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return queryParams;
    }

    /**
     * Converts a string value to the specified type.
     *
     * @param type The target type.
     * @param value The string value to convert.
     * @return The converted value.
     */
    private Object convertToType(Class<?> type, String value) {
        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        } else if (type == float.class || type == Float.class) {
            return Float.parseFloat(value);
        } else if (type == long.class || type == Long.class) {
            return Long.parseLong(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else {
            return value; 
        }
    }

    /**
     * Determines the content type based on the file extension.
     *
     * @param fileRequested The requested file path.
     * @return The MIME type of the file.
     */
    private String getContentType(String fileRequested) {
        if (fileRequested.endsWith(".html"))
            return "text/html";
        else if (fileRequested.endsWith(".css"))
            return "text/css";
        else if (fileRequested.endsWith(".js"))
            return "application/javascript";
        else if (fileRequested.endsWith(".png"))
            return "image/png";
        else if (fileRequested.endsWith(".jpg"))
            return "image/jpeg";
        return "text/plain";
    }

    /**
     * Reads file data from the specified file.
     *
     * @param file The file to read.
     * @param fileLength The length of the file.
     * @return A byte array containing the file data.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];
        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null)
                fileIn.close();
        }
        return fileData;
    }
}
