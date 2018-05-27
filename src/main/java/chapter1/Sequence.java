package chapter1;


import annotation.ThreadSafe;

@ThreadSafe
public class Sequence {

    private int Value;

    public synchronized int getNext() {
        return Value++;
    }

}
