package analyzer;

import java.io.File;
import java.util.List;

import static analyzer.FileProcessor.processFile;
import static analyzer.RK.RabinKarp;

public class Worker extends Thread{
    private String filepath;
    private String filename;
    @Override
    public void run() {
        processPatterns();
    }

    public void setFile(File file) {
        filepath = file.getPath();
        filename = file.getName();
    }

    private void processPatterns(){
        List<String[]> list = Main.listOfPatterns;
        boolean isNotFound = true;
        for(int i = 0; i < list.size(); i++){
            String[] strArr = list.get(i);
            if(RabinKarp(strArr[1], strArr[2], processFile(filepath), filename)){
                isNotFound = false;
                break;
            }
        }

        if(isNotFound){
            System.out.println(filename + ": Unknown file type");
        }
    }
}
