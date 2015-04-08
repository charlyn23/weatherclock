package nyc.c4q.ac21.weatherclock;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.lang.Character;

public class Main {

    /**
     * SAMPLE CODE: Returns sunset time for the current day.
     */
    //SUNRISE
    public static Calendar getSunrise()
    {
        URL url = HTTP.stringToURL("http://api.openweathermap.org/data/2.5/weather?q=New%20York,NY");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);

        JSONObject sys = (JSONObject) obj.get("sys");
        if(sys == null) return null;
        Long sunriseTimestamp = (Long) sys.get("sunrise");
        if(sunriseTimestamp == null) return null;
        else

            return DateTime.fromTimestamp(sunriseTimestamp);

    }
    public static Calendar getSunset() {
        URL url = HTTP.stringToURL("http://api.openweathermap.org/data/2.5/weather?q=New%20York,NY");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);

        JSONObject sys = (JSONObject) obj.get("sys");
        if(sys == null) return null;
        Long sunsetTimestamp = (Long) sys.get("sunset");
        if(sunsetTimestamp == null) return null;
        else

            return DateTime.fromTimestamp(sunsetTimestamp);

    }

    /**
     * SAMPLE CODE: Displays a very primitive clock.
     */
    public static void main(String[] args) {
        // Find out the size of the terminal currently.
        final int numCols = TerminalSize.getNumColumns();
        final int numRows = TerminalSize.getNumLines();

        // Create the terminal.
        final AnsiTerminal terminal = new AnsiTerminal();

        // When the program shuts down, reset the terminal to its original state.
        // This code makes sure the terminal is reset even if you kill your
        // program by pressing Control-C.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                terminal.showCursor();
                terminal.reset();
                terminal.scroll(1);
                terminal.moveTo(numRows, 0);
            }
        });

        // Clear the screen to black.
        terminal.setBackgroundColor(AnsiTerminal.Color.BLACK);
        terminal.clear();
        // Don't show the cursor.
        terminal.hideCursor();

        // Get sunset time for the current day.
        Calendar sunset = getSunset();
        Calendar sunrise = getSunrise();
        //Get wind speed and direction
        Double windSpeed = WindSpeed.getWindSpeed();
        String windDirection = WindDirection.getWindDirection();

        int xPosition = 1 + numCols / 2 - 5;
        while (true) {
            // Get the current date and time.
            Calendar cal = Calendar.getInstance();

            // Write the time, including seconds, in white.
            String time = DateTime.formatTime(cal, true);
            if (cal.get(Calendar.HOUR_OF_DAY) >= 12)
                time += " PM";
            else
                time += " AM";
            terminal.setTextColor(AnsiTerminal.Color.WHITE);
            terminal.moveTo(3, xPosition);
            terminal.write(time);

            // Write the date in gray.
            String date = DateTime.formatDate(cal);
            terminal.setTextColor(AnsiTerminal.Color.WHITE, false);
            terminal.moveTo(5, xPosition);
            terminal.write(date);

            // Write the day of the week in green on a blue background.
            String dayOfWeek = DateTime.getDayOfWeekNames().get(cal.get(Calendar.DAY_OF_WEEK));
            terminal.setTextColor(AnsiTerminal.Color.GREEN);
            terminal.setBackgroundColor(AnsiTerminal.Color.BLUE);
            terminal.moveTo(7, xPosition);
            terminal.write("  " + dayOfWeek + "  ");

            // Set the background color back to black.
            terminal.setBackgroundColor(AnsiTerminal.Color.BLACK);

            //Write sunrise time
            String sunriseEmoji = new String(new int[] { 0x1F303 }, 0, 1);
            String sunriseTime = DateTime.formatTime(sunrise, false);
            terminal.setTextColor(AnsiTerminal.Color.YELLOW, false);
            terminal.moveTo(9, xPosition - 2);
            terminal.write(sunriseEmoji + " sunrise at " + sunriseTime);

            // Write sunset time in dark yellow.
            String sunsetTime = DateTime.formatTime(sunset, false);
            terminal.setTextColor(AnsiTerminal.Color.YELLOW, false);
            terminal.moveTo(11, xPosition - 2);
            terminal.write("U+1F304" + "sunset at " + sunsetTime);

            //Write wind direction
            terminal.setTextColor(AnsiTerminal.Color.BLUE, false);
            terminal.moveTo(13, xPosition - 2);
            terminal.write("Wind direction : " + windDirection);

            //Write wind speed
            terminal.setTextColor(AnsiTerminal.Color.BLUE, false);
            terminal.moveTo(15, xPosition -2);
            terminal.write("Wind speed: " + windSpeed);

            //Write DST
            terminal.setTextColor(AnsiTerminal.Color.GREEN);
            terminal.moveTo(17, xPosition -2);
            terminal.write("DST :" + DST.isDST(DateTime.parseDate(date)));
            // Pause for one second, and do it again.
            //DateTime.pause(1.0);

        }
    }
}
