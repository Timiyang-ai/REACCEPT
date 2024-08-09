public static Duration create(long seconds, int nanos) {
    if (seconds < -MAX_SECONDS || seconds > MAX_SECONDS) {
      return new Duration(0, 0);
    }
    if (nanos < -MAX_NANOS || nanos > MAX_NANOS) {
      return new Duration(0, 0);
    }
    if ((seconds < 0 && nanos > 0) || (seconds > 0 && nanos < 0)) {
      return new Duration(0, 0);
    }
    return new Duration(seconds, nanos);
  }