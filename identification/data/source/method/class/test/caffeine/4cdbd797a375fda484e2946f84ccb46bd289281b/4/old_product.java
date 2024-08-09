@NonNull
  public Caffeine<K, V> refreshAfterWrite(long duration, @NonNull TimeUnit unit) {
    requireNonNull(unit);
    requireState(refreshNanos == UNSET_INT, "refresh was already set to %s ns", refreshNanos);
    requireArgument(duration > 0, "duration must be positive: %s %s", duration, unit);
    this.refreshNanos = unit.toNanos(duration);
    return this;
  }