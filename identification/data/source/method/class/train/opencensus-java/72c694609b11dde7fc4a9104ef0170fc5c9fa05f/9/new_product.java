public static Duration fromMillis(long millis) {
    long seconds = millis / MILLIS_PER_SECOND;
    int nanos = (int) (millis % MILLIS_PER_SECOND * NANOS_PER_MILLI);
    return Duration.create(seconds, nanos);
  }