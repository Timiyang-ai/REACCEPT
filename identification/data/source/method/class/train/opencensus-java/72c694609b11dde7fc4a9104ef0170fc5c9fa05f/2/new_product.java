public static Timestamp create(long seconds, int nanos) {
    if (seconds < -MAX_SECONDS) {
      throw new IllegalArgumentException(
          "'seconds' is less than minimum (" + -MAX_SECONDS + "): " + seconds);
    }
    if (seconds > MAX_SECONDS) {
      throw new IllegalArgumentException(
          "'seconds' is greater than maximum (" + MAX_SECONDS + "): " + seconds);
    }
    if (nanos < 0) {
      throw new IllegalArgumentException("'nanos' is less than zero: " + nanos);
    }
    if (nanos > MAX_NANOS) {
      throw new IllegalArgumentException(
          "'nanos' is greater than maximum (" + MAX_NANOS + "): " + nanos);
    }
    return new AutoValue_Timestamp(seconds, nanos);
  }