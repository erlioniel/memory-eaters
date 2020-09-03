package memory;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import static memory.Utils.inputAwait;

public class MemoryEaterEvelyn {
    // java -verbose:gc -Xmx250M memory.MemoryEaterBart
    @SneakyThrows
    public static void main(String[] args) {
        inputAwait();

        var counter = new AtomicLong();
        var eventList = new ConcurrentHashMap<Long, Event>();
        new Thread(new CorrupterRunner(counter, eventList)).start();
        for (int i = 0; i < 10; i++) {
            new Thread(new HealthyRunner(counter, eventList)).start();
        }

        while (true) {
            // Just wait
            Thread.sleep(500L);
            System.out.println("Completed: " + counter.get());
        }
    }

    @RequiredArgsConstructor
    private static class HealthyRunner implements Runnable {
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
    private static class CorrupterRunner implements Runnable {
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
