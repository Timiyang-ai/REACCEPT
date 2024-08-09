@VisibleForTesting
  static MetricKind createMetricKind(View.AggregationWindow window, Aggregation aggregation) {
    if (aggregation instanceof LastValue) {
      return MetricKind.GAUGE;
    }
    return window.match(
        Functions.returnConstant(MetricKind.CUMULATIVE), // Cumulative
        // TODO(songya): We don't support exporting Interval stats to StackDriver in this version.
        Functions.returnConstant(MetricKind.UNRECOGNIZED), // Interval
        Functions.returnConstant(MetricKind.UNRECOGNIZED));
  }