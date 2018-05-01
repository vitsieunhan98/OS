import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bathroom {
    static int men, women;
    public static final int CAPACITY = 3;
    //Semaphore empty = new Semaphore(1);
    public Lock lock = new ReentrantLock();
    public int freeResources = CAPACITY;
    public static int nMenWaiting = 0;
    public static int nMenUsing = 0;
    public static int nWomenWaiting = 0;
    public static int nWomenUsing = 0;
    private static Bathroom instance = new Bathroom(CAPACITY);
    private final int capacity;
    
    public Bathroom(int capacity) {
    	this.capacity = capacity;
    }

    public static String incomingUser() {
        String line = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of men: ");
        men = sc.nextInt();
        System.out.println("Enter the number of women: ");
        women = sc.nextInt();

        while (men != 0 || women != 0) {
            int r = new Random().nextInt(2);
            switch (r) {
                case 0:
                    if (men > 0) {
                        line=line.concat("M");
                        men--;
                    }
                    break;
                case 1:
                    if (women > 0) {
                        line=line.concat("W");
                        women--;
                    }
                    break;
            }
        }
        return line;
    }
    
    public static Bathroom getInstance() {
        return instance;
    }
}
