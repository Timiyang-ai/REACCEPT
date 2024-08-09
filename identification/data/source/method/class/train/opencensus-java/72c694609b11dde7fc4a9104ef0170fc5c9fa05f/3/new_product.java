public static Duration create(long seconds, int nanos) {
    if (seconds < -MAX_SECONDS) {
      throw new IllegalArgumentException(
          "'seconds' is less than minimum (" + -MAX_SECONDS + "): " + seconds);
    }
    if (seconds > MAX_SECONDS) {
      throw new IllegalArgumentException(
          "'seconds' is greater than maximum (" + MAX_SECONDS + "): " + seconds);
    }
    if (nanos < -MAX_NANOS) {
      throw new IllegalArgumentException(
          "'nanos' is less than minimum (" + -MAX_NANOS + "): " + nanos);
    }
    if (nanos > MAX_NANOS) {
      throw new IllegalArgumentException(
          "'nanos' is greater than maximum (" + MAX_NANOS + "): " + nanos);
    }
    if ((seconds < 0 && nanos > 0) || (seconds > 0 && nanos < 0)) {
      throw new IllegalArgumentException(
          "'seconds' and 'nanos' have inconsistent sign: seconds=" + seconds + ", nanos=" + nanos);
    }
    return new AutoValue_Duration(seconds, nanos);
  }