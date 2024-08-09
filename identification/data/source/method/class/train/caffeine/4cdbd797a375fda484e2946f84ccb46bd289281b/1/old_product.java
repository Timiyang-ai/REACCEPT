public Caffeine<K, V> recordStats() {
    statsCounterSupplier = ENABLED_STATS_COUNTER_SUPPLIER;
    return this;
  }