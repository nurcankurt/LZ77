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
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str = "abcdefab";
        lz77(str);
    }

    static void lz77(String str) {
        List<Tuple> tupleList = new ArrayList<>();

        int searchBufferIndex = 0;
        int lookAheadBufferIndex = 0;
        int windowSize = 5; // Adjust window size as needed

        while (lookAheadBufferIndex < str.length()) {
            int offset = 0;
            int length = 0;
            String match = null;

            for (int i = Math.max(0, lookAheadBufferIndex - windowSize); i < lookAheadBufferIndex; i++) {
                int j = lookAheadBufferIndex;
                int currentLength = 0;
                while (j < str.length() && str.charAt(i) == str.charAt(j) && currentLength < j - lookAheadBufferIndex) {
                    currentLength++;
                    j++;
                }
                if (currentLength > length) {
                    length = currentLength;
                    offset = lookAheadBufferIndex - i;
                    match = str.substring(lookAheadBufferIndex, lookAheadBufferIndex + length);
                }
            }

            if (offset == 0) {
                tupleList.add(new Tuple(0, 0, str.charAt(lookAheadBufferIndex)));
                lookAheadBufferIndex++;
            } else {
                tupleList.add(new Tuple(offset, length, match.charAt(0)));
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
        char nextChar;

        public Tuple(int offset, int length, char nextChar) {
            this.offset = offset;
            this.length = length;
            this.nextChar = nextChar;
        }

        @Override
        public String toString() {
            return "(" + offset + ", " + length + ", " + nextChar + ")";
        }
    }
}
