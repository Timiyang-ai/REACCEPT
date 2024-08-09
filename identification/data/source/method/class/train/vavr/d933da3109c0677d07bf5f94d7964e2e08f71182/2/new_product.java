@SuppressWarnings("unchecked")
    public static <T> Array<T> ofAll(Iterable<? extends T> elements) {
        Objects.requireNonNull(elements, "elements is null");
        if (elements instanceof Array) {
            return (Array<T>) elements;
        }
        if (elements instanceof ListView
                && ((ListView<T, ?>) elements).getDelegate() instanceof Array) {
            return (Array<T>) ((ListView<T, ?>) elements).getDelegate();
        }
        return wrap(toArray(elements));
    }