public Stream<V> values() {
    return underlying.map(Entry::getValue);
  }