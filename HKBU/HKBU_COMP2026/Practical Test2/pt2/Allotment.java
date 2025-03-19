/* Don't change this line */
import plantPackage.RedBean;

/**
 * Not allowed to add any field to Allotment
 * Not allowed to add any method to Allotment
 * Not allowed to change public static void main
 * 
 * You can and you should change the methods below.
 * You need to create the class RedBean too, in a separated file
 */
public class Allotment {

    private RedBean[] beans;
    public void plantRedBean() {
 
    }
    public void growRedBeans() {
    }
    public void checkRedBeans() {
    }
    public int harvest() {
        return 0; //return how many redBean is harvested
    }
    public static void main(String[] args) {
        int day = 1;
        Allotment a = new Allotment();
        a.plantRedBean();
        a.plantRedBean();
        a.plantRedBean();
        for (; day < 4; day++) {
            a.growRedBeans();
            System.out.println("Day " + (day));
            a.checkRedBeans();
        }
        a.plantRedBean();
        for (; day < 7; day++) {
            a.growRedBeans();
            System.out.println("Day " + (day));
            a.checkRedBeans();
        }
        int count = a.harvest();
        System.out.println("Day " + (day++) + " - Harvest " + count + " beans");
        a.checkRedBeans();
        System.out.println("Plant two more beans");
        a.plantRedBean();
        a.plantRedBean();
        a.checkRedBeans();
    }
}
