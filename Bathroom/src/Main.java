public class Main {
	private static Bathroom b = Bathroom.getInstance();

    public static void main(String[] args) throws InterruptedException {
        String Line = b.incomingUser();
        System.out.println(Line);
        
        Thread[] men = new Thread[Line.length()];
        Thread[] women = new Thread[Line.length()];

        for (int i = 0; i < Line.length(); i++) {
            if (Line.charAt(i) == 'M') {
                men[i] = new MenThread(i, b);
                men[i].start();
            }
            if (Line.charAt(i) == 'W') {
                women[i] = new WomenThread(i, b);
                women[i].start();
            }
        }

//        for (int i = 1; i < Line.length(); i++) {
//            if (Line.charAt(i) != Line.charAt(i - 1)) {
//                if (Line.charAt(i) == 'M') {
//                    try {
//                        men[i].join();
//                    } catch (InterruptedException ex) {
//                        System.out.println("Error");
//                    }
//
//                } else {
//                    try {
//                        women[i].join();
//                    } catch (InterruptedException ex) {
//                        System.out.println("Error");
//                    }
//                }
//            }
//        }
    }
}