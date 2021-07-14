package analyzer;

public class KMP {

    public static boolean kmpTypeComparator(String P, String type, String outputText, String filename){
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
                System.out.println(filename + ": " + type);
                return true;
            }
        }
        System.out.println(filename + ": Unknown file type");
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
