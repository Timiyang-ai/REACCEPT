@SuppressWarnings("varargs")
    @SafeVarargs
    public static <T> Queue<T> ofAll(T... elements) {
        Objects.requireNonNull(elements, "elements is null");
        return Queue.ofAll(List.ofAll(elements));
    }