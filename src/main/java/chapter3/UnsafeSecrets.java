package chapter3;

import annotation.NotThreadSafe;

import java.util.HashSet;
import java.util.Set;

@NotThreadSafe
public class UnsafeSecrets {

    public static Set<Secret> knownSecrets;

    public void initialize() {
       knownSecrets = new HashSet<Secret>();
    }

    private static class Secret {
    }
}
