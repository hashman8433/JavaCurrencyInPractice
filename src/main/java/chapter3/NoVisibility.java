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

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ready = true;

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number = 42;


    }

}
