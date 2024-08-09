public void publishExpired(Cache<K, V> cache, K key, V value) {
    publish(new JCacheEntryEvent<>(cache, EventType.EXPIRED, key, value, null));
  }