public void publishCreated(Cache<K, V> cache, K key, V value) {
    publish(cache, EventType.CREATED, key, /* hasOldValue */ false,
        /* oldValue */ null, /* newValue */ value, /* quiet */ false);
  }