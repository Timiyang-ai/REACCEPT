public final Cache2kBuilder<K,V> boostConcurrency(boolean f) {
    config().setBoostConcurrency(f);
    return this;
  }