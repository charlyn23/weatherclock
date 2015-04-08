package nyc.c4q.ac21.weatherclock;

import java.util.ArrayList;
import java.util.Calendar;

public class CalendarPrinter
{

    /**
     * Prints a calendar for a month.
     *
     * Prints the calendar in the traditional format with one week per line.
     * Each week starts on Sunday and shows the days of month for days in
     * that week.  The current date is marked with an asterisk.
     *
     * For example, for 2015-04-03, prints this:
     *
     *     April 2015
     *                  1   2   3*  4
     *      5   6   7   8   9  10  11
     *     12  13  14  15  16  17  18
     *     19  20  21  22  23  24  25
     *     26  27  28  29  30
     *
     * @param date
     *   The date containing the month to print.
     */
    public static ArrayList<String> getCalendar(Calendar date) {
        ArrayList<String> print = new ArrayList<String>();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        // First, show the month name and year.
        print.add("        " + DateTime.getMonthNames().get(month) + " " + year);

        // Start the calendar on the first day of the month.
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        String week = "";

        int indent = cal.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        for (int i = 0; i < indent; ++i) {
            week += "    ";
        }

        while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) == month) {
            int d = cal.get(Calendar.DAY_OF_MONTH);
            if (d < 10) {
                week += ' ';
            }
            week += d;

            if (d == day) {
                week += '*';
            } else {
                week += ' ';
            }

            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                    d == cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                print.add(week);
                week = "";
            } else {
                week += ' ';
            }

            cal = DateTime.getNextDay(cal);
        }

        return print;
    }

}
