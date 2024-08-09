@NonNull
  public Caffeine<K, V> refreshAfterWrite(@NonNull Duration duration) {
    return refreshAfterWrite(saturatedToNanos(duration), TimeUnit.NANOSECONDS);
  }