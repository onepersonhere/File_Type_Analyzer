package analyzer;

import java.io.File;

import static analyzer.FileProcessor.processFile;
import static analyzer.KMP.kmpTypeComparator;

public class Worker extends Thread{
    private String filepath;
    private String filename;
    @Override
    public void run() {
        kmpTypeComparator(Main.pattern, Main.resulting_type, processFile(filepath), filename);
    }

    public void setFile(File file) {
        filepath = file.getPath();
        filename = file.getName();
    }
}
