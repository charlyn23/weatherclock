package nyc.c4q.ac21.weatherclock;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by Sufei Zhao on 4/7/15.
 * Sets user input as alarm
 * Alarm will ring at the set time, as the digital clock changes color
 */

public class Alarm {
    final static AnsiTerminal terminal = new AnsiTerminal();
    final static int numRows = TerminalSize.getNumLines();

    public static void alarm() {
        URL url = Clock.class.getResource("/audio/clock-cuckoo2.wav");
        AudioClip clip = Applet.newAudioClip(url);
        clip.play();
    }

    public static AnsiTerminal.Color colorChange() {
        Random random = new Random();
        int x = random.nextInt(5);

        if (x == 0)
            return AnsiTerminal.Color.GREEN;
        else if (x == 1)
            return AnsiTerminal.Color.RED;
        else if (x == 2)
            return AnsiTerminal.Color.YELLOW;
        else if (x == 3)
            return AnsiTerminal.Color.BLUE;
        else
            return AnsiTerminal.Color.MAGENTA;
    }

    public static void setAlarm(Calendar cal, String alarm, int hour, int min, int sec){
        String[] time = new String[2];
        String ap;
        boolean am = true;

        if (!alarm.contains(":") || alarm.length() < 6)
            alarm = null;
        else {
            time = alarm.split(":");
            ap = time[1].substring(2);
            time[1] = time[1].substring(0, 2);
            if (Integer.valueOf(time[0]) > 12 ||
                    Integer.valueOf(time[1]) > 59)
                alarm = null;
            else if (time[0].length() > 2 || time[1].length() > 2)
                alarm = null;
            if (ap.equalsIgnoreCase("am"))
                am = true;
            else if (ap.equalsIgnoreCase("pm"))
                am = false;
        }

        if (alarm != null) {
            boolean morning = am && (cal.get(Calendar.HOUR_OF_DAY) <= 12);
            boolean evening = !am && (cal.get(Calendar.HOUR_OF_DAY) >= 12);

            terminal.moveTo(numRows, 0);
            terminal.write("\u23F0" + "  " + alarm);

            if ((morning || evening) &&
                    hour == Integer.valueOf(time[0]) &&
                    min == Integer.valueOf(time[1]) &&
                    sec <= 5) {
                Alarm.alarm();
                terminal.setTextColor(Alarm.colorChange());
            } else
                terminal.clear();
        }
    }
}
