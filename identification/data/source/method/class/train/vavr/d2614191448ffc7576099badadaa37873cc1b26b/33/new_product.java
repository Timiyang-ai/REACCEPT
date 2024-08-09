@SuppressWarnings("unchecked")
    static <T> Stream<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Stream) {
            return (Stream<T>) elements;
        } else {
            return StreamFactory.create(elements.iterator());
        }
    }