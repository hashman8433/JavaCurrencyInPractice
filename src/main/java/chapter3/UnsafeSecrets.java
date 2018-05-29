package chapter3;

import java.util.HashSet;
import java.util.Set;

public class UnsafeSecrets {

    public static Set<Secret> knownSecrets;

    public void initialize() {
       knownSecrets = new HashSet<Secret>();
    }

    private static class Secret {
    }
}
