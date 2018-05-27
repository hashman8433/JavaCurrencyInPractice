package chapter1;

import annotation.NotThreadSafe;

@NotThreadSafe
public class UnsafeSequence {

    private int value;

    public int getNext() {
        return value++;
    }
}
