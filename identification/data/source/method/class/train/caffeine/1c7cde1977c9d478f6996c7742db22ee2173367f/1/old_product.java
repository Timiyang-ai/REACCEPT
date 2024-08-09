public void publishExpired(Cache<K, V> cache, K key, V value) {
    JCacheEntryEvent<K, V> event = new JCacheEntryEvent<>(
        cache, EventType.EXPIRED, key, value, null);
    publish(event, listener -> listener.onExpired(event));
  }