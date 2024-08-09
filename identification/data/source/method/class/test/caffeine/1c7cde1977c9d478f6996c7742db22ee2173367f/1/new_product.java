public void publishUpdated(Cache<K, V> cache, K key, V oldValue, V newValue) {
    publish(cache, EventType.UPDATED, key, oldValue, newValue, /* quiet */ false);
  }