public static LinkedHashSet<Byte> ofAll(byte[] array) {
        Objects.requireNonNull(array, "array is null");
        return LinkedHashSet.ofAll(Iterator.ofAll(array));
    }