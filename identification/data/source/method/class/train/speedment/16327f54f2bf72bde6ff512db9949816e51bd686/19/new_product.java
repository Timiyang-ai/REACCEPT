public <R> Stream<R> flatMap(BiFunction<? super K, ? super V, ? extends Stream<? extends R>> mapper) {
        requireNonNull(mapper);
        return inner.flatMap(e -> mapper.apply(e.getKey(), e.getValue()));
    }