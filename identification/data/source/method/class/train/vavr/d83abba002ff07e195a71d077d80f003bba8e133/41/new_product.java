public static <T> Queue<T> ofAll(java.util.stream.Stream<? extends T> javaStream) {
        Objects.requireNonNull(javaStream, "javaStream is null");
        return new Queue<>(List.ofAll(javaStream), List.empty());
    }