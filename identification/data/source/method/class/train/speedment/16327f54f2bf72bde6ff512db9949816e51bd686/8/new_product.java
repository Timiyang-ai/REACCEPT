@Override
    public LongStream flatMapToLong(Function<? super Map.Entry<K, V>, ? extends LongStream> mapper) {
        return inner.flatMapToLong(requireNonNull(mapper));
    }