public void publishUpdated(Cache<K, V> cache, K key, V oldValue, V newValue) {
    publish(new JCacheEntryEvent<>(cache, EventType.UPDATED, key, oldValue, newValue));
  }