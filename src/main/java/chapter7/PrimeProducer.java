package chapter7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread{
    private BlockingQueue<BigInteger> queue;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;

        while (Thread.currentThread().isInterrupted()) {
            queue.add(p.nextProbablePrime());
        }
    }

    public void cancel() {
        interrupt();
    }
}
