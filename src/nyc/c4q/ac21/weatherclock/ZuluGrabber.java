package nyc.c4q.ac21.weatherclock;
/**
* Created by charlynbuchanan on 4/9/15.
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class ZuluGrabber {
    public static ArrayList<String> getWordArray(File filename) throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<String>();
        Scanner reader = new Scanner(filename);
        
        while (reader.hasNextLine()) {
          words.add(reader.nextLine());
        }
        
        return words;
    }
}

