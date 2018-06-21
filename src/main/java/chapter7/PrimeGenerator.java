package chapter7;

import annotation.GuardeBy;
import annotation.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

@ThreadSafe
public class PrimeGenerator implements Runnable{

    @GuardeBy("this")
    private final List<BigInteger> primes =
            new ArrayList<BigInteger>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while(!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                System.out.println(p);
                primes.add(p);
            }
        }

    }

    /**
     * stop thread continue produce prime number
     */
    public void cancel() { cancelled = true; }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }


    /**
     * example 7.2
     * @return
     * @throws InterruptedException
     */
    List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        try {
            SECONDS.sleep(500);
        } finally {

            generator.cancel();
            System.out.println("finally proccess");
        }
        // thread will continue proccess when proof cancel not in finally
//        generator.cancel();
        return generator.get();
    }


    public static void main(String[] args) throws InterruptedException {
        final PrimeGenerator generator = new PrimeGenerator();
        Runnable r = new Runnable() {
            @Override
            public void run() {

                try {
                    generator.aSecondOfPrimes();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(r);
        executor.shutdownNow();
        System.out.println(executor.isShutdown());
    }
}
