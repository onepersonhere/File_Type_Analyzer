package analyzer;

import java.io.File;
import java.util.List;

public class Main {
    public static String folderName;
    public static String pattern;
    public static String resulting_type;
    public static void main(String[] args) throws InterruptedException {

        folderName = args[0];
        pattern = args[1];
        resulting_type = args[2];
        //for testing only
        /*
        folderName = "C:\\Users\\wh\\IdeaProjects\\File Type Analyzer\\File Type Analyzer\\task\\src\\analyzer\\res";
        pattern = "%PDF-";
        resulting_type = "PDF document";*/


        List<File> list = FileProcessor.processFolder(new File(folderName));

        for(int i = 0; i < list.size(); i++){
            Worker worker = new Worker();
            worker.setFile(list.get(i));
            worker.start();
        }
        Thread.sleep(1000);
    }


}
