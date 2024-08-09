public static Array<Byte> ofAll(byte[] array) {
        Objects.requireNonNull(array, "array is null");
        return Array.ofAll(Iterator.ofAll(array));
    }