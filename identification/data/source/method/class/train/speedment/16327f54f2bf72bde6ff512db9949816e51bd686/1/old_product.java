public <R> Stream<R> map(BiFunction<? super K, ? super V, ? extends R> mapper) {
        return map(e -> mapper.apply(e.getKey(), e.getValue()));
    }