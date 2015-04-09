package nyc.c4q.ac21.weatherclock;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URL;

public class TPH {

    public static double kToF(double k) {
        double f = (k - 273.15) * 1.8 + 32;
        return f;
    }

    public static Double getTemp() {
        URL url = HTTP.stringToURL("http://api.openweathermap.org/data/2.5/weather?q=New%20York,NY");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONObject main = (JSONObject) obj.get("main");
        if (main == null)
            return null;
        Double temp = (Double) main.get("temp");
        if (temp == null)
            return null;
        return kToF(temp);
    }

    public static double paToHg(double pa) {
        double inHg = pa / 33.86389;
        return inHg;
    }

    public static Double getPressure() {
        URL url = HTTP.stringToURL("http://api.openweathermap.org/data/2.5/weather?q=New%20York,NY");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONObject main = (JSONObject) obj.get("main");
        if (main == null)
            return null;
        Double pressure = (Double) main.get("pressure");
        if (pressure == null)
            return null;
        return paToHg(pressure);
    }

    public static Long getHumid() {
        URL url = HTTP.stringToURL("http://api.openweathermap.org/data/2.5/weather?q=New%20York,NY");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONObject main = (JSONObject) obj.get("main");
        if (main == null)
            return null;
        Long humid = (Long) main.get("humidity");
        if (humid == null)
            return null;
        return humid;
    }

    public static Long getID() {
        URL url = HTTP.stringToURL("http://api.openweathermap.org/data/2.5/weather?q=New%20York,NY");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONArray weather = (JSONArray) obj.get("weather");
        JSONObject weatherStuff = (JSONObject) weather.get(0);
        Long id = (Long) weatherStuff.get("id");
       return id;

    }



//    public static void main (String [] args) {
//       getTemp();
//        System.out.println(getID());
//
//    }
}
