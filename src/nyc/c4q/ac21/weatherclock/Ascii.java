package nyc.c4q.ac21.weatherclock;

import java.util.ArrayList;

/**
 * Created by sufeizhao on 4/7/15.
 */
public class Ascii {
    public static ArrayList<String> printCity() {
        ArrayList<String> ny = new ArrayList<String>();
        ny.add("      ___              __   __                     ");
        ny.add("|\\ | |__  |  |    \\ / /  \\ |__) |__/      |\\ | \\ / ");
        ny.add("| \\| |___ |/\\|     |  \\__/ |  \\ |  \\ ,    | \\|  |  ");

        return ny;
    }

    public static ArrayList<String> day1() {
        ArrayList<String> sun = new ArrayList<String>();
        sun.add("        \\ | /");
        sun.add("       -.~-~.-");
        sun.add("     -==;   ;==-");
        sun.add("       -'~-~'- .') ");
        sun.add("        / | \\ (_  )");

        return sun;
    }

    public static ArrayList<String> day2() {
        ArrayList<String> sun = new ArrayList<String>();
        sun.add("        \\ | /");
        sun.add("      '-.~-~.-'");
        sun.add("     -==;   ;==-");
        sun.add("      .-'~-~'-. .') ");
        sun.add("        / | \\    (_  )");

        return sun;
    }
}
