@NonNull
  public Caffeine<K, V> expireAfterWrite(@NonNull Duration duration) {
    return expireAfterWrite(saturatedToNanos(duration), TimeUnit.NANOSECONDS);
  }