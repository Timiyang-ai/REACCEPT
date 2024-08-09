@Nonnull
  public Caffeine<K, V> expireAfterAccess(@Nonnegative long duration, @Nonnull TimeUnit unit) {
    requireState(expireAfterAccessNanos == UNSET_INT,
        "expireAfterAccess was already set to %s ns", expireAfterAccessNanos);
    requireArgument(duration >= 0, "duration cannot be negative: %s %s", duration, unit);
    this.expireAfterAccessNanos = unit.toNanos(duration);
    return this;
  }