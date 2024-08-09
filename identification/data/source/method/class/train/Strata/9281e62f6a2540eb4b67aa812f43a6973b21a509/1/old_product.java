public Stream<K> keys() {
    return underlying.map(e -> e.getKey());
  }