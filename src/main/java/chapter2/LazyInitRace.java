package chapter2;


import annotation.NotThreadSafe;

@NotThreadSafe
public class LazyInitRace {

    private ExpensiveObject instence = null;

    public ExpensiveObject getInstence() {
        if (instence == null)
            instence = new ExpensiveObject();
        return instence;
    }

    private class ExpensiveObject {
    }
}
