public class Slicing {
    public static void main(String arg[]) {
        new Slicing().runApp();
    }

    void runApp() {
        partA();
        partB();
        partC();
    }

    /**
     * Print 1D array
     * 
     * @param array
     */
    void printArray(int[] array) {
        for (int i : array)
            System.out.print(i + " ");
        System.out.println();
    }

    /**
     * Print 2D array
     * 
     * @param array
     */
    void printArray(int[][] array) {
        for (int[] i : array)
            printArray(i);
    }

    /**
     * Print arrays of 2D array
     * 
     * @param array
     */
    void printArray(int[][][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("-----Slice %d-----\n", i);
            printArray(array[i]);
        }
    }

    /**
     * Slice the input 1D array from start to end
     * 
     * @param array - array to slice
     * @param start - the start index
     * @param end   - the end index
     * @return - the result array
     */
    int[] slice1DArray(int[] array, int start, int end) {
        int[] result = new int[end - start + 1];
        for (int i = start, j = 0; i <= end; i++, j++) {
            result[j] = array[i];
        }
        return result;
    }

    /**
     * Driver code for partA.
     * You are not allowed to change this part.
     */
    void partA() {

        int[] array = { 10, 20, 30, 40, 50, 60 };
        System.out.println("Part A\nOriginal Array looks like:");
        printArray(array);

        int start = 1;
        int end = 4;
        int[] result = slice1DArray(array, start, end);
        System.out.printf("Slice from %d to %d\n", start, end);
        printArray(result);

        start = 3;
        end = 3;
        result = slice1DArray(array, start, end);
        System.out.printf("Slice from %d to %d\n", start, end);
        printArray(result);
    }

    /**
     * Slice a 2D array to a 2D array
     * 
     * @param array
     * @param r1    - the row of upper left corner of the slice
     * @param c1    - the column of the upper left corner of the slice
     * @param r2    - the row of bottom right corner of the slice
     * @param c2    - the column of bottom right corner of the slice
     * @return the sliced 2D array
     */
    int[][] slice2DArray(int[][] array, int r1, int c1, int r2, int c2) {
        int[][] result = new int[r2 - r1 + 1][c2 - c1 + 1];
        for (int i = 0, r = r1; r <= r2; r++, i++)
            for (int j = 0, c = c1; c <= c2; c++, j++)
                result[i][j] = array[r][c];
        return result;
    }

    /**
     * Driver code for part B, do not modify code here.
     */
    void partB() {
        int[][] array = { { 11, 12, 13, 14 },
                { 21, 22, 23, 24 },
                { 31, 32, 33, 34 }
        };

        System.out.println("\n\nPart B\nOriginal Array looks like:");
        printArray(array);

        int r1, r2, c1, c2;
        int[][] result;

        r1 = 1;
        r2 = 2;
        c1 = 0;
        c2 = 2;
        result = slice2DArray(array, r1, c1, r2, c2);
        System.out.printf("\nSlice 2D array from (%d, %d) to (%d, %d)\n", r1, c1, r2, c2);
        printArray(result);

        r1 = 1;
        r2 = 1;
        c1 = 1;
        c2 = 3;
        result = slice2DArray(array, r1, c1, r2, c2);
        System.out.printf("\nSlice 2D array from (%d, %d) to (%d, %d)\n", r1, c1, r2, c2);
        printArray(result);

    }

    /**
     * Slide a 2D array into an array of 2D arrays
     * 
     * @param array - the array to slice
     * @param h     - the height of the result array
     * @param w     - the weight of the result array
     * @return an array of 2D-arrays. There is no fixed order of the resulting
     *         arrays.
     *         That is, you can put the upper left slice as the first array or the
     *         bottom right slice as the first array.
     *         More explicit - for example, the original array is
     *         {{1, 2, 3, 4},
     *         {5, 6 ,7, 8}}
     *         h = 2, w = 2
     *         The result array can be either
     *         {
     *         {{1,2},
     *         {5, 6}}
     *         ,
     *         {{3,4},
     *         {7, 8}}
     *         }
     *         or
     *         {
     *         {{3,4},
     *         {7,8}}
     *         ,
     *         {{1,2},
     *         {5, 6}}
     *         }
     */
    int[][][] slice3D(int[][] array, int h, int w) {
        int numOfSliceColumn = array[0].length / w;
        int numOfSliceRow = array.length / h;
        int[][][] result = new int[numOfSliceColumn * numOfSliceRow][h][w];
        for (int r = 0, i = 0; r < numOfSliceRow; r++)
            for (int c = 0; c < numOfSliceColumn; c++, i++) {
                result[i] = slice2DArray(array, r * h, c * w, (r + 1) * h - 1, (c + 1) * w - 1);
            }
        return result;
    }

    /**
     * Driver code for Part C
     * Do not modify the code here.
     */
    void partC() {
        int[][] array = { { 11, 12, 13, 14, 15, 16 },
                { 21, 22, 23, 24, 25, 26 },
                { 31, 32, 33, 34, 35, 36 },
                { 41, 42, 43, 44, 45, 46 },
        };
        System.out.println("\n\nPart C\nOriginal Array looks like:");
        printArray(array);
        int height, width;
        int[][][] result;

        height = 2;
        width = 3;
        result = slice3D(array, height, width);
        System.out.printf("Slice the array into %d-by-%d arrays.\n", height, width);
        printArray(result);

        height = 2;
        width = 6;
        result = slice3D(array, height, width);
        System.out.printf("Slice the array into %d-by-%d arrays.\n", height, width);
        printArray(result);

    }

}