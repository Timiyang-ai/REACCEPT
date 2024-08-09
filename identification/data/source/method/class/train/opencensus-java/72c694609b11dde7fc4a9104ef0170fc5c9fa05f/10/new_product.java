public static Timestamp create(long seconds, int nanos) {
    if (seconds < -MAX_SECONDS || seconds > MAX_SECONDS) {
      return new Timestamp(0, 0);
    }
    if (nanos < 0 || nanos > MAX_NANOS) {
      return new Timestamp(0, 0);
    }
    return new Timestamp(seconds, nanos);
  }