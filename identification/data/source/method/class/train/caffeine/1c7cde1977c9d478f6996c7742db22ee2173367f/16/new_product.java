public void publishExpired(Cache<K, V> cache, K key, V value) {
    publish(cache, EventType.EXPIRED, key, /* hasOldValue */ true,
        /* oldValue */ value, /* newValue */ value, /* quiet */ false);
  }