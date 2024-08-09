public void deregister(CacheEntryListenerConfiguration<K, V> configuration) {
    requireNonNull(configuration);
    dispatchQueues.keySet().removeIf(registration ->
        configuration.equals(registration.getConfiguration()));
  }