package io.faucette.event_emitter;


import static org.junit.Assert.*;
import org.junit.*;

import java.util.concurrent.atomic.AtomicBoolean;


public class EventEmitterTest {
    @Test
    public void testBasic() {
        EventEmitter emitter = new EventEmitter();
        final AtomicBoolean trueCalled = new AtomicBoolean(false);
        final AtomicBoolean falseCalled = new AtomicBoolean(false);

        Emitter.Callback trueCallback = emitter.on("test", new Emitter.Callback() {
            @Override
            public void call(Object[] args) {
                trueCalled.set(true);
            }
        });
        Emitter.Callback falseCallback = emitter.on("test", new Emitter.Callback() {
            @Override
            public void call(Object[] args) {
                falseCalled.set(true);
            }
        });

        assertTrue(emitter.getEventCount("test") == 2);
        emitter.emit("test");
        assertTrue(trueCalled.get());
        assertTrue(falseCalled.get());
        trueCalled.set(false);
        falseCalled.set(false);

        emitter.off("test", trueCallback);

        assertTrue(emitter.getEventCount("test") == 1);
        emitter.emit("test");
        assertFalse(trueCalled.get());
        assertTrue(falseCalled.get());

        emitter.off("test");
        assertTrue(emitter.getEventCount("test") == 0);
    }
}
