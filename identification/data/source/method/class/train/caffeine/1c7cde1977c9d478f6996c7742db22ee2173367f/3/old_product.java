public void publishCreated(Cache<K, V> cache, K key, V value) {
    CacheEntryEvent<K, V> event = new JCacheEntryEvent<>(
        cache, EventType.CREATED, key, null, value);
    publish(event, listener -> listener.onCreated(Collections.singleton(event)));
  }