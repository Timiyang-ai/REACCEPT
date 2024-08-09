@Nonnull
  public Caffeine<K, V> refreshAfterWrite(@Nonnegative long duration, @Nonnull TimeUnit unit) {
    requireNonNull(unit);
    requireState(refreshNanos == UNSET_INT, "refresh was already set to %s ns", refreshNanos);
    requireArgument(duration > 0, "duration must be positive: %s %s", duration, unit);
    this.refreshNanos = unit.toNanos(duration);
    return this;
  }