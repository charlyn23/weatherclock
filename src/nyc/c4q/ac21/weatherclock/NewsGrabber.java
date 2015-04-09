package nyc.c4q.ac21.weatherclock;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * Created by charlynbuchanan on 4/8/15.
 * This class grabs headlines from google news that the Main class will then print to terminal
 */
public class NewsGrabber
{

    public static ArrayList<String> getHeadlines()
    {
        //Stores headlines to array
        ArrayList<String> headlineArray = new ArrayList<String>();


        URL url = HTTP.stringToURL(
                "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&q=http%3A%2F%2Fnews.google.com%2Fnews%3Foutput%3Drss%26num%3D8");
        String doc = HTTP.get(url);
        JSONObject obj = (JSONObject) JSONValue.parse(doc);

        JSONObject blob = (JSONObject) obj.get("responseData");
        JSONObject feed = (JSONObject) blob.get("feed");
        JSONArray entries = (JSONArray) feed.get("entries");

        //entries is an array. iterate through each item for "title".
        //if it has "title', grab it and add it to headlineArray
        for (int i = 0; i < entries.size(); i++) {
            JSONObject currentEntry = (JSONObject)entries.get(i);

            if (currentEntry.containsKey("title")) {
                //String title;
                String headline = (String)currentEntry.get("title");
              headlineArray.add(headline);
            }
        }



        return headlineArray;
    }
//    public static void main (String [] args) {
//        ArrayList<String> newsfeed =  getHeadlines();
//
//        for (int i = 0; i < newsfeed.size(); i++){
//            System.out.println( i + ": " + newsfeed.get(i));
//        }
//    }
}
