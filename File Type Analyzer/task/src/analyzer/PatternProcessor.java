package analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PatternProcessor {
    public static List<String[]> processPatternFile(String filename){
        List<String[]> list = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) { //line by line
                String line = myReader.nextLine();
                int firstColon = line.indexOf(";");
                int lastColon = line.lastIndexOf(";");
                String number = line.substring(0,firstColon);
                String pattern = line.substring(firstColon+2,lastColon-1);
                String description = line.substring(lastColon+2,line.length()-1);
                list.add(new String[]{number,pattern,description});
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }
}
