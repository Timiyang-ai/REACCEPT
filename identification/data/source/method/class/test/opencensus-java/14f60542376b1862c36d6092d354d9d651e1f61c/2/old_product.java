@VisibleForTesting
  static MetricKind createMetricKind(View.AggregationWindow window, Aggregation aggregation) {
    if (aggregation instanceof LastValue) {
      return MetricKind.GAUGE;
    }
    return window.match(
        METRIC_KIND_CUMULATIVE_FUNCTION, // Cumulative
        // TODO(songya): We don't support exporting Interval stats to StackDriver in this version.
        METRIC_KIND_UNRECOGNIZED_FUNCTION, // Interval
        METRIC_KIND_UNRECOGNIZED_FUNCTION);
  }