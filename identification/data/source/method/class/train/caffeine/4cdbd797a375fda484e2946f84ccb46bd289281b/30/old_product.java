@NonNull
  public Caffeine<K, V> refreshAfterWrite(@NonNull Duration duration) {
    return refreshAfterWrite(duration.toNanos(), TimeUnit.NANOSECONDS);
  }