public static HashSet<Byte> ofAll(byte... array) {
        Objects.requireNonNull(array, "array is null");
        return HashSet.ofAll(Iterator.ofAll(array));
    }