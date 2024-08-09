public ImmutableListMultimap<K, V> toListMultimap() {
    return underlying.collect(Guavate.toImmutableListMultimap(Entry::getKey, Entry::getValue));
  }