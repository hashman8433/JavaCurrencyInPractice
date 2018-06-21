package chapter7;

import java.util.concurrent.*;

public class TimedRun710 {

    private static final ExecutorService taskExec =
            Executors.newCachedThreadPool();

    public static void timeRun(Runnable r,
                               long timeout, TimeUnit unit) {
        Future<?> task = taskExec.submit(r);

        try {
            task.get(timeout, unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            task.cancel(true);
            System.out.println(task.isCancelled());
            e.printStackTrace();
        } finally {

        }
    }


    public static void main(String[] args) {
        TimedRun710.timeRun(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(!Thread.currentThread().isInterrupted()) {
//                for (;true;) {  // cant be stop by interrupted
                    i++;
                    if (i % 1000000000 == 0)
                        System.out.println(i);
                }
            }
        }, 1, TimeUnit.SECONDS);
    }
}
