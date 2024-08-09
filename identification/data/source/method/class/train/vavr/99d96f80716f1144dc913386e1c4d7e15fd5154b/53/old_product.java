static <T> Iterator<T> ofAll(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "iterator is null");
        return Iterator.ofAll(iterable.iterator());
    }