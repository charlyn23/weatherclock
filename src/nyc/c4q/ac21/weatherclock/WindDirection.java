package nyc.c4q.ac21.weatherclock;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.net.URL;

/**
 * Created by charlynbuchanan on 4/7/15.
 */
public class WindDirection
{

    public static String getWindDirection() {
        JSONParser windParser = new JSONParser();
        URL url = HTTP
                .stringToURL("http://api.openweathermap.org/data/2.5/weather?q=New%20York,NY");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONObject windStuff = (JSONObject) obj.get("wind");
        Double windDeg = (Double) windStuff.get("deg");
        //System.out.println("Wind direction: " + getWindDirection());

        while(true)
        {
            String windDirection = "";

            if((windDeg >= 348.75) && (windDeg <= 360))
            {
                windDirection += "North";
                //return windDirection;
            }
            if((windDeg >= 0) && (windDeg <= 11.25))
            {
                windDirection += "North";
                //return windDirection;
            }
            else if((windDeg > 11.25) && (windDeg < 33.75)) {
                windDirection += "NNE";
            }
            else if((windDeg >= 33.75) && (windDeg < 56.25)) {
                windDirection += "NE";
            }
            else if ((windDeg >= 56.25) && (windDeg < 78.75)) {
                windDirection += "ENE";
            }
            else if((windDeg >= 78.75) && (windDeg < 101.25)) {
                windDirection += "E";
            }
            else if ((windDeg >= 101.25) && (windDeg < 123.75)) {
                windDirection += "ESE";
            }
            else if ((windDeg >= 123.75) && (windDeg < 146.25)) {
                windDirection += "SE";
            }
            else if ((windDeg >= 146.25) && (windDeg < 168.75)) {
                windDirection += "SSE";
            }
            else if ((windDeg >= 168.75) && (windDeg < 191.25)) {
                windDirection += "S";
            }
            else if((windDeg >= 191.25) && (windDeg < 213.75)) {
                windDirection += "SSW";
            }
            else if ((windDeg >= 213.75) && (windDeg < 236.25)) {
                windDirection += "SW";
            }
            else if ((windDeg >= 236.25) && (windDeg < 258.75)) {
                windDirection += "WSW";
            }
            else if ((windDeg >= 258.75) && (windDeg < 251.25)) {
                windDirection += "W";
            }
            else if ((windDeg >=281.25) && (windDeg < 303.75)){
                windDirection += "WNW";
            }
            else if ((windDeg >= 303.75) && (windDeg < 326.25)) {
                windDirection += "NW";
            }
            else if ((windDeg >= 326.25) && (windDeg < 348.75)) {
                windDirection += "NNW";
            }
            //System.out.println("Wind direction: " + windDirection);
            return windDirection;

        }
    }
    public static void main(String [] args){
        getWindDirection();
    }

}
