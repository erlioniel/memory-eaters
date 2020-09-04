package memory;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static memory.Utils.inputAwait;

public class MemoryEaterEvelyn {
    @SneakyThrows
    public static void main(String[] args) {
        inputAwait();

        var counter = new AtomicLong();
        var eventList = new ConcurrentHashMap<Long, Event>();
        new Thread(new EvilEvelyn(counter, eventList)).start();
        for (int i = 0; i < 10; i++) {
            new Thread(new HappyEvelyn(counter, eventList)).start();
        }

        while (true) {
            // Just wait
            Thread.sleep(500L);
            System.out.println("Cakes eaten: " + counter.get());
        }
    }

    @RequiredArgsConstructor
    private static class HappyEvelyn implements Runnable {
        private final AtomicLong counter;
        private final Map<Long, Event> eventList;

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                var cake = new MemoryCake();
                var idx = this.counter.incrementAndGet();
                eventList.put(idx, new MyEvent());
                Thread.sleep(10L);
            }
        }

        private static class MyEvent extends Event {
        }
    }

    @RequiredArgsConstructor
    private static class EvilEvelyn implements Runnable {
        private final AtomicLong counter;
        private final Map<Long, Event> eventList;

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                var cake = new MemoryCake();
                var idx = this.counter.incrementAndGet();
                eventList.put(idx, new Event() {});
                Thread.sleep(10L);
            }
        }
    }

    @RequiredArgsConstructor
    private static abstract class Event {
    }
}
