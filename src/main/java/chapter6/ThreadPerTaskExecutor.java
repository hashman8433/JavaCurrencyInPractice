package chapter6;

import java.util.concurrent.Executor;

public class ThreadPerTaskExecutor extends AbstractSever implements Executor {

    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}
