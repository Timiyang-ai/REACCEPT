@Override
  public int compareTo(TtlBucket ttlBucket) {
    long startTime1 = getTtlIntervalStartTimeMs();
    long startTime2 = ttlBucket.getTtlIntervalStartTimeMs();

    if (startTime1 < startTime2) {
      return -1;
    }
    if (startTime1 == startTime2) {
      return 0;
    }
    return 1;
  }