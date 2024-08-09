public void publishUpdated(Cache<K, V> cache, K key, V oldValue, V newValue) {
    publish(cache, EventType.UPDATED, key, /* hasOldValue */ true,
        oldValue, newValue, /* quiet */ false);
  }