import java.util.Scanner;
import java.io.File;

public class Canvas {
    public static void main(String[] args) {
        new Canvas().runApp("data.txt");
        System.out.println("\n\n\n-------------------------------------\n\n\n");
        new Canvas().runApp("data2.txt");
    }
    int numberOfRows(String filename) {
        int i = 0;
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                i++;
                scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return i;
    }
    void readData(String filename, int[] size, int[][] result, char[] symbol) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            int count = 0;
            size[0] = row;
            size[1] = col;
            while (scanner.hasNextLine()) {
                result[count][0] = scanner.nextInt();
                result[count][1] = scanner.nextInt();
                symbol[count] = scanner.next().charAt(0);
                count++;
            }
        } catch (Exception e) {
            System.out.println("Error reading lines");

        }
        return;
    }
    void printCanvas(int[] size, int[][] coordinates, char[] symbols) {
        char[][] canvas = new char[size[0]][size[1]];
        for (int i = 0; i < size[0]; i++)
            for (int j = 0; j < size[1]; j++)
                canvas[i][j] = ' ';
        for (int i = 0; i < coordinates.length; i++)
            canvas[coordinates[i][0]][coordinates[i][1]] = symbols[i];

        for (int i = 0; i < size[1] + 2; i++)
            System.out.print("-");
        System.out.println();
        for (int i = 0; i < size[0]; i++) {
            System.out.print("|");
            for (int j = 0; j < size[1]; j++) {
                System.out.print(canvas[i][j]);
            }
            System.out.println("|");
        }
        for (int i = 0; i < size[1] + 2; i++)
            System.out.print("-");
        System.out.println();
    }
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
}