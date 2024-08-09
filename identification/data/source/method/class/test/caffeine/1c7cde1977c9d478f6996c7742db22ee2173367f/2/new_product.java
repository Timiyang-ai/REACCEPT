public void publishCreated(Cache<K, V> cache, K key, V value) {
    publish(new JCacheEntryEvent<>(cache, EventType.CREATED, key, null, value), false);
  }