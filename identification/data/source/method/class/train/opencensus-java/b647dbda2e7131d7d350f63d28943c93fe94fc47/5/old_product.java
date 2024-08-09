static MetricFamilySamples createMetricFamilySamples(Metric metric) {
    MetricDescriptor metricDescriptor = metric.getMetricDescriptor();
    String name = Collector.sanitizeMetricName(metricDescriptor.getName());
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