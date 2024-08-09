public void deregister(CacheEntryListenerConfiguration<K, V> configuration) {
    requireNonNull(configuration);

    for (Iterator<Registration<K, V>> iter = dispatchQueues.keySet().iterator(); iter.hasNext();) {
      Registration<K, V> registration = iter.next();
      if (configuration.equals(registration.getConfiguration())) {
        iter.remove();
        return;
      }
    }
  }