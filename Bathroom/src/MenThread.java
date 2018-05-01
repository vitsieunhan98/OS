import java.util.Random;

public class MenThread extends Thread {
    private static Bathroom b;
    private int id;
    private boolean canLeave;
    private boolean needBathroom;
    Random rnd = new Random();

    public MenThread(int id, Bathroom b) {
        this.id = id;
        this.b = b;
        this.canLeave = false;
        this.needBathroom = true;
    }

    @Override
    public void run() {
        while(this.needBathroom) {
        	manEnter(id, b);
        	
        	try {
                Thread.sleep(500*rnd.nextInt(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       	 
            if(this.canLeave) {
            	manExit(id, b);
            }
        }
    }

    public void manEnter(int id, Bathroom b) {
        try {
        	b.lock.lock();
            if (b.freeResources > 0 && b.nWomenUsing == 0) {
                System.out.println("Man " + id + " enter the bathroom");
                b.nMenUsing++;
                b.freeResources--;
                this.canLeave = true;
                if (b.freeResources == 0) System.out.println("The bathroom is full");
            }
        } finally {
        	b.lock.unlock();
        }
    }

    public void manExit(int id, Bathroom b) {
        try {
        	b.lock.lock();
            System.out.println("Man " + id + " left the bathroom");
            b.nMenUsing--;
            b.freeResources++;
            if (b.freeResources == b.CAPACITY) System.out.println("The bathroom is empty");
        } finally {
        	b.lock.unlock();
        }
        
        this.canLeave = false;
        this.needBathroom = false;
    }
}