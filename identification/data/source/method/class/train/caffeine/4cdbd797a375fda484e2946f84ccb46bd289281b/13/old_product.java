public Caffeine<K, V> executor(Executor executor) {
    this.executor = requireNonNull(executor);
    return this;
  }