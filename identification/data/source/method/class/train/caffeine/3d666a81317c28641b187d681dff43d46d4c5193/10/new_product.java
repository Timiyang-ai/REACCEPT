public void publishRemoved(Cache<K, V> cache, K key, V value) {
    publish(cache, EventType.REMOVED, key, /* hasOldValue */ true,
        /* oldValue */ value, /* newValue */ value, /* quiet */ false);
  }