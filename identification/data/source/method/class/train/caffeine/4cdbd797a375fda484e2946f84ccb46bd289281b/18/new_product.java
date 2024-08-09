@Nonnull
  public Caffeine<K, V> executor(@Nonnull Executor executor) {
    requireState(this.executor == null, "executor was already set to %s", this.executor);
    this.executor = requireNonNull(executor);
    return this;
  }