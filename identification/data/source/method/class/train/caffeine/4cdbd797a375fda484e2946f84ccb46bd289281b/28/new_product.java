@NonNull
  public Caffeine<K, V> expireAfterWrite(long duration, @NonNull TimeUnit unit) {
    requireState(expireAfterWriteNanos == UNSET_INT,
        "expireAfterWrite was already set to %s ns", expireAfterWriteNanos);
    requireState(expiry == null, "expireAfterAccess may not be used with variable expiration");
    requireArgument(duration >= 0, "duration cannot be negative: %s %s", duration, unit);
    this.expireAfterWriteNanos = unit.toNanos(duration);
    return this;
  }