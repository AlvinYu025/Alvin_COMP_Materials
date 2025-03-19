public class Flask {
    private int volume;
    private int capacity;
    public Flask(int capacity) {
        this.capacity = capacity;
        this.volume = 0;
    }
    public void fill() {
        volume = capacity;
    }
    public void empty() {
        volume = 0;
    }
    public void pour(Flask f) {
        int remain = f.capacity - f.volume;
        if (remain >= volume) {
            f.volume += volume;
            volume = 0;
        } else {
            f.volume = f.capacity;
            this.volume -= remain;
        }  
    }
    public String toString() {
        return "(" + volume + "/" + capacity + ")";
    }
    public static void main(String[] arg) {
        Flask f1 = new Flask(8);
        Flask f2 = new Flask(3);

        System.out.print("Step1\nFlask 1:");
        System.out.print(f1); // 0/8
        System.out.print("\nFlask 2:");
        System.out.print(f2); // 0/3

        f1.fill();
        System.out.print("\n\nStep2\nFlask 1:");
        System.out.print(f1); // 8/8


        f1.pour(f2); 
        System.out.print("\n\nStep3\nFlask 1:");
        System.out.print(f1); // 5/8
        System.out.print("\nFlask 2:");
        System.out.print(f2); // 3/3

        f1.empty(); 
        f2.pour(f1);        
        System.out.print("\n\nStep4\nFlask 1:");
        System.out.print(f1); // 3/8
        System.out.print("\nFlask 2:");
        System.out.print(f2); // 0/3

        f1.fill();        
        f1.pour(f2);
        f2.empty();
        f1.pour(f2);
        System.out.print("\n\nStep5\nFlask 1:");
        System.out.print(f1); //  2/8
        System.out.print("\nFlask 2:");
        System.out.print(f2); //  3/3

        System.out.println("\n\nDo you know how to make a 1?");
        System.out.println("Sorry, this is not part of the question");
    }
}
