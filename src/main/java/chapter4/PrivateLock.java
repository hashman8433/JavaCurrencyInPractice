package chapter4;

import chapter2.Widget;

public class PrivateLock {

    private final Object myLock = new Object();
    Widget widget;

    void someMethod() {
        synchronized (myLock) {

        }
    }
}
