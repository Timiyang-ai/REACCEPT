@VisibleForTesting
  static MetricDescriptor.ValueType createValueType(Type type) {
    // TODO(mayurkale): decide what to do with Summary type.
    if (type == Type.CUMULATIVE_DOUBLE || type == Type.GAUGE_DOUBLE) {
      return MetricDescriptor.ValueType.DOUBLE;
    } else if (type == Type.GAUGE_INT64 || type == Type.CUMULATIVE_INT64) {
      return MetricDescriptor.ValueType.INT64;
    } else if (type == Type.GAUGE_DISTRIBUTION || type == Type.CUMULATIVE_DISTRIBUTION) {
      return MetricDescriptor.ValueType.DISTRIBUTION;
    }
    return MetricDescriptor.ValueType.UNRECOGNIZED;
  }