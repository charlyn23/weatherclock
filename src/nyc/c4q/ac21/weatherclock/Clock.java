package nyc.c4q.ac21.weatherclock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by Sufei Zhao on 4/4/15.
 * Contains digital clock numbers stored into ArrayLists to be used in main.java
 * Methods write to AnsiTerminal
 */

public class Clock {
    final static AnsiTerminal terminal = new AnsiTerminal();
    final static int numCols = TerminalSize.getNumColumns();
    final static int yClock = numCols/2;
    final static int yPosition = numCols / 2 - 11;


    public static ArrayList<String> getClock(int hour){
        HashMap<Integer, ArrayList<String>> num = new HashMap<Integer, ArrayList<String>>();
        ArrayList<ArrayList<String>> all = new ArrayList<ArrayList<String>>();
        ArrayList<String> zero = new ArrayList<String>();
        ArrayList<String> one = new ArrayList<String>();
        ArrayList<String> two = new ArrayList<String>();
        ArrayList<String> three = new ArrayList<String>();
        ArrayList<String> four = new ArrayList<String>();
        ArrayList<String> five = new ArrayList<String>();
        ArrayList<String> six = new ArrayList<String>();
        ArrayList<String> seven = new ArrayList<String>();
        ArrayList<String> eight = new ArrayList<String>();
        ArrayList<String> nine = new ArrayList<String>();

        String holiday;
        zero.add(" ██████╗ ");
        zero.add("██╔═████╗");
        zero.add("██║██╔██║");
        zero.add("████╔╝██║");
        zero.add("╚██████╔╝");
        zero.add(" ╚═════╝ ");
        one.add("    ██╗  ");
        one.add("   ███║  ");
        one.add("   ╚██║  ");
        one.add("    ██║  ");
        one.add("    ██║  ");
        one.add("    ╚═╝  ");
        two.add("██████╗  ");
        two.add("╚════██╗ ");
        two.add(" █████╔╝ ");
        two.add("██╔═══╝  ");
        two.add("███████╗ ");
        two.add("╚══════╝ ");
        three.add("██████╗  ");
        three.add("╚════██╗ ");
        three.add(" █████╔╝ ");
        three.add(" ╚═══██╗ ");
        three.add("██████╔╝ ");
        three.add("╚═════╝  ");
        four.add("██╗  ██╗ ");
        four.add("██║  ██║ ");
        four.add("███████║ ");
        four.add("╚════██║ ");
        four.add("     ██║ ");
        four.add("     ╚═╝ ");
        five.add("███████╗ ");
        five.add("██╔════╝ ");
        five.add("███████╗ ");
        five.add("╚════██║ ");
        five.add("███████║ ");
        five.add("╚══════╝ ");
        six.add(" ██████╗ ");
        six.add("██╔════╝ ");
        six.add("███████╗ ");
        six.add("██╔═══██╗");
        six.add("╚██████╔╝");
        six.add(" ╚═════╝ ");
        seven.add("███████╗ ");
        seven.add("╚════██║ ");
        seven.add("   ██╔╝  ");
        seven.add("  ██╔╝   ");
        seven.add("  ██║    ");
        seven.add("  ╚═╝    ");
        eight.add(" █████╗  ");
        eight.add("██╔══██╗ ");
        eight.add("╚█████╔╝ ");
        eight.add("██╔══██╗ ");
        eight.add("╚█████╔╝ ");
        eight.add(" ╚════╝  ");
        nine.add(" █████╗  ");
        nine.add("██╔══██╗ ");
        nine.add("╚██████║ ");
        nine.add(" ╚═══██║ ");
        nine.add(" █████╔╝ ");
        nine.add(" ╚════╝  ");
        all.add(zero);
        all.add(one);
        all.add(two);
        all.add(three);
        all.add(four);
        all.add(five);
        all.add(six);
        all.add(seven);
        all.add(eight);
        all.add(nine);

        for (int i=0; i<10; i++) {
            num.put(i, all.get(i));
        }

        return num.get(hour);
    }

