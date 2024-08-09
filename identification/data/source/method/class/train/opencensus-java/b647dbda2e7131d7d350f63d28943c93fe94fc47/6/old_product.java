static MetricFamilySamples createDescribableMetricFamilySamples(View view) {
    String name =
        Collector.sanitizeMetricName(OPENCENSUS_NAMESPACE + '_' + view.getName().asString());
    Type type = getType(view.getAggregation(), view.getWindow());
    List<String> labelNames = convertToLabelNames(view.getColumns());
    if (containsDisallowedLeLabelForHistogram(labelNames, type)) {
      throw new IllegalStateException(
          "Prometheus Histogram cannot have a label named 'le', "
              + "because it is a reserved label for bucket boundaries. "
              + "Please remove this tag key from your view.");
    }
    return new MetricFamilySamples(
        name, type, OPENCENSUS_HELP_MSG + view.getDescription(), Collections.<Sample>emptyList());
  }