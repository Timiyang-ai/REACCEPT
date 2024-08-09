public void register(CacheEntryListenerConfiguration<K, V> configuration) {
    if (configuration.getCacheEntryListenerFactory() == null) {
      return;
    }
    EventTypeAwareListener<K, V> listener = new EventTypeAwareListener<>(
        configuration.getCacheEntryListenerFactory().create());

    CacheEntryEventFilter<K, V> filter = event -> true;
    if (configuration.getCacheEntryEventFilterFactory() != null) {
      filter = new EventTypeFilter<K, V>(listener,
          configuration.getCacheEntryEventFilterFactory().create());
    }

    Registration<K, V> registration = new Registration<>(configuration, filter, listener);
    dispatchQueues.putIfAbsent(registration, CompletableFuture.completedFuture(null));
  }