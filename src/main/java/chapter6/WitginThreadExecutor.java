package chapter6;

import java.util.concurrent.Executor;

public class WitginThreadExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}
