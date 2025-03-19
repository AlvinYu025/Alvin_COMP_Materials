//This line suggest RedBean should be in another package
import plantPackage.RedBean;
public class Allotment {
    private RedBean[] beans;
    public void plantRedBean() {
        //beans is initialized to null (default value)
        //You cannot add a constructor by rule
        if (beans == null) {
            beans = new RedBean[1];
            beans[0] = new RedBean();
        } else {
            RedBean[] newBeans = new RedBean[beans.length + 1];
            for (int i = 0; i < beans.length; i++)
                newBeans[i] = beans[i];
            newBeans[beans.length] = new RedBean();
            beans = newBeans;
        }
    }
    public void growRedBeans() {
        for (int i = 0; i < beans.length; i++)
            beans[i].grow();
    }
    public void checkRedBeans() {
        System.out.println("Red Beans in the allotment:" + beans.length);
        for (RedBean r : beans)
            if (r != null) //be careful for null beans. A good habit
                System.out.println(r);
    }
    public int harvest() {
        int count = 0;
        for (int i = 0; i < beans.length; i++)
            if (beans[i].getHeight() == RedBean.MAX_HEIGHT) {
                count++;
                beans[i] = null; //this mark out which allotment slot is null
            }

        //resize the allotment to remove the null beans
        RedBean[] newBeans = new RedBean[beans.length - count];
        int j = 0;
        for (int i = 0; i < beans.length; i++)
            if (beans[i] != null)
                newBeans[j++] = beans[i];
        beans = newBeans;

        return count;
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
