@SuppressWarnings("unchecked")
    public static <T> Vector<T> ofAll(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        if (iterable instanceof Vector) {
            return (Vector<T>) iterable;
        } else if (iterable instanceof Collection<?>) {
            final Object[] array = ((Collection<? extends T>) iterable).toArray();
            return ofAll(BitMappedTrie.ofAll(array));
        } else {
            final Seq<? extends T> elems = seq(iterable);
            final Object[] array = asArray(elems.iterator(), elems.size());
            return ofAll(BitMappedTrie.ofAll(array));
        }
    }