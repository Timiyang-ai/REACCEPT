@Override
    public <R> Stream<R> map(Function<? super Map.Entry<K, V>, ? extends R> mapper) {
        return inner.map(requireNonNull(mapper));
    }