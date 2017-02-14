package io.faucette.event_emitter;


public interface Emitter {

    public interface Callback {
        void call(Emitter emitter, Object[] args);
    }

    int getEventCount(String name);
    Callback on(String name, Callback callback);
    void off(String name, Callback callback);
    void off(String name);
    void emit(String name, Object... args);
}
