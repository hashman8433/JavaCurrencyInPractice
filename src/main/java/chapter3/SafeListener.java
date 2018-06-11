package chapter3;

import java.awt.*;
import java.util.EventListener;

public class SafeListener {
    private final EventListener listener;
    
    private SafeListener() {
        listener = new EventListener() {
            public void onEvent(Event e) {
                doSomething(e);
            }
        };
    }

    public static SafeListener newInstence(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }
    
    private void doSomething(Event e) {
    }

    private static class EventSource {
        public void registerListener(EventListener listener) {
        }
    }
}
