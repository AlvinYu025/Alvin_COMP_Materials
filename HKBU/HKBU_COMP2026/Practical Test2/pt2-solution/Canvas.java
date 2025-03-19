import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The key of the question is to assign the value to the proper fields.
 * coordinates stores all points. 
 * filename stores the filename
 * row stores the row
 * col stores the col
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

    public int getRow() { return row; }
    public int getCol() { return col; }
    public Canvas(String filename) {
        this.filename = filename;
    }
    public void printPoints() {
        System.out.printf("There are %d points read.\n", coordinates.size());
        for (Point p : coordinates) {
            System.out.println(p);
        }
    }
    public void readData() {
        try (Scanner scanner = new Scanner(new File(filename))) {
            row = scanner.nextInt();
            col = scanner.nextInt();
            while (scanner.hasNextLine()) {
                int r = scanner.nextInt();
                int c = scanner.nextInt();
                char s = scanner.next().charAt(0);
                coordinates.add(new Point(r,c,s));
            }
        } catch (Exception e) {
            System.out.println("Error reading lines");
        }
    }
    public void printCanvas() {
        //prepare your canvas. Default as space and then replace the points in coordinates
        char[][] canvas = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                canvas[i][j] = ' ';                
            }
        }

        for (Point p : coordinates) {
            int r = p.getR();
            int c = p.getC();
            char s = p.getSymbol();
            canvas[r][c] = s;
        }

        //to print
        printLine();

        for (int i = 0; i < row; i++) {
            System.out.print("|");
            for (int j = 0; j < col; j++)
                System.out.print(canvas[i][j]);
            System.out.println("|");
        }
        printLine();
    }
    private void printLine() {
        for (int i = 0; i < col + 2; i++)
            System.out.print("-");
        System.out.println();
    }
    public static void main(String[] args) {
        Canvas c1 = new Canvas("data.txt");
        c1.readData();
        c1.printPoints();
        System.out.printf("The size of the canvas is %d-by-%d\n", c1.getRow(), c1.getCol());
        c1.printCanvas();
        System.out.println("\n\n\n-------------------------------------\n\n\n");
        Canvas c2 = new Canvas("data2.txt");
        c2.readData();
        c2.printPoints();
        System.out.printf("The size of the canvas is %d-by%d\n", c2.getRow(), c2.getCol()); //this line was misplace in the question.
        c2.printCanvas();
    }

}