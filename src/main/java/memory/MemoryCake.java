package memory;

public class MemoryCake {
    private final byte[] bytes;

    public MemoryCake() {
        this(1);
    }

    public MemoryCake(int size) {
        this.bytes = new byte[size * 1024];
    }
}
