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
}
