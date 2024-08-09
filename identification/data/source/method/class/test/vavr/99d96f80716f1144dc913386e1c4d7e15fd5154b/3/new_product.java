@SuppressWarnings("unchecked")
    static <T> Iterator<T> ofAll(java.lang.Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        if (iterable instanceof Iterator) {
            return (Iterator<T>) iterable;
        } else {
            return Iterator.ofAll(iterable.iterator());
        }
    }