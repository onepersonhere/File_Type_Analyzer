package analyzer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        String comparatorType = args[0];
        String filename = args[1];
        String pattern = args[2];
        String resulting_type = args[3];

        //for testing only
        /*
        String comparatorType = "--KMP";
        String filename = "C:\\Users\\wh\\IdeaProjects\\File Type Analyzer\\File Type Analyzer\\task\\src\\analyzer\\res\\idk.pdf";
        String pattern = "%PDF-";
        String resulting_type = "PDF document";
        */
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
        System.out.println("It took " + elapseTime * Math.pow(10,-9) + " seconds");
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
        //build the table
        int[] arr = longestPrefixSuffixArray(P);
        int i = 0; //outputText index
        int j = 0; //P index

        while(i < outputText.length()){ //for the entire length of the text
            if(P.charAt(j) == outputText.charAt(i)){ //if char at j of P == char at i of text
                j++;
                i++; //both indexes increases
            }else{//if not completely matches and is mismatched at that index
                if(j != 0){//J is not at the start
                    j = arr[j-1]; //P index returns to previous J array number
                }else{//J is at the start
                    i++; //Text index increases
                }
            }
            if(j == P.length()){ //completely matches
                System.out.println(type);
                return true;
            }
        }
        System.out.println("Unknown file type");
        return false;
    }

    private static int[] longestPrefixSuffixArray(String P){
        int[] arr = new int[P.length()];
        int i = 1;
        arr[0] = 0;

        //calc loop from i = 1 to i = length - 1;
        for(;i < P.length(); i++){
            if(P.charAt(i) == P.charAt(arr[i-1])){ //compares the "last" char with the corresponding "first" char
                arr[i] = arr[i-1] + 1; //eg. ayeaye -> [0,0,0,1,2,3] -> last char adds to prev arr value
            }else{                     //                               while maintaining the comparison
                arr[i] = 0;
            }
        }
        return arr;
    }
}
