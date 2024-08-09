static <T> Iterator<T> ofAll(java.lang.Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterable is null");
        return Iterator.ofAll(iterable.iterator());
    }