package nyc.c4q.ac21.weatherclock;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by team3 on 4/7/15.
 */
public class Main {

    public static void main(String[] args) {
        final int numCols = TerminalSize.getNumColumns();
        final int numRows = TerminalSize.getNumLines();
        final AnsiTerminal terminal = new AnsiTerminal();
        DecimalFormat df = new DecimalFormat("#");

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
        
         // User input set alarm
        Scanner input = new Scanner(System.in);
        System.out.println("Set your alarm time: ");
        String ap, alarm = input.next();
        String[] time = new String[2];
        boolean am = true;

        if (alarm.matches("[0-2?]\\d:[0-5]\\d[a|A|p|P][m|M]")) {
            time = alarm.split(":");
            ap = time[1].substring(2);
            time[1] = time[1].substring(0, 2);

            if (ap.equalsIgnoreCase("am"))
                am = true;
            else if (ap.equalsIgnoreCase("pm"))
                am = false;
        } else {
            alarm = null;
        }

        // Get sunset time for the current day.
        Calendar sunset = Sunset.getSunset();
        Calendar sunrise = Sunrise.getSunrise();
        //Get wind speed and direction
        Double windSpeed = WindSpeed.getWindSpeed();
        String windDirection = WindDirection.getWindDirection();
        //Get temp and pressure and humidity
        double temp = TPH.getTemp().intValue();
        double presh = TPH.getPressure().intValue();
        double humidity = TPH.getHumid();
        //Get headlines
        ArrayList<String> newsfeed =  NewsGrabber.getHeadlines();
        String news = "";
        for (int i = 0; i < newsfeed.size(); i++) {
            news += ".   " + newsfeed.get(i);
        }
        //Get Current Weather ID
        long currentWeather = TPH.getID();

        // Print digital clock
        int yPosition = 1 + numCols / 2 - 5;
        int yClock = numCols/2;
        int xPosition = numCols;
        int newsOffset = 0;
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
//            if (Alarm.isTime(cal, am, time, hour, min, sec)) {
//                Alarm.alarm();
//                terminal.setTextColor(Alarm.colorChange());
//            } else if (alarm != null) {
//                terminal.moveTo(numRows, 0);
//                terminal.write("\u23F0" + "  " + alarm);
//            }

            // print Clock
            terminal.setTextColor(AnsiTerminal.Color.CYAN);
            Clock.printHour(hour, hour1, hour2);
            Clock.printColon(- 14);
            Clock.printMin(min, min1, min2);
            Clock.printColon(10);
            Clock.printSec(sec, sec1, sec2);

            // print AM/PM
            terminal.moveTo(11, yClock + 34);
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
                terminal.moveTo(14 + i, 4);
                terminal.write(Ascii.printCity().get(i));
            }

            //Write sunrise time
            String sunriseEmoji = new String(new int[] { 0x1F305 }, 0, 1);
            String sunriseTime = DateTime.formatTime(sunrise, false);
            terminal.setTextColor(AnsiTerminal.Color.YELLOW, false);
            terminal.moveTo(17, 20);
            terminal.write(sunriseEmoji + "  sunrise at " + sunriseTime);

            // Write sunset time in dark yellow.
            String sunsetEmoji = new String (new int[] {0x1F307}, 0, 1);
            String sunsetTime = DateTime.formatTime(sunset, false);
            terminal.setTextColor(AnsiTerminal.Color.YELLOW, false);
            terminal.moveTo(18, 20);
            terminal.write(sunsetEmoji + "  sunset at " + sunsetTime);


            // Temperature, Pressure, Humidity
            terminal.setTextColor(AnsiTerminal.Color.RED, false);
            terminal.moveTo(19, 20);
            terminal.write("Temperature : " + df.format(temp) + " F");
            terminal.moveTo(20, 20);
            terminal.write("Pressure : " + df.format(presh) + " inHg");
            terminal.moveTo(21, 22);
            terminal.write("Humidity : " + humidity + "%");

            //Write wind direction
            terminal.setTextColor(AnsiTerminal.Color.BLUE, false);
            terminal.moveTo(22, 18);
            terminal.write("Wind direction : " + windDirection);

            //Write wind speed
            terminal.setTextColor(AnsiTerminal.Color.BLUE, false);
            terminal.moveTo(23, 22);
            terminal.write("Wind speed: " + windSpeed);

            //Write DST
            String date = DateTime.formatDate(cal);
            terminal.setTextColor(AnsiTerminal.Color.GREEN);
            terminal.moveTo(24, 25);
            terminal.write("DST :" + DST.isDST(DateTime.parseDate(date)));

            //Write newsfeed
            terminal.moveTo(3, 0);
            terminal.setTextColor(AnsiTerminal.Color.WHITE);
            newsOffset+=4;
            terminal.write(news.substring(newsOffset++, numCols+newsOffset - 1));
            if (numCols+newsOffset+5 >= news.length()) {
                newsOffset = 0;
            }

            // print calendar
            int xCalendar = numRows - 6;
            terminal.setTextColor(AnsiTerminal.Color.CYAN);
            for (int i = 0; i < CalendarPrinter.getCalendar(cal).size(); i++) {
                terminal.moveTo(xCalendar + i, 16);
                terminal.write(CalendarPrinter.getCalendar(cal).get(i));
            }

            //Print weather condition ASCII
            terminal.setTextColor(AnsiTerminal.Color.WHITE);
            Weather.printWeather(currentWeather);

            // Pause for one second, and do it again.
            terminal.reset();
            DateTime.pause(1.0);
        }
    }
}
