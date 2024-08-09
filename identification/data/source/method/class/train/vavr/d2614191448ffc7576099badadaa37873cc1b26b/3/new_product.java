static <T> Stream<T> ofAll(Iterator<? extends T> iterator) {
        Objects.requireNonNull(iterator, "iterator is null");
        if (iterator.hasNext()) {
            // we need to get the head, otherwise a tail call would get the head instead
            final T head = iterator.next();
            return new Cons<>(() -> head, () -> Stream.ofAll(iterator));
        } else {
            return Nil.instance();
        }
    }