public void publishRemoved(Cache<K, V> cache, K key, V value) {
    CacheEntryEvent<K, V> event = new JCacheEntryEvent<>(
        cache, EventType.REMOVED, key, value, null);
    publish(event, listener -> listener.onRemoved(Collections.singleton(event)));
  }