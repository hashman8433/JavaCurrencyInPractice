package chapter3;

public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready)
                Thread.yield();
            System.out.println(number);
        }
    }

    // for result obvious  Thread sleep 3s
    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
        // probably print 0

    }

}
