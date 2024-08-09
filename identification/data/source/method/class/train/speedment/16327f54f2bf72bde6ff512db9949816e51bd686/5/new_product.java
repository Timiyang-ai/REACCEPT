public IntStream mapToInt(ToIntBiFunction<? super K, ? super V> mapper) {
        requireNonNull(mapper);
        return inner.mapToInt(e -> mapper.applyAsInt(e.getKey(), e.getValue()));
    }