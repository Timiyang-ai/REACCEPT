@VisibleForTesting
  static Point createPoint(
      AggregationData aggregationData,
      ViewData.AggregationWindowData windowData,
      Aggregation aggregation) {
    Point.Builder builder = Point.newBuilder();
    builder.setInterval(createTimeInterval(windowData, aggregation));
    builder.setValue(createTypedValue(aggregation, aggregationData));
    return builder.build();
  }