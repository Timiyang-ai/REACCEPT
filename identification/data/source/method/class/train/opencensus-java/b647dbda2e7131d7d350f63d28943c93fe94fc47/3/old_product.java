static MetricFamilySamples createDescribableMetricFamilySamples(View view) {
    String name =
        Collector.sanitizeMetricName(OPENCENSUS_NAMESPACE + '_' + view.getName().asString());
    Type type = getType(view.getAggregation(), view.getWindow());
    return new MetricFamilySamples(
        name, type, OPENCENSUS_HELP_MSG + view.getDescription(), Collections.<Sample>emptyList());
  }