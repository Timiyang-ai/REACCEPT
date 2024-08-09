public static Queue<Byte> ofAll(byte... array) {
        Objects.requireNonNull(array, "array is null");
        return ofAll(List.ofAll(array));
    }