@SuppressWarnings("unchecked")
    static <T> Stream<T> ofAll(java.lang.Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Stream) {
            return (Stream<T>) elements;
        } else {
            class StreamFactory {
                <T> Stream<T> create(java.util.Iterator<? extends T> iterator) {
                    if (iterator.hasNext()) {
                        // we need to get the head, otherwise a tail call would get the head instead
                        final T head = iterator.next();
                        return new Cons<>(head, () -> create(iterator));
                    } else {
                        return Nil.instance();
                    }
                }
            }
            return new StreamFactory().create(elements.iterator());
        }
    }