static <T> Stream<T> ofAll(java.util.stream.Stream<? extends T> javaStream) {
        Objects.requireNonNull(javaStream, "javaStream is null");
        return StreamFactory.create(javaStream.iterator());
    }