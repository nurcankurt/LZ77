import java.util.ArrayList;
import java.util.List;

public class Main {
    
    private static final int SEARCH_BUFFER_SIZE = 15;
    private static final int LOOKAHEAD_BUFFER_SIZE = 10;
    
    public static List<Tuple> compress(String input) {
        List<Tuple> tuples = new ArrayList<>();
        
        int currentIndex = 0;
        while (currentIndex < input.length()) {
            int matchLength = 0;
            int matchPosition = 0;
            
            for (int i = currentIndex - 1; i >= Math.max(0, currentIndex - SEARCH_BUFFER_SIZE); i--) {
                int len = longestMatch(input, i, currentIndex);
                
                if (len > matchLength) {
                    matchLength = len;
                    matchPosition = currentIndex - i;
                }
            }
            
            if (matchLength > 0) {
                tuples.add(new Tuple(matchPosition, matchLength, input.charAt(currentIndex + matchLength)));
                currentIndex += matchLength + 1;
            } else {
                tuples.add(new Tuple(0, 0, input.charAt(currentIndex)));
                currentIndex++;
            }
        }
        
        return tuples;
    }
    
    private static int longestMatch(String input, int start1, int start2) {
        int len = 0;
        
        while (start2 + len < input.length() && input.charAt(start1 + len) == input.charAt(start2 + len) && len < LOOKAHEAD_BUFFER_SIZE) {
            len++;
        }
        
        return len;
    }
    
    public static String decompress(List<Tuple> tuples) {
        StringBuilder result = new StringBuilder();
        
        for (Tuple tuple : tuples) {
            if (tuple.getOffset() == 0 && tuple.getLength() == 0) {
                result.append(tuple.getCharacter());
            } else {
                int startIndex = result.length() - tuple.getOffset();
                for (int i = 0; i < tuple.getLength(); i++) {
                    result.append(result.charAt(startIndex + i));
                }
                result.append(tuple.getCharacter());
            }
        }
        
        return result.toString();
    }
    

    public static void main(String[] args) {
        String input = "CABRACADABRAR";
        List<Tuple> compressedTuples = compress(input);
        System.out.println("Compressed Tuples: " + compressedTuples);
        
        String decompressedString = decompress(compressedTuples);
        System.out.println("Decompressed String: " + decompressedString);
    }
}
