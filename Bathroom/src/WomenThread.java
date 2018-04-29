public class WomenThread extends Thread {
    private static Bathroom b;
    private int id;
    private boolean canLeave;
    private boolean needBathroom;

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
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             
             if(this.canLeave) {
             	womanExit(id, b);
             }
        }
    }

    public void womanEnter(int id, Bathroom b) {
        b.lock.lock();
        if (b.freeResources > 0 && b.nMenUsing == 0) {
            System.out.println("Woman " + id + " enter the bathroom");
            b.nWomenUsing++;
            b.freeResources--;
            this.canLeave = true;
            if (b.freeResources == 0) System.out.println("The bathroom is full");
        }
//        else {
//            try {
//                b.nWomenWaiting++;
//                b.womenQueue.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        b.lock.unlock();
    }

    public void womanExit(int id, Bathroom b) {
        b.lock.lock();
        System.out.println("Woman " + id + " left the bathroom");
        b.nWomenUsing--;
        b.freeResources++;
        if (b.freeResources == b.CAPACITY) System.out.println("The bathroom is empty");
//        while (b.nWomenUsing > 0) {
//
//        }
//        if (b.nWomenUsing==0 && b.nMenWaiting > 0) {
//            b.nMenWaiting--;
//            b.menQueue.signal();
//        }
//        while(b.nWomenUsing!=0 || b.nMenWaiting == 0){
//            try {
//                b.menQueue.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        b.lock.unlock();
        
        this.canLeave = false;
        this.needBathroom = false;
    }
}