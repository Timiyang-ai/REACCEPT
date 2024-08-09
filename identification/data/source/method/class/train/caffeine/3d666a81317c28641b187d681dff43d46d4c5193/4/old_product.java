public void publishRemoved(Cache<K, V> cache, K key, V value) {
    if (dispatchQueues.isEmpty()) {
      return;
    }
    publish(new JCacheEntryEvent<>(cache, EventType.REMOVED, key, null, value), false);
  }