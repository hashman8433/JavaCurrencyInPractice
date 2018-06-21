package chapter7;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BrokenPrimeProducer extends Thread{
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean concelled = false;

    BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!concelled) {
            queue.add(p.nextProbablePrime());
        }
    }

    public void concel () {
        concelled = true;
    }


    void consumePrimes() {

        BlockingQueue<BigInteger> queue =
                new ArrayBlockingQueue<BigInteger>(5);
        BrokenPrimeProducer producer = new BrokenPrimeProducer(queue);
        producer.start();

        while (needMorePrimes()) {
            try {
                comsumer(queue.take());
            } catch (InterruptedException e) {
                producer.concel();
            }
        }
    }

    private boolean needMorePrimes() {
        return true;
    }

    private void comsumer(BigInteger take) {
    }
}
