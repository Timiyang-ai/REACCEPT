public static Timestamp fromMillis(long millis) {
    long seconds = millis / NUM_MILLIS_PER_SECOND;
    int nanos = (int) (millis % NUM_MILLIS_PER_SECOND) * NUM_NANOS_PER_MILLI;
    if (nanos < 0) {
      return new Timestamp(seconds - 1, (int) (nanos + NUM_NANOS_PER_SECOND));
    } else {
      return new Timestamp(seconds, nanos);
    }
  }