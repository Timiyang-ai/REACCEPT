@VisibleForTesting
  static MetricDescriptor.ValueType createValueType(
      Aggregation aggregation, final Measure measure) {
    return aggregation.match(
        Functions.returnConstant(
            measure.match(
                Functions.returnConstant(MetricDescriptor.ValueType.DOUBLE), // Sum Double
                Functions.returnConstant(MetricDescriptor.ValueType.INT64), // Sum Long
                Functions.returnConstant(MetricDescriptor.ValueType.UNRECOGNIZED))),
        Functions.returnConstant(MetricDescriptor.ValueType.INT64), // Count
        Functions.returnConstant(MetricDescriptor.ValueType.DOUBLE), // Mean
        Functions.returnConstant(MetricDescriptor.ValueType.DISTRIBUTION), // Distribution
        Functions.returnConstant(MetricDescriptor.ValueType.UNRECOGNIZED));
  }