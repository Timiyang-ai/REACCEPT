@NonNull
  public Caffeine<K, V> expireAfterAccess(@NonNull Duration duration) {
    return expireAfterAccess(duration.toNanos(), TimeUnit.NANOSECONDS);
  }