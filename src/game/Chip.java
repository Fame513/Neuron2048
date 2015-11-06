package game;


public class Chip {
    private int value;

    public Chip(int value) throws Exception{
        if ((value & value-1) != 0)
            throw new Exception("Chip value must be power of two");
        this.value = value;
    }

    private int log2(int a){
        int result = 0;
        while ((a >>>= 1) != 0) result++;
        return result;
    }

    public int getValue() {
        return value;
    }

    public int getLogValue(){
        return log2(value);
    }

    public void up(){
        value <<= 1;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    public static void main(String[] args) {
        try {
            Chip c = new Chip(256);
            System.out.println(c.log2(8));
        } catch (Exception e){
            System.err.println(e);
        }
    }
}
