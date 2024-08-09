public static Queue<Byte> ofAll(byte[] array) {
        Objects.requireNonNull(array, "array is null");
        return Queue.ofAll(List.ofAll(array));
    }