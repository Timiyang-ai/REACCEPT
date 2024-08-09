@VisibleForTesting
  static Point createPoint(
      io.opencensus.metrics.export.Point point,
      @javax.annotation.Nullable io.opencensus.common.Timestamp startTimestamp) {
    TimeInterval.Builder timeIntervalBuilder = TimeInterval.newBuilder();
    timeIntervalBuilder.setEndTime(convertTimestamp(point.getTimestamp()));
    if (startTimestamp != null) {
      timeIntervalBuilder.setStartTime(convertTimestamp(startTimestamp));
    }

    Point.Builder builder = Point.newBuilder();
    builder.setInterval(timeIntervalBuilder.build());
    builder.setValue(createTypedValue(point.getValue()));
    return builder.build();
  }