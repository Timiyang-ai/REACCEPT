@Nonnull
  public Caffeine<K, V> executor(@Nonnull Executor executor) {
    this.executor = requireNonNull(executor);
    return this;
  }