@SuppressWarnings("unchecked")
    public static <T> Array<T> ofAll(java.lang.Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Array) {
            return (Array<T>) elements;
        } else {
            return wrap(create(elements));
        }
    }