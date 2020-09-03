package memory;

import lombok.SneakyThrows;

import java.util.Vector;

import static memory.Utils.inputAwait;

public class MemoryEaterZoe {
    // java -Xmx20M memory.MemoryEaterZoe
    @SneakyThrows
    public static void main(String[] args) {
        inputAwait();

        var vector = new Vector<>();
        while (true) {
            vector.add(new MemoryCake(128));
            System.out.println(vector.size());
            Thread.sleep(100L);
        }
    }
}
