package nyc.c4q.ac21.weatherclock;
/**
* Created by charlynbuchanan on 4/9/15.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class ZuluGrabber
{
    public static void main(String[] args)
    {
        File zulu = new File(
                "/Users/charlynbuchanan/Desktop/accesscode/weatherclock/src/nyc/c4q/ac21/weatherclock/Zulu.txt");
        getALine(zulu);

    }



//    public static ArrayList<String> readLinesFromFile(String filename)
//    {
//        ArrayList<String> lines = new ArrayList<String>();
//        try
//        {
//            return (ArrayList<String>) Files
//                    .readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
//        }
//        catch(IOException exception)
//        {
//            exception.printStackTrace(System.err);
//            return null;
//        }
//    }

    public static ArrayList<String> getWordArray(String filename) {
        ArrayList<String> words = new ArrayList<String>();
        Scanner reader = new Scanner(file);
        
        while (reader.hasNextLine()) {
          words.add(reader.nextLine());
        }
        
        return words;
    }

    public static void getALine(File file)
    {

        try
        {
            Scanner reader = new Scanner(file);
            ArrayList<String> tempZuluArray = new ArrayList<String>();

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                tempZuluArray.add(line);
                System.out.println(tempZuluArray);
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
// Object randomItem = list.get(new Random().nextInt(list.size())
    }
    public String word() {
        Random randomGenerator = new Random();
    int index = randomGenerator.next.size();
    Item randomItem = .get(index);
    System.out.println("Managers choice this week" + randomItem.toString() + "our recommendation to you");
    return randomItem;
}




}
}
