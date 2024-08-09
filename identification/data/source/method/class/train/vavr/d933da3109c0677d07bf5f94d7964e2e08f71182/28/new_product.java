@SuppressWarnings("varargs")
    @SafeVarargs
    public static <T> Array<T> of(T... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return wrap(Arrays.copyOf(elements, elements.length));
    }