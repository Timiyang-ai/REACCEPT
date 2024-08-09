@SuppressWarnings("unchecked")
    public static <T> Array<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        return elements instanceof Array
               ? (Array<T>) elements
               : wrap(toArray(elements));
    }