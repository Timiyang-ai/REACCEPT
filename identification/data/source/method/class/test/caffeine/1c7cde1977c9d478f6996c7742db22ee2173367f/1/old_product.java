public void publishUpdated(Cache<K, V> cache, K key, V oldValue, V newValue) {
    if (dispatchQueues.isEmpty()) {
      return;
    }
    publish(new JCacheEntryEvent<>(cache, EventType.UPDATED, key, oldValue, newValue), false);
  }