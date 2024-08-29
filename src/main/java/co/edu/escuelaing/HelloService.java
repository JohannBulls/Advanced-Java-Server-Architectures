package co.edu.escuelaing;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@RestController
public class HelloService {

    @GetMapping("/hello")
    public static String hello() {
        return "Hello world!";
    }

    @GetMapping("/date")
    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    @GetMapping("/time")
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

    @GetMapping("/timezone")
    public static String getTimeZone() {
        TimeZone tz = TimeZone.getDefault();
        return tz.getID();
    }

}