@Nonnull
  public Caffeine<K, V> recordStats() {
    requireState(this.statsCounterSupplier == null, "Statistics recording was already set");
    statsCounterSupplier = ENABLED_STATS_COUNTER_SUPPLIER;
    return this;
  }