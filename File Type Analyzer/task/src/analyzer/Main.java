package analyzer;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String comparatorType = args[0];
        String filename = args[1];//"C:\\Users\\wh\\IdeaProjects\\File Type Analyzer\\File Type Analyzer\\task\\src\\analyzer\\res\\idk.pdf";
        String pattern = args[2];//"%PDF-";
        String resulting_type = args[3];//"PDF document";

        ComparatorChooser(comparatorType,filename,pattern,resulting_type);
    }

    private static void ComparatorChooser(String comparatorType, String filename, String pattern, String resulting_type){
        long startTime = System.nanoTime();

        if(comparatorType.equals("--naive")){
            naiveTypeComparator(pattern, resulting_type, processFile(filename));
        }else if(comparatorType.equals("--KMP")){
            kmpTypeComparator(pattern, resulting_type, processFile(filename));
        }

        long elapseTime = System.nanoTime() - startTime;
        System.out.println(elapseTime);
    }

    private static String processFile(String filename){
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

    private static boolean naiveTypeComparator(String P, String type, String outputText){
        Pattern pattern = Pattern.compile(P);
        Matcher matcher = pattern.matcher(outputText);
        if(matcher.find()){
            if(matcher.group().equals(P)){
                System.out.println(type);
                return true;
            }
        }
        System.out.println("Unknown file type");
        return false;
    }

    private static boolean kmpTypeComparator(String P, String type, String outputText){
        return false;
    }
}
