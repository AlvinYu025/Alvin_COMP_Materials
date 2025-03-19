class A1 {
    private int[] x; 
    public A1(int size) {
        this.x = new int[size];
        for (int i = 0; i < size; i++)
            x[i] = 5;
    }
}

class A2 {
    final String name;
    public A2(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}

class A3 extends A2{
    public A3(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "A3: " + getName();
    }
}

public class A4 {
    public static void main(String[] arg) {
        A2[] array = new A2[10];
        for (int i = 0; i < 5; i++)
            array[i] = new A2("A2-" + i);
        for (int i = 5; i < 10; i++)
            array[i] = new A3("A3-" + i);
        for (A2 a : array)
            System.out.println(a.getName()); //print their name, not the toString()
    }
}

