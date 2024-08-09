static BitSet<Byte> ofAll(byte... array) {
        Objects.requireNonNull(array, "array is null");
        return BitSet.withBytes().ofAll(Iterator.ofAll(array));
    }