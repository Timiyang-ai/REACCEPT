static MetricFamilySamples createMetricFamilySamples(ViewData viewData) {
    View view = viewData.getView();
    String name =
        Collector.sanitizeMetricName(OPENCENSUS_NAMESPACE + '_' + view.getName().asString());
    Type type = getType(view.getAggregation(), view.getWindow());
    List<Sample> samples = Lists.newArrayList();
    for (Entry<List</*@Nullable*/ TagValue>, AggregationData> entry :
        viewData.getAggregationMap().entrySet()) {
      samples.addAll(getSamples(name, view.getColumns(), entry.getKey(), entry.getValue()));
    }
    return new MetricFamilySamples(
        name, type, OPENCENSUS_HELP_MSG + view.getDescription(), samples);
  }