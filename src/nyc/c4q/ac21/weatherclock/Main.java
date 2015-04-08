package nyc.c4q.ac21.weatherclock;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URL;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by sufeizhao on 4/7/15.
 */
public class Main {

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
    public static void main(String[] args) {
        final int numCols = TerminalSize.getNumColumns();
        final int numRows = TerminalSize.getNumLines();
        final AnsiTerminal terminal = new AnsiTerminal();

        // Set Alarm
        Scanner input = new Scanner(System.in);
        System.out.println("Set your alarm time (ie 7:00am): ");
        String alarm = input.next(), ap;

        // When the program shuts down, reset the terminal to its original state.
        // This code makes sure the terminal is reset even if you kill your
        // program by pressing Control-C.
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                terminal.showCursor();
                terminal.reset();
                terminal.scroll(1);
                terminal.moveTo(numRows, 0);
            }
        });
        terminal.setBackgroundColor(AnsiTerminal.Color.BLACK);
        terminal.clear();
        terminal.hideCursor();

        // Print digital clock
        int yPosition = 1 + numCols / 2 - 5;
        int yClock = numCols/2;

        // Get sunset time for the current day.
        Calendar sunset = getSunset();
        Calendar sunrise = getSunrise();
        //Get wind speed and direction
        Double windSpeed = WindSpeed.getWindSpeed();
        String windDirection = WindDirection.getWindDirection();

        //int xPosition = 1 + numCols / 2 - 5;
        int xPosition = numCols;
        while (true) {
            // Get the current date and time.
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR);
            int hour1 = cal.get(Calendar.HOUR) / 10;
            int hour2 = cal.get(Calendar.HOUR) % 10;
            if (hour == 0) {
                hour = 12;
                hour1 = 1;
                hour2 = 2;
            }
            int min = cal.get(Calendar.MINUTE);
            int min1 = cal.get(Calendar.MINUTE) / 10;
            int min2 = cal.get(Calendar.MINUTE) % 10;
            int sec = cal.get(Calendar.SECOND);
            int sec1 = cal.get(Calendar.SECOND) / 10;
            int sec2 = cal.get(Calendar.SECOND) % 10;

            // Alarm goes off
            Alarm.setAlarm(cal, alarm, hour, min, sec);

            // print Clock
            Clock.printHour(hour, hour1, hour2);
            Clock.printColon(- 14);
            Clock.printMin(min, min1, min2);
            Clock.printColon(10);
            Clock.printSec(sec, sec1, sec2);

            // print AM/PM
            terminal.moveTo(7, yClock + 34);
            if (cal.get(Calendar.HOUR_OF_DAY) >= 12)
                terminal.write("PM");
            else
                terminal.write("AM");

            // Write the date
            Clock.printDate(cal);

            // print location
            terminal.setBackgroundColor(AnsiTerminal.Color.BLACK);
            terminal.setTextColor(AnsiTerminal.Color.MAGENTA);
            for (int i = 0; i < Ascii.printCity().size(); i++) {
                terminal.moveTo(10 + i, 0);
                terminal.write(Ascii.printCity().get(i));
            }

            // print sun
            terminal.setTextColor(AnsiTerminal.Color.YELLOW);
            for (int i = 0; i < Ascii.day1().size(); i++) {
                if (i%2==0) {
                    terminal.moveTo(2 + i, 0);
                    terminal.write(Ascii.day1().get(i));
                } else {
                    terminal.moveTo(2 + i, 0);
                    terminal.write(Ascii.day2().get(i));
                }
            }

            // print calendar
            int xCalendar = numRows - 5;
            terminal.setTextColor(AnsiTerminal.Color.CYAN);
            for (int i = 0; i < CalendarPrinter.getCalendar(cal).size(); i++) {
                terminal.moveTo(xCalendar + i, numCols-26);
                terminal.write(CalendarPrinter.getCalendar(cal).get(i));
            }
            DateTime.pause(1.0);

            //Write sunrise time
            String sunriseEmoji = new String(new int[] { 0x1F303 }, 0, 1);
            String sunriseTime = DateTime.formatTime(sunrise, false);
            terminal.setTextColor(AnsiTerminal.Color.YELLOW, false);
            terminal.moveTo(13, xPosition - 1);
            terminal.write(sunriseEmoji + " sunrise at " + sunriseTime);

            // Write sunset time in dark yellow.
            String sunsetTime = DateTime.formatTime(sunset, false);
            terminal.setTextColor(AnsiTerminal.Color.YELLOW, false);
            terminal.moveTo(15, xPosition - 1);
            terminal.write("U+1F304" + "sunset at " + sunsetTime);

            //Write wind direction
            terminal.setTextColor(AnsiTerminal.Color.BLUE, false);
            terminal.moveTo(17, xPosition - 1);
            terminal.write("Wind direction : " + windDirection);

            //Write wind speed
            terminal.setTextColor(AnsiTerminal.Color.BLUE, false);
            terminal.moveTo(19, xPosition - 1);
            terminal.write("Wind speed: " + windSpeed);

            //Write DST
            String date = DateTime.formatDate(cal);
            terminal.setTextColor(AnsiTerminal.Color.GREEN);
            terminal.moveTo(21, xPosition - 1);
            terminal.write("DST :" + DST.isDST(DateTime.parseDate(date)));
            // Pause for one second, and do it again.
        }
    }
}
