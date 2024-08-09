    @Override
    protected <T extends Comparable<? super T>> TreeSet<T> ofJavaStream(java.util.stream.Stream<? extends T> javaStream) {
        return TreeSet.ofAll(javaStream);
    }