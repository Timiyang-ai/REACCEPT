@VisibleForTesting
  static Point createPoint(
      AggregationData aggregationData, AggregationWindowData windowData, Aggregation aggregation) {
    Point.Builder builder = Point.newBuilder();
    builder.setInterval(createTimeInterval(windowData));
    builder.setValue(createTypedValue(aggregation, aggregationData));
    return builder.build();
  }