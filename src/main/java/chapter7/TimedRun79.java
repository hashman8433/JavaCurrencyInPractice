package chapter7;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimedRun79 {

    private static final ScheduledExecutorService cancelExec =
            Executors.newScheduledThreadPool(50);

    public static void timedRun(final Runnable r,
                               long timeout, TimeUnit unit) throws Exception {
        class RethrowableTask implements Runnable {
            private volatile Throwable t;

            @Override
            public void run() {
                try {
                    r.run();
                } catch (Throwable t) {
                    this.t = t;
                }
            }
            void rethrow() throws Exception {
                if (t != null)
                    throw launderThrowable(t);
            }
            Exception launderThrowable(Throwable t) {
                return new Exception(t);
            }
        }

        RethrowableTask task = new RethrowableTask();
        final Thread taskThread = new Thread(task);
        taskThread.start();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("task run");
                taskThread.interrupt();
            }
        }, timeout, unit);
        System.out.println("task join");
        taskThread.join(unit.toMillis(timeout));
        System.out.println("task run end");
        task.rethrow();
        System.out.println("task rethrow");

    }


    public static void main(String[] args) throws Exception {

        TimedRun79.timedRun(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException();

            }
        }, 1, TimeUnit.MILLISECONDS);
    }

}
