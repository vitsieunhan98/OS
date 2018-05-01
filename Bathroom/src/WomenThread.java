import java.util.Random;

public class WomenThread extends Thread {
    private static Bathroom b;
    private int id;
    private boolean canLeave;
    private boolean needBathroom;
    Random rnd = new Random();

    public WomenThread(int id, Bathroom b) {
        this.id = id;
        this.b = b;
        this.canLeave = false;
        this.needBathroom = true;
    }

    @Override
    public void run() {
    	while(this.needBathroom) {
    		womanEnter(id, b);
        	
        	try {
                Thread.sleep(500*rnd.nextInt(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             
			if(this.canLeave) {
				womanExit(id, b);
			}
        }
    }

    public void womanEnter(int id, Bathroom b) {
        try {
        	b.lock.lock();
            if (b.freeResources > 0 && b.nMenUsing == 0) {
                System.out.println("Woman " + id + " enter the bathroom");
                b.nWomenUsing++;
                b.freeResources--;
                this.canLeave = true;
                if (b.freeResources == 0) System.out.println("The bathroom is full");
            }
        } finally {
        	b.lock.unlock();
        }
    }

    public void womanExit(int id, Bathroom b) {
        try {
        	b.lock.lock();
            System.out.println("Woman " + id + " left the bathroom");
            b.nWomenUsing--;
            b.freeResources++;
            if (b.freeResources == b.CAPACITY) System.out.println("The bathroom is empty");
        } finally {
        	b.lock.unlock();
        }
        
        this.canLeave = false;
        this.needBathroom = false;
    }
}