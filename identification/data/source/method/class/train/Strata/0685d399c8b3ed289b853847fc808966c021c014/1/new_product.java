public MapStream<K, V> filter(BiPredicate<? super K, ? super V> predicate) {
    return wrap(underlying.filter(e -> predicate.test(e.getKey(), e.getValue())));
  }