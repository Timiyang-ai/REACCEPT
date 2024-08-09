public static BitSet<Byte> ofAll(byte... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return BitSet.withBytes().ofAll(Iterator.ofAll(elements));
    }