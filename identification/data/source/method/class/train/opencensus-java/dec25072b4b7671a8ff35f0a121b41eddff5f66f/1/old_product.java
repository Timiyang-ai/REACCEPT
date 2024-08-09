@VisibleForTesting
  static MetricDescriptor.ValueType createValueType(
      Aggregation aggregation, final Measure measure) {
    return aggregation.match(
        Functions.returnConstant(
            measure.match(
                VALUE_TYPE_DOUBLE_FUNCTION, // Sum Double
                VALUE_TYPE_INT64_FUNCTION, // Sum Long
                VALUE_TYPE_UNRECOGNIZED_FUNCTION)),
        VALUE_TYPE_INT64_FUNCTION, // Count
        VALUE_TYPE_DISTRIBUTION_FUNCTION, // Distribution
        Functions.returnConstant(
            measure.match(
                VALUE_TYPE_DOUBLE_FUNCTION, // LastValue Double
                VALUE_TYPE_INT64_FUNCTION, // LastValue Long
                VALUE_TYPE_UNRECOGNIZED_FUNCTION)),
        valueTypeMeanFunction);
  }