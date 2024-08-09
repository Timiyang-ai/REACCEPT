@NonNull
  public Caffeine<K, V> expireAfterAccess(long duration, @NonNull TimeUnit unit) {
    requireState(expireAfterAccessNanos == UNSET_INT,
        "expireAfterAccess was already set to %s ns", expireAfterAccessNanos);
    requireState(expiry == null, "expireAfterAccess may not be used with variable expiration");
    requireArgument(duration >= 0, "duration cannot be negative: %s %s", duration, unit);
    this.expireAfterAccessNanos = unit.toNanos(duration);
    return this;
  }