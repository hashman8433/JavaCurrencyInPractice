package chapter4;

public class FunctionSynTest implements Runnable {


    public void run() {
        while (true) {
            count();
        }
    }

    public synchronized void count() {
        System.out.println("+++++++++++++++++++++++++" + Thread.currentThread().getName());
        System.out.println("=========================" + Thread.currentThread().getName());
        System.out.println("=========================" + Thread.currentThread().getName());
        System.out.println("=========================" + Thread.currentThread().getName());
        System.out.println("=========================" + Thread.currentThread().getName());
        System.out.println("+++++++++++++++++++++++++"+ Thread.currentThread().getName() +"\n\n");

    }

    public static void main(String[] args) {
        FunctionSynTest func = new FunctionSynTest();
        Thread t = new Thread(func, "thread");
        Thread t1 = new Thread(func, "thread1");
        Thread t2 = new Thread(func, "thread2");
        t.start();
        t1.start();
        t2.start();

        FunctionSynTest func2 = new FunctionSynTest();
        Thread t3 = new Thread(func, "thread3");
        t3.start();
    }

}
