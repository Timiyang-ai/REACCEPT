@SafeVarargs
    public static <T> Array<T> ofAll(T... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return wrap(Arrays.copyOf(elements, elements.length));
    }