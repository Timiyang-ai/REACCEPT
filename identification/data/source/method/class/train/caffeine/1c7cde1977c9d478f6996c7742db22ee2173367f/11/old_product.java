public void publishExpired(Cache<K, V> cache, K key, V value) {
    CacheEntryEvent<K, V> event = new JCacheEntryEvent<>(
        cache, EventType.EXPIRED, key, value, null);
    publish(event, listener -> listener.onExpired(Collections.singleton(event)));
  }