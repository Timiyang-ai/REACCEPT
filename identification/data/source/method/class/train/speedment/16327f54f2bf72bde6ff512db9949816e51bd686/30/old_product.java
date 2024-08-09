public LongStream flatMapToLong(BiFunction<? super K, ? super V, ? extends LongStream> mapper) {
        return inner.flatMapToLong(e -> mapper.apply(e.getKey(), e.getValue()));
    }