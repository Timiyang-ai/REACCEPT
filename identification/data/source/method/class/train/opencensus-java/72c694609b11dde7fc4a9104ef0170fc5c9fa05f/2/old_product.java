public static Timestamp create(long seconds, int nanos) {
    if (seconds < -MAX_SECONDS || seconds > MAX_SECONDS) {
      return EPOCH;
    }
    if (nanos < 0 || nanos > MAX_NANOS) {
      return EPOCH;
    }
    return new AutoValue_Timestamp(seconds, nanos);
  }