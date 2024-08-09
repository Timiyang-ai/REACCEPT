public void publishCreated(Cache<K, V> cache, K key, V value) {
    publish(cache, EventType.CREATED, key, /* newValue */ null, value, /* quiet */ false);
  }