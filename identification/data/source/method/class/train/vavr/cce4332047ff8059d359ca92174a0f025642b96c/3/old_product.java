static <T> Tree<T> ofAll(java.util.stream.Stream<? extends T> javaStream) {
        Objects.requireNonNull(javaStream, "javaStream is null");
        return ofAll(io.vavr.collection.Iterator.ofAll(javaStream.iterator()));
    }