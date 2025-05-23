static MetricFamilySamples createMetricFamilySamples(ViewData viewData) {
    View view = viewData.getView();
    String name = Collector.sanitizeMetricName(view.getName().asString());
    Type type = getType(view.getAggregation(), view.getWindow());
    List<String> labelNames = convertToLabelNames(view.getColumns());
    List<Sample> samples = Lists.newArrayList();
    for (Entry<List</*@Nullable*/ TagValue>, AggregationData> entry :
        viewData.getAggregationMap().entrySet()) {
      samples.addAll(
          getSamples(name, labelNames, entry.getKey(), entry.getValue(), view.getAggregation()));
    }
    return new MetricFamilySamples(name, type, view.getDescription(), samples);
  }