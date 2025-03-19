public class Point {
    private final int r;
    private final int c;
    private final char symbol;
    public Point(int r, int c, char symbol) {
        this.r = r;
        this.c = c;
        this.symbol = symbol;
    }
    public int getR() { return r; }
    public int getC() { return c; }
    public char getSymbol() { return symbol; }
    public String toString() {
        return "(" + r + "," + c + ") - " + symbol;
    }
}
