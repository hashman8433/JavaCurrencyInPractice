package chapter3;

import annotation.NotThreadSafe;

@NotThreadSafe
public class MutableInteger {
    private int value;

    public int get() { return value;}
    public void set(int value) { this.value = value;}
}
