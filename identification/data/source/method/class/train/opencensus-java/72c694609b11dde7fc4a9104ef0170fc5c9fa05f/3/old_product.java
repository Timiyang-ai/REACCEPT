public static Duration create(long seconds, int nanos) {
    if (seconds < -MAX_SECONDS || seconds > MAX_SECONDS) {
      return ZERO;
    }
    if (nanos < -MAX_NANOS || nanos > MAX_NANOS) {
      return ZERO;
    }
    if ((seconds < 0 && nanos > 0) || (seconds > 0 && nanos < 0)) {
      return ZERO;
    }
    return new AutoValue_Duration(seconds, nanos);
  }