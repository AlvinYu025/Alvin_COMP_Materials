package plantPackage;


public class RedBean {
    private int height;
    private String name;
    private static int count = 1;
    public static final int MAX_HEIGHT = 5;
    public RedBean() {
        height = 0;
        name = "RedBean" + count;
        count++;
    }
    public void grow() {
        if (height < MAX_HEIGHT)
            height++;
    }    
    public String toString() {
        return name + ": " + height;
    }
    public int getHeight() {
        return height;
    }

}
