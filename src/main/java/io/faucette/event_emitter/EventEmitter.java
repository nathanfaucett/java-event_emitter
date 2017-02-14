package io.faucette.event_emitter;


import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class EventEmitter implements Emitter {
    private Map<String, List<Emitter.Callback>> events;


    public EventEmitter() {
        events = new HashMap<>();
    }

    public int getEventCount(String name) {
        List<Emitter.Callback> eventList = events.get(name);

        if (eventList != null) {
            return eventList.size();
        } else {
            return 0;
        }
    }

    public Callback on(String name, Callback callback) {
        List<Emitter.Callback> eventList = events.get(name);

        if (eventList == null) {
            eventList = new ArrayList<>();
            events.put(name, eventList);
        }

        eventList.add(callback);

        return callback;
    }
    public void off(String name, Callback callback) {
        List<Emitter.Callback> eventList = events.get(name);

        if (eventList != null) {
            eventList.remove(callback);
        }
    }
    public void off(String name) {
        events.remove(name);
    }
    public void emit(String name, Object... args) {
        List<Emitter.Callback> eventList = events.get(name);

        if (eventList != null) {
            for (Emitter.Callback callback: eventList) {
                callback.call(args);
            }
        }
    }
}
