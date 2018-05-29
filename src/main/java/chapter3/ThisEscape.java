package chapter3;

import javafx.event.Event;

import java.util.EventListener;

public class ThisEscape {

    public ThisEscape(EventSource source) {
        source.registerListener(
            new EventListener() {
                public void onEvent(Event e) {
                    doSomething(e);
                }
            });
        }

    private void doSomething(Event e) {
        System.out.println("ThisEscape calling doSomething");
    }

    private class EventSource {
        void registerListener(EventListener el) {
        }
    }
}
