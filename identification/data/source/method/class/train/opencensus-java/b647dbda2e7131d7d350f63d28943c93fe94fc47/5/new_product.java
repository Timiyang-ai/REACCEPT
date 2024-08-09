static MetricFamilySamples createMetricFamilySamples(Metric metric, String namespace) {
    MetricDescriptor metricDescriptor = metric.getMetricDescriptor();
    String name = getNamespacedName(metricDescriptor.getName(), namespace);
    Type type = getType(metricDescriptor.getType());
    List<String> labelNames = convertToLabelNames(metricDescriptor.getLabelKeys());
    List<Sample> samples = Lists.newArrayList();

    for (io.opencensus.metrics.export.TimeSeries timeSeries : metric.getTimeSeriesList()) {
      for (io.opencensus.metrics.export.Point point : timeSeries.getPoints()) {
        samples.addAll(getSamples(name, labelNames, timeSeries.getLabelValues(), point.getValue()));
      }
    }
    return new MetricFamilySamples(name, type, metricDescriptor.getDescription(), samples);
  }