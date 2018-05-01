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
    }
}