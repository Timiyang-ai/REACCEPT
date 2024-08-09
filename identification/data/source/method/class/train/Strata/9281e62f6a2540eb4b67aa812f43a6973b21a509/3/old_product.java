public ImmutableListMultimap<K, V> toListMultimap() {
    return underlying.collect(Guavate.toImmutableListMultimap(e -> e.getKey(), e -> e.getValue()));
  }