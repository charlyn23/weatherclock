package nyc.c4q.ac21.weatherclock;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import java.net.URL;

public class Weather {

    final static AnsiTerminal terminal = new AnsiTerminal();
    final static int numCols = TerminalSize.getNumColumns();
    final static int yPosition = numCols / 2 - 11;

    public static String weatherInfo() {
        URL url = HTTP.stringToURL("http://api.openweathermap.org/data/2.5/weather?q=New%20York");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);
        JSONArray weather = (JSONArray) obj.get("weather");
        if (weather == null)
            return null;
        JSONObject mainWeather = (JSONObject) weather.get(0);
        String description = (String) mainWeather.get("description");
        if (description == null)
            return null;
        return description;
    }

    public static void printWeather(long id, String weatherInfo){
        int xPosition = 15;
        int yPosition = numCols - 30;
        if(id >= 200 && id <= 232) {  // thunderstorm
            for (int i = 0; i < Ascii.thunder().size(); i++) {
                terminal.moveTo(xPosition + i, yPosition);
                terminal.write(Ascii.thunder().get(i));
            }
            terminal.moveTo(xPosition + 5,yPosition-4);
            terminal.write(weatherInfo);

        } else if (id >= 300 && id <= 531){
            //print rain ascii
            for (int i = 0; i < Ascii.rain().size(); i++) {
                terminal.moveTo(xPosition + i, yPosition);
                terminal.write(Ascii.rain().get(i));
            }
            terminal.moveTo(xPosition + 5,yPosition-4);
            terminal.write(weatherInfo);
        } else if (id >= 600 && id <= 622){
            //print snow ascii
            for (int i = 0; i < Ascii.snow().size(); i++) {
                terminal.moveTo(xPosition + i,yPosition);
                terminal.write(Ascii.snow().get(i));
            }
            terminal.moveTo(xPosition + 5,yPosition-4);
            terminal.write(weatherInfo);
        } else if (id >= 701 && id <= 781){
            //print atmosphere
            for (int i = 0; i < Ascii.extreme().size(); i++) {
                terminal.moveTo(xPosition + i, yPosition);
                terminal.write(Ascii.extreme().get(i));
            }
            terminal.moveTo(xPosition + 5,yPosition-4);
            terminal.write(weatherInfo);
        } else if (id == 800){
            //print sun
            for (int i = 0; i < Ascii.sun().size(); i++) {
                terminal.moveTo(xPosition + i, yPosition);
                terminal.write(Ascii.sun().get(i));
            }
            terminal.moveTo(xPosition + 5,yPosition-4);
            terminal.write(weatherInfo);
        } else if (id > 800 && id <= 804){  //cloud
            //print cloud ascii
            for (int i = 0; i < Ascii.cloud().size(); i++) {
                terminal.moveTo(xPosition + i, yPosition);
                terminal.write(Ascii.cloud().get(i));
            }
            terminal.moveTo(xPosition + 5,yPosition-4);
            terminal.write(weatherInfo);
        } else if (id >= 900 && id <= 906){
            //print extreme face ascii
            for (int i = 0; i < Ascii.extreme().size(); i++) {
                terminal.moveTo(xPosition + i, yPosition);
                terminal.write(Ascii.extreme().get(i));
            }
            terminal.moveTo(xPosition + 5,yPosition-4);
            terminal.write(weatherInfo);
        }
    }
}
