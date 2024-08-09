@SuppressWarnings("varargs")
    @SafeVarargs
    public static <T> Queue<T> of(T... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return Queue.ofAll(List.of(elements));
    }