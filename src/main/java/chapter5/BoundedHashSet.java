package chapter5;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;

        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded)
                sem.release();
        }

    }

    public boolean remove(T o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved)
            sem.release();
        return wasRemoved;
    }

    public static void main(String[] args) {
        final BoundedHashSet<String> JDBCPool = new BoundedHashSet<String>(2);
        Executor executor = Executors.newCachedThreadPool();

        for(int i = 0; i < 50; i++) {
            executor.execute(new JDBCProcess(JDBCPool));
        }

    }

    static class JDBCProcess implements Runnable {

        BoundedHashSet<String> JDBCPool;

        public JDBCProcess(BoundedHashSet<String> JDBCPool) {
            this.JDBCPool = JDBCPool;
        }

        public void run() {
            try {

                System.out.println("start to add number processs");
                double random = Math.random() * 100000;
                System.out.println("JDBCPool add number " + random + "  " +
                        JDBCPool.add(String.valueOf(random)));
                Thread.sleep(1000);
                System.out.println("JDBCPool remove number " + random + "  " +
                        JDBCPool.remove(String.valueOf(random)));

                Thread.currentThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
