package analyzer;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static String folderName;
    public static String patternFile;
    public static List<String[]> listOfPatterns;
    public static void main(String[] args) throws InterruptedException {

        //folderName = args[0];
        //patternFile = args[1];
        //for testing only
        folderName = "File Type Analyzer/task/src/analyzer/res";
        patternFile = "File Type Analyzer/task/src/analyzer/res/patterns.db";

        List<File> list = FileProcessor.processFolder(new File(folderName));
        listOfPatterns = PatternProcessor.processPatternFile(patternFile);
        Collections.reverse(listOfPatterns);

        for(int i = 0; i < list.size(); i++){
            Worker worker = new Worker();
            worker.setFile(list.get(i));
            worker.start();
        }
        Thread.sleep(1000);
    }


}
