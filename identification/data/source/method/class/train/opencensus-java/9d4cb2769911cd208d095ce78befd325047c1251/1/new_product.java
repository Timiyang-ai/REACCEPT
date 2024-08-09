@VisibleForTesting
  static Timestamp convertTimestamp(io.opencensus.common.Timestamp censusTimestamp) {
    if (censusTimestamp.getSeconds() < 0) {
      // Stackdriver doesn't handle negative timestamps.
      return Timestamp.newBuilder().build();
    }
    return Timestamp.newBuilder()
        .setSeconds(censusTimestamp.getSeconds())
        .setNanos(censusTimestamp.getNanos())
        .build();
  }