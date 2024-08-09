@VisibleForTesting
  static MetricKind createMetricKind(Type type) {
    if (type == Type.GAUGE_INT64 || type == Type.GAUGE_DOUBLE) {
      return MetricKind.GAUGE;
    } else if (type == Type.CUMULATIVE_INT64
        || type == Type.CUMULATIVE_DOUBLE
        || type == Type.CUMULATIVE_DISTRIBUTION) {
      return MetricKind.CUMULATIVE;
    }
    return MetricKind.UNRECOGNIZED;
  }