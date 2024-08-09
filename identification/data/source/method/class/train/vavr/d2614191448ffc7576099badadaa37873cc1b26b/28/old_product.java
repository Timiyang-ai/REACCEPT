@SuppressWarnings("unchecked")
    static <T> Stream<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Stream) {
            return (Stream<T>) elements;
        } else {
            class StreamFactory {
                // TODO: in a future version of Java this will be a private interface method
                <T> Stream<T> create(java.util.Iterator<? extends T> iterator) {
                    if (iterator.hasNext()) {
                        // we need to get the head, otherwise a tail call would get the head instead
                        final T head = iterator.next();
                        return new Cons<>(() -> head, () -> create(iterator));
                    } else {
                        return Nil.instance();
                    }
                }
            }
            return new StreamFactory().create(elements.iterator());
        }
    }