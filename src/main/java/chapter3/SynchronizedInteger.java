package chapter3;

import annotation.ThreadSafe;

@ThreadSafe
public class SynchronizedInteger {

    private int value;

    public synchronized int get() { return value; }
    public synchronized void set(int value) { this.value = value; }

}
