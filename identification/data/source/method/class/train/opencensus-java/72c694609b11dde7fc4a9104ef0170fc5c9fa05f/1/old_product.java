public static Timestamp fromMillis(long millis) {
    long seconds = millis / NUM_MILLIS_PER_SECOND;
    int nanos = (int) (millis % NUM_MILLIS_PER_SECOND) * NUM_NANOS_PER_MILLI;
    return new Timestamp(seconds, seconds < 0 ? -nanos : nanos);
  }