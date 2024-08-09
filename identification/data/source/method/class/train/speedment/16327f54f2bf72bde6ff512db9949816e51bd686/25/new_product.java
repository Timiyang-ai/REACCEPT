public LongStream mapToLong(ToLongBiFunction<? super K, ? super V> mapper) {
        requireNonNull(mapper);
        return inner.mapToLong(e -> mapper.applyAsLong(e.getKey(), e.getValue()));
    }