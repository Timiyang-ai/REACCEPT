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
        Functions.returnConstant(MetricDescriptor.ValueType.DISTRIBUTION), // Distribution
        Functions.returnConstant(
            measure.match(
                Functions.returnConstant(MetricDescriptor.ValueType.DOUBLE), // LastValue Double
                Functions.returnConstant(MetricDescriptor.ValueType.INT64), // LastValue Long
                Functions.returnConstant(MetricDescriptor.ValueType.UNRECOGNIZED))),
        new Function<Aggregation, MetricDescriptor.ValueType>() {
          @Override
          public MetricDescriptor.ValueType apply(Aggregation arg) {
            // TODO(songya): remove this once Mean aggregation is completely removed. Before that
            // we need to continue supporting Mean, since it could still be used by users and some
            // deprecated RPC views.
            if (arg instanceof Aggregation.Mean) {
              return MetricDescriptor.ValueType.DOUBLE;
            }
            return MetricDescriptor.ValueType.UNRECOGNIZED;
          }
        });
  }