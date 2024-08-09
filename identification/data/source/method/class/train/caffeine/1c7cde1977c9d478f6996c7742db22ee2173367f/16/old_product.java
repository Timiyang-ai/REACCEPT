public void publishExpired(Cache<K, V> cache, K key, V value) {
    publish(cache, EventType.EXPIRED, key, value, /* newValue */ null, /* quiet */ false);
  }