public MapStream<K, V> filter(BiFunction<? super K, ? super V, Boolean> predicate) {
    return wrap(underlying.filter(e -> predicate.apply(e.getKey(), e.getValue())));
  }