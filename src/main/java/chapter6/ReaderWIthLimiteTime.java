package chapter6;

import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;

public class ReaderWIthLimiteTime {

    private static final long TIME_BUDGET = 100000;
    private static final Ad DEFAULT_AD = new Ad();
    private ExecutorService exec = Executors.newFixedThreadPool(10);

    Page readerPageWithAd() throws InterruptedException {
        long endNanos = System.nanoTime() + TIME_BUDGET;
        Ad ad;
        FutureTask<Ad> f = (FutureTask<Ad>) exec.submit(new Callable<Ad>() {
            @Override
            public Ad call() throws Exception {
                return new Ad();
            }
        });

        long duringTime = endNanos - System.nanoTime();
        try {
            ad = f.get(duringTime, TimeUnit.NANOSECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            ad = DEFAULT_AD;
            f.cancel(true);
        }

        return null;
    }

    private class Page {
    }

    private static class Ad {
    }
}
