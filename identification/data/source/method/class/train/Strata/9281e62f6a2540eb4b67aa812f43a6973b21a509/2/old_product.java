public Stream<V> values() {
    return underlying.map(e -> e.getValue());
  }