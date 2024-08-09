static <T> List<T> ofAll(java.util.stream.Stream<? extends T> javaStream) {
        Objects.requireNonNull(javaStream, "javaStream is null");
        final java.util.Iterator<? extends T> iterator = javaStream.iterator();
        List<T> list = List.empty();
        while (iterator.hasNext()) {
            list = list.prepend(iterator.next());
        }
        return list.reverse();
    }