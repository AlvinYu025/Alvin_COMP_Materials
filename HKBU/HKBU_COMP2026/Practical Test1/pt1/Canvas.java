import java.util.Scanner;
import java.io.File;


/**
 * You can add additional method.
 */
public class Canvas {
    /**
     * Do not change this method. 
     * @param args
     */
    public static void main(String[] args) {
        new Canvas().runApp("data.txt");
        System.out.println("\n\n\n-------------------------------------\n\n\n");
        new Canvas().runApp("data2.txt");
    }
    /**
     * Do not change this method.
     * 
     * @param filename
     */
    void runApp(String filename) {
        int row = numberOfRows(filename);
        if (row <= 1) {
            System.out.println("Invalid file format");
            return;
        }
        int[] size = new int[2];
        int[][] coordinates = new int[row-1][2];
        char[] symbols = new char[row-1];
        readData(filename, size, coordinates, symbols);
        System.out.printf("The size of Canvas is: %d by %d\n", size[0], size[1]);
        System.out.printf("There are %d rows in the file, which means there are %d data points.\n", row, row-1);
        printCanvas(size, coordinates, symbols);
    }
    /**
     * Return how many rows in a file. data.txt contain 8 rows. 
     * It should return 8. 
     * 
     * @param filename The filename to read
     * @return how many rows in a file
     **/ 
    int numberOfRows(String filename) {
        //TODO
        return 8; //change this one
    }
    /**
     * Read data from a data file. The following code is hardcoded so that it gives you
     * the idea of how the data should be stored. 
     * 
     * @param filename - The method should read data from this file.
     * @param size - The method should store the size of the canvas in the array. size[0] stores width, size[1] stores height.
     * @param result - The method should store the coordinates into the array. result[i][0] is the row of data i and result[i][1] is the column of data i.
     * @param symbol - The method should store the symbols that be printed by each coordinate. 
     */
    void readData(String filename, int[] size, int[][] result, char[] symbol) {
        //TODO replace the following code with correct logic
        size[0] = 8;
        size[1] = 10;
        int[][] corr = {
                {1, 5}, 
                {2, 5},
                {7, 4}, 
                {4, 9}, 
                {2, 4}, 
                {3, 5}, 
                {4, 4}};
        char[] sym = {'*','*','+','+','+','o','o'};
        for (int i = 0; i < corr.length; i++) {
            result[i][0] = corr[i][0];
            result[i][1] = corr[i][1];
        }
        for (int i = 0; i < symbol.length; i++)
            symbol[i] = sym[i];
    }

    /**
     * Print Canvas with the given parameter 
     * 
     * @param size - the size of the canvas
     * @param coordinates - the coordinates of the points
     * @param symbols - the corresponding symbol for the points
     */
    void printCanvas(int[] size, int[][] coordinates, char[] symbols) {
        //TODO
    }

}