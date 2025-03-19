import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 *  
 *  NOT allow to add additional fields
 *  NOT allow to modify main method
 * 
 *  You can and you should add more methods, either public or private
 * 
 */
public class Canvas {
    /**
     * A list storing the coordinates read from the file
     */
    private List<Point> coordinates = new ArrayList<>();
    /**
     * The filename
     */
    private final String filename;
    /**
     * The height of the canvas
     */
    private int row;
    /**
     * The width of the canvas
     */
    private int col;


 
    public static void main(String[] args) {
        Canvas c1 = new Canvas("data.txt");
        c1.readData();
        c1.printPoints();
        System.out.printf("The size of the canvas is %d-by-%d\n", c1.getRow(), c1.getCol());
        c1.printCanvas();
        System.out.println("\n\n\n-------------------------------------\n\n\n");
        Canvas c2 = new Canvas("data2.txt");
        System.out.printf("The size of the canvas is %d-by%d\n", c2.getRow(), c2.getCol());
        c2.readData();
        c2.printPoints();
        c2.printCanvas();
    }
}