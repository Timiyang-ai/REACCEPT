@SuppressWarnings("unchecked")
    public static <T> Stream<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Stream) {
            return (Stream<T>) elements;
        } else if (elements instanceof ListView
                && ((ListView<T, ?>) elements).getDelegate() instanceof Stream) {
            return (Stream<T>) ((ListView<T, ?>) elements).getDelegate();
        } else {
            return StreamFactory.create(elements.iterator());
        }
    }