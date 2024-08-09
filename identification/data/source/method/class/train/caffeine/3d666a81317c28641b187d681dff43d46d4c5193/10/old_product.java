public void publishRemoved(Cache<K, V> cache, K key, V value) {
    publish(cache, EventType.REMOVED, key, /* oldValue */ null, value, /* quiet */ false);
  }