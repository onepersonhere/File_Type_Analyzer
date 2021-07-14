package analyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    public static List<File> processFolder(final File folder) {
        List<File> fileList = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                processFolder(fileEntry);
            } else {
                fileList.add(fileEntry);
            }
        }
        return fileList;
    }

    public static String processFile(String filename){
        try(
                InputStream inputStream = new FileInputStream(filename);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
        ){
            long fileSize = new File(filename).length();
            byte[] allBytes = new byte[(int) fileSize];

            inputStream.read(allBytes);
            outputStream.write(allBytes);
            String output = outputStream.toString();
            return output;
        }catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }
}
