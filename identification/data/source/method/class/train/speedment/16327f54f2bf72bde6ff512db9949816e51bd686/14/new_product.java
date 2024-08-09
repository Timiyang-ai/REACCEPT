public IntStream flatMapToInt(BiFunction<? super K, ? super V, ? extends IntStream> mapper) {
        requireNonNull(mapper);
        return inner.flatMapToInt(e -> mapper.apply(e.getKey(), e.getValue()));
    }