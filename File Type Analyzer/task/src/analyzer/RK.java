package analyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RK {
    /*
    In this implementation, we use a method charToLong to associate each symbol with some number.
    For example, for upper-case letters, it returns the sequence number of a letter in the alphabet,
    for lower-case letters it returns the sequence number plus 32.
    */
    public static long charToLong(char ch) {
        return (long)(ch - 'A' + 1);
    }

    public static boolean RabinKarp(List<String[]> list, String text, String filename) {
        long[] patternHash = new long[list.size()];
        for(int i = 0; i < list.size(); i++){
            patternHash[i] = getHash(list.get(i)[1]);
        }
        for(int j = 0; j < list.size(); j++){
            String pattern = list.get(j)[1];
            for(int i = 0; i < text.length() - pattern.length() + 1; i++){
                String subStr = text.substring(i, i + pattern.length());
                long subHash = getHash(subStr);
                if(subHash == patternHash[j]){
                    //if hash is same, compare chars
                    if(pattern.equals(subStr)){
                        String type = list.get(j)[2];
                        System.out.println(filename + ": " + type);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static long getHash(String string){
        int a = 53; //prime number
        long m = 1_000_000_000 + 9; //mod
        long patternHash = 0;

        //create a hash for each char in the string
        for (int i = 0; i < string.length(); i++) {
            patternHash += charToLong(string.charAt(i)) * Math.pow(a, i);
        }
        patternHash %= m; //then mod it
        return patternHash;
    }
}
