public final Cache2kBuilder<K,V> maximizeConcurrency(boolean f) {
    config().setMaximizeConcurrency(f);
    return this;
  }