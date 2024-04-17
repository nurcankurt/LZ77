public class Tuple {
    private final int offset;
    private final int length;
    private final char character;
    
    public Tuple(int offset, int length, char character) {
        this.offset = offset;
        this.length = length;
        this.character = character;
    }
    
    public int getOffset() {
        return offset;
    }
    
    public int getLength() {
        return length;
    }
    
    public char getCharacter() {
        return character;
    }
    
    @Override
    public String toString() {
        return "(" + offset + ", " + length + ", '" + character + "')";
    }
}