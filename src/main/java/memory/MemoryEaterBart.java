package memory;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

import static memory.Utils.inputAwait;

public class MemoryEaterBart {
    @SneakyThrows
    public static void main(String[] args) {
        inputAwait();

        var counter = new AtomicLong();
        new Thread(new EvilBart(counter)).start();
        for (int i = 0; i < 10; i++) {
            new Thread(new HappyBart(counter)).start();
        }

        while (true) {
            // Just wait
            Thread.sleep(500L);
            System.out.println("Cakes eaten: " + counter.get());
        }
    }

    @RequiredArgsConstructor
    private static class HappyBart implements Runnable {
        private final AtomicLong counter;

        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                var cake = new MemoryCake();
                this.counter.incrementAndGet();
                Thread.sleep(10L);
            }
        }
    }

    @RequiredArgsConstructor
    private static class EvilBart implements Runnable {
        private final AtomicLong counter;

        @Override
        @SneakyThrows
        public void run() {
            var vector = new Vector<>();
            while (true) {
                vector.add(new MemoryCake());
                this.counter.incrementAndGet();
                Thread.sleep(10L);
            }
        }
    }
}
