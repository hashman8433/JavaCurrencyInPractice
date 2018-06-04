package chapter5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            t.setName("nThreads index " + i);
            t.start();
        }

        long start = System.nanoTime();
        System.out.println("start task:");
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }


    public static void main(String[] args) {
        Thread task = new Thread(new Runnable() {

            private AtomicInteger count = new AtomicInteger(0);
            public void run() {
                for (int i = 0; i < 1000; i++)
                        System.out.println("Thread id : " +
                                Thread.currentThread().getName() + " count  = " +
                                count.incrementAndGet());

            }
        });

        TestHarness testHarness = new TestHarness();
        try {
            System.out.println("fast Time ==> " + testHarness.timeTasks(20, task));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
