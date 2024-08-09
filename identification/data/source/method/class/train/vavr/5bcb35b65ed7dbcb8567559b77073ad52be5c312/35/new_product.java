@SuppressWarnings("unchecked")
    public static <T> Vector<T> ofAll(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        if (iterable instanceof Traversable && io.vavr.collection.Collections.isEmpty(iterable)) {
            return empty();
        }
        if (iterable instanceof Vector) {
            return (Vector<T>) iterable;
        }
        if (iterable instanceof ListView
                && ((ListView<T, ?>) iterable).getDelegate() instanceof Vector) {
            return (Vector<T>) ((ListView<T, ?>) iterable).getDelegate();
        }
        final Object[] values = withSize(iterable).toArray();
        return ofAll(BitMappedTrie.ofAll(values));
    }