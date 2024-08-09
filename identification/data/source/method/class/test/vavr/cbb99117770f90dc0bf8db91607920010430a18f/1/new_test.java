    @Override
    protected <T extends Comparable<? super T>> Traversable<T> ofJavaStream(java.util.stream.Stream<? extends T> javaStream) {
        return PriorityQueue.ofAll(naturalComparator(), javaStream);
    }