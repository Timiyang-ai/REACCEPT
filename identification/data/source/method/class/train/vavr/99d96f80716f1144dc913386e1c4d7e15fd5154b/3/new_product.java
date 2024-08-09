@SuppressWarnings("unchecked")
    static <T> Iterator<T> ofAll(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        if (iterable instanceof Iterator) {
            return (Iterator<T>) iterable;
        } else {
            return ofAll(iterable.iterator());
        }
    }