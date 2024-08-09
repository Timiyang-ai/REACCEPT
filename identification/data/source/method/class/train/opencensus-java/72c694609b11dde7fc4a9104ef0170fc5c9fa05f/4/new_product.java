public static Timestamp fromMillis(long epochMilli) {
    long secs = floorDiv(epochMilli, MILLIS_PER_SECOND);
    int mos = (int) floorMod(epochMilli, MILLIS_PER_SECOND);
    return create(secs, (int) (mos * NANOS_PER_MILLI)); // Safe int * NANOS_PER_MILLI
  }