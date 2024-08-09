@NonNull
  public Caffeine<K, V> expireAfterAccess(@NonNull Duration duration) {
    return expireAfterAccess(saturatedToNanos(duration), TimeUnit.NANOSECONDS);
  }