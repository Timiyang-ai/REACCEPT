public Stream<K> keys() {
    return underlying.map(Entry::getKey);
  }