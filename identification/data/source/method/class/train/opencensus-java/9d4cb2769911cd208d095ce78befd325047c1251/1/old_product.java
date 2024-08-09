@VisibleForTesting
  static Timestamp convertTimestamp(io.opencensus.common.Timestamp censusTimestamp) {
    return Timestamp.newBuilder()
        .setSeconds(censusTimestamp.getSeconds())
        .setNanos(censusTimestamp.getNanos())
        .build();
  }