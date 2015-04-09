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
    
    public static ArrayList<String> sun(){
        ArrayList<String> sunshine = new ArrayList<String>();
        sunshine.add("    \\ | /   ");
        sunshine.add("  '-.~~~.-' ");
        sunshine.add(" -==;   ;==-");
        sunshine.add("  .-'~~~'-. ");
        sunshine.add("    / | \\   ");

        return sunshine;
    }

    public static ArrayList<String> cloud(){
        ArrayList<String> cloud = new ArrayList<String>();
        cloud.add("     __ __     ");
        cloud.add("   .(     )).  ");
        cloud.add("  (  -- (-- )) ");
        cloud.add(" (__________)  ");

        return cloud;

    }

    public static ArrayList<String> rain(){
        ArrayList<String> rain = new ArrayList<String>();
        rain.add("  ______    ");
        rain.add(" (  )    )  ");
        rain.add("(__;__ ;__))");
        rain.add("  '  '  '  '");
        rain.add("    '  '  ' ");

        return rain;
    }

    public static ArrayList<String> snow(){
        ArrayList<String> snow = new ArrayList<String>();
        snow.add("  ______    ");
        snow.add(" ( )    )   ");
        snow.add("(__*__*__)) ");
        snow.add(" *  *  *  * ");
        snow.add("   *  *  *  ");

        return snow;
    }

    public static ArrayList<String> extreme(){
        ArrayList<String> face = new ArrayList<String>();
        face.add("     88888888       ");
        face.add(" 88   |]    |]    88  ");
        face.add("88        __       88  ");
        face.add(" 88     (___)    88  ");
        face.add("     88888888        ");

        return face;
    }

    public static ArrayList<String> thunder(){
        ArrayList<String> lightning = new ArrayList<String>();
        lightning.add("      ____  ");
        lightning.add("     /   /  ");
        lightning.add("  __/  ./   ");
        lightning.add(" /  ./      ");
        lightning.add("/,/         ");

        return lightning;
    }
}
