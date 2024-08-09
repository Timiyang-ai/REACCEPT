public <R> Stream<R> flatMap(BiFunction<? super K, ? super V, Stream<R>> mapper) {
    return underlying.flatMap(e -> mapper.apply(e.getKey(), e.getValue()));
  }