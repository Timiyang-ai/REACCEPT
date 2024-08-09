@NonNull
  public Caffeine<K, V> expireAfterWrite(@NonNull Duration duration) {
    return expireAfterWrite(duration.toNanos(), TimeUnit.NANOSECONDS);
  }