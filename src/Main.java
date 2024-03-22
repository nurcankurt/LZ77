import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str = "abcbbcbaaaaaa";
		char[] chars = str.toCharArray();
        lz77(chars);
       
    }
    static void lz77(char[] chars) {
        List<Tuple> tupleList = new ArrayList<Tuple>();
    
        int searchBufferIndex = 0;
        int lookAheadBufferIndex = 1;
    
        while (lookAheadBufferIndex < chars.length) {
            int offset = 0;
            int length = 0;
            String match = null;
            char codeword = ' ';
    
            for (int i = searchBufferIndex; i >= 0; i--) {
                int j = lookAheadBufferIndex;
                while (j < chars.length && chars[i] == chars[j] && length < j - i) {
                    length++;
                    match = new String(chars, i, length);
                    offset = j - i;
                    j++;
                    i++;
                    codeword = chars[j];
                }
                if (length == j - i) {
                    offset = searchBufferIndex - i;
                }
            }
    
            if (offset == 0) {
                tupleList.add(new Tuple(0, 0, chars[lookAheadBufferIndex]));
                searchBufferIndex++;
                lookAheadBufferIndex++;
            } else {
                tupleList.add(new Tuple(offset, length, codeword));
                searchBufferIndex += length;
                lookAheadBufferIndex += length;
            }
        }
    
        // Print the tuples
        for (Tuple tuple : tupleList) {
            System.out.println(tuple);
        }
    }
    
    static class Tuple {
        int offset;
        int length;
        char nextSymbol;
    
        public Tuple(int offset, int length, char nextSymbol) {
            this.offset = offset;
            this.length = length;
            this.nextSymbol = nextSymbol;
        }
    
        @Override
        public String toString() {
            return "(" + offset + ", " + length + ", " + nextSymbol + ")";
        }
    }
} 
