public <R> Stream<R> flatMap(BiFunction<? super K, ? super V, Stream<? extends R>> mapper) {
    return underlying.flatMap(e -> mapper.apply(e.getKey(), e.getValue()));
  }