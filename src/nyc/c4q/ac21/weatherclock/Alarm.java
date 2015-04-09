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

    public static void alarm() {
        URL url = Clock.class.getResource("/audio/happy.wav");
        AudioClip clip = Applet.newAudioClip(url);
        int count = 0;
        clip.play();
    }

    public static AnsiTerminal.Color colorChange() {
        Random random = new Random();
        int x = random.nextInt(5);

        if (x == 0)
            return AnsiTerminal.Color.RED;
        else if (x == 1)
            return AnsiTerminal.Color.GREEN;
        else if (x == 2)
            return AnsiTerminal.Color.YELLOW;
        else if (x == 3)
            return AnsiTerminal.Color.BLUE;
        else
            return AnsiTerminal.Color.MAGENTA;
    }

    public static boolean isTime(Calendar cal, boolean am, String[] time, int hour, int min, int sec){
        boolean morning = am && (cal.get(Calendar.HOUR_OF_DAY) <= 12);
        boolean evening = !am && (cal.get(Calendar.HOUR_OF_DAY) >= 12);

        if ((morning || evening) &&
                hour == Integer.valueOf(time[0]) &&
                min == Integer.valueOf(time[1]) &&
                sec <= 5) {
            return true;
        } else
            return false;
    }
}
