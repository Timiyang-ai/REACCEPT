public void publishUpdated(Cache<K, V> cache, K key, V oldValue, V newValue) {
    JCacheEntryEvent<K, V> event = new JCacheEntryEvent<>(
        cache, EventType.UPDATED, key, oldValue, newValue);
    publish(event, listener -> listener.onUpdated(event));
  }