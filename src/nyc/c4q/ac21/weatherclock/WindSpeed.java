package nyc.c4q.ac21.weatherclock;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import java.net.URL;

/**
 * Created by charlynbuchanan on 4/7/15.
 */
public class WindSpeed {
    public static Double getWindSpeed() {

        JSONParser windParser = new JSONParser();
        URL url = HTTP
                .stringToURL("http://api.openweathermap.org/data/2.5/weather?q=New%20York,NY");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        if (obj == null)
            return null;
        JSONObject windStuff = (JSONObject) obj.get("wind");
        if (windStuff == null)
            return null;
        Double windSpeed = (Double) windStuff.get("speed");

        return windSpeed;
    }

}