    public static ArrayList<String> colon() {
        ArrayList<String> colon = new ArrayList<String>();
        colon.add("██╗");
        colon.add("╚═╝");
        colon.add("██╗");
        colon.add("╚═╝");

        return colon;
    }

    public static void printHour(int hour, int hour1, int hour2) {
        if (hour < 10) {
            for (int i = 0; i < Clock.getClock(hour).size(); i++) {
                terminal.moveTo(i+2, yClock - 33);
                terminal.write("         ");
            }
            for (int i = 0; i < Clock.getClock(hour).size(); i++) {
                terminal.moveTo(i+2, yClock - 24);
                terminal.write(Clock.getClock(hour).get(i));
            }
        } else {
            for (int i = 0; i < Clock.getClock(hour1).size(); i++) {
                terminal.moveTo(i+2, yClock - 33);
                terminal.write(Clock.getClock(hour1).get(i));
            }
            for (int i = 0; i < Clock.getClock(hour2).size(); i++) {
                terminal.moveTo(i+2, yClock - 24);
                terminal.write(Clock.getClock(hour2).get(i));
            }
        }
    }

    public static void printMin(int min, int min1, int min2) {
        if (min < 10) {
            for (int i = 0; i < Clock.getClock(0).size(); i++) {
                terminal.moveTo(i+2, yClock - 9);
                terminal.write(Clock.getClock(0).get(i));
            }
            for (int i = 0; i < Clock.getClock(min).size(); i++) {
                terminal.moveTo(i+2, yClock);
                terminal.write(Clock.getClock(min).get(i));
            }
        } else {
            for (int i = 0; i < Clock.getClock(min1).size(); i++) {
                terminal.moveTo(i+2, yClock - 9);
                terminal.write(Clock.getClock(min1).get(i));
            }
            for (int i = 0; i < Clock.getClock(min2).size(); i++) {
                terminal.moveTo(i+2, yClock);
                terminal.write(Clock.getClock(min2).get(i));
            }
        }
    }

    public static void printSec(int sec, int sec1, int sec2) {
        if (sec < 10) {
            for (int i = 0; i < Clock.getClock(0).size(); i++) {
                terminal.moveTo(i+2, yClock + 15);
                terminal.write(Clock.getClock(0).get(i));
            }
            for (int i = 0; i < Clock.getClock(sec).size(); i++) {
                terminal.moveTo(i+2, yClock + 24);
                terminal.write(Clock.getClock(sec).get(i));
            }
        } else {
            for (int i = 0; i < Clock.getClock(sec1).size(); i++) {
                terminal.moveTo(i+2, yClock + 15);
                terminal.write(Clock.getClock(sec1).get(i));
            }
            for (int i = 0; i < Clock.getClock(sec2).size(); i++) {
                terminal.moveTo(i+2, yClock + 24);
                terminal.write(Clock.getClock(sec2).get(i));
            }
        }
    }

    public static void printColon(int y) {
        for (int i = 0; i < Clock.colon().size(); i++) {
            terminal.moveTo(i+3, yClock + y);
            terminal.write(Clock.colon().get(i));
        }
    }

    public static void printDate(Calendar cal) {
        String dayOfWeek = DateTime.getDayOfWeekNames().get(cal.get(Calendar.DAY_OF_WEEK));
        String month = DateTime.getMonthNames().get(cal.get(Calendar.MONTH));
        int date = cal.get(Calendar.DATE);
        int year = cal.get(Calendar.YEAR);
        terminal.setTextColor(AnsiTerminal.Color.BLUE);
        terminal.setBackgroundColor(AnsiTerminal.Color.WHITE, false);
        terminal.moveTo(9, yPosition);
        terminal.write(" " + dayOfWeek + ", " + month + " " + date + " " + year + " ");
    }
}
