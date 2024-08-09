public void publishRemoved(Cache<K, V> cache, K key, V value) {
    JCacheEntryEvent<K, V> event = new JCacheEntryEvent<>(
        cache, EventType.REMOVED, key, null, value);
    publish(event, listener -> listener.onRemoved(event));
  }