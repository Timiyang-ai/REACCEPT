public LongStream mapToLong(ToLongBiFunction<? super K, ? super V> mapper) {
        return inner.mapToLong(e -> mapper.applyAsLong(e.getKey(), e.getValue()));
    }