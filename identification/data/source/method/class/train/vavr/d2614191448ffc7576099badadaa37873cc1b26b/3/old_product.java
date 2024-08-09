static <T> Stream<T> ofAll(Iterator<? extends T> iterator) {
        Objects.requireNonNull(iterator, "iterator is null");
        if (iterator.hasNext()) {
            return new Cons<>(iterator.next(), () -> Stream.ofAll(iterator));
        } else {
            return Nil.instance();
        }
    }