import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str = "abcdefab";
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

            for (int i = searchBufferIndex; i >= 0; i--) {
                int j = lookAheadBufferIndex;
                while (j < chars.length && chars[i] == chars[j] && length < j - i) {
                    length++;
                    match = new String(chars, i, length);
                    j++;
                }
                if (length == j - i) {
                    offset = searchBufferIndex - i;
                }
            }

            if (offset == 0) {
                tupleList.add(new Tuple(0, 0, String.valueOf(chars[lookAheadBufferIndex])));
                searchBufferIndex++;
                lookAheadBufferIndex++;
            } else {
                tupleList.add(new Tuple(offset, length, match));
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
        String match;

        public Tuple(int offset, int length, String match) {
            this.offset = offset;
            this.length = length;
            this.match = match;
        }

        @Override
        public String toString() {
            return "(" + offset + ", " + length + ", " + match + ")";
        }
    }
}
