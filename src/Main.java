import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String str = "abcdefab";
		char[] chars = str.toCharArray();
        lz77(chars);
       
    }
    static void lz77(char[] chars){
        List<Tuple> tupleList = new ArrayList<Tuple>();

        
        int offset = 0;
        int length = 0;

        int searchBufferIndex = 0;
        int seperatorIndex = 1;
        int lookAheadBufferIndex = 1;
        String match = null;

        while (searchBufferIndex > 0) {

            if (chars[searchBufferIndex] == chars[lookAheadBufferIndex]) {
                length++;
                match = match + chars[searchBufferIndex];
                while (searchBufferIndex < seperatorIndex) {
                    searchBufferIndex++;
                    lookAheadBufferIndex++;
                    if (chars[searchBufferIndex] == chars[lookAheadBufferIndex]) {
                        match = match + chars[searchBufferIndex];
                    }
                    else
                    {
                        Tuple tuple  =new Tuple(offset, length, match);
                        tupleList.add(tuple);
                        

                    }
                    
                }    
                
            }else
            {
                offset++;
                searchBufferIndex--;
  
            }
            
        }



    }

} 