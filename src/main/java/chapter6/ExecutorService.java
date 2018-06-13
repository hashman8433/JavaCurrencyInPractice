package chapter6;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public interface ExecutorService extends Executor {
    void shutdown();
    List<Runnable> shutdownNow();
    boolean isShutdown();
    boolean isTerminated();
    boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException;
}
