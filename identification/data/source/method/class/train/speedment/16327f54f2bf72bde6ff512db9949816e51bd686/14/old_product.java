public IntStream flatMapToInt(BiFunction<? super K, ? super V, ? extends IntStream> mapper) {
        return inner.flatMapToInt(e -> mapper.apply(e.getKey(), e.getValue()));
    }