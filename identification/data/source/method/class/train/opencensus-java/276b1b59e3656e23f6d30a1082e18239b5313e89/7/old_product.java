static List<TimeSeries> createTimeSeriesList(
      io.opencensus.metrics.export.Metric metric,
      MonitoredResource monitoredResource,
      String domain) {
    List<TimeSeries> timeSeriesList = Lists.newArrayList();
    io.opencensus.metrics.export.MetricDescriptor metricDescriptor = metric.getMetricDescriptor();

    // Shared fields for all TimeSeries generated from the same Metric
    TimeSeries.Builder shared = TimeSeries.newBuilder();
    shared.setMetricKind(createMetricKind(metricDescriptor.getType()));
    shared.setResource(monitoredResource);
    shared.setValueType(createValueType(metricDescriptor.getType()));

    // Each entry in timeSeriesList will be converted into an independent TimeSeries object
    for (io.opencensus.metrics.export.TimeSeries timeSeries : metric.getTimeSeriesList()) {
      // TODO(mayurkale): Consider using setPoints instead of builder clone and addPoints.
      TimeSeries.Builder builder = shared.clone();
      builder.setMetric(createMetric(metricDescriptor, timeSeries.getLabelValues(), domain));

      io.opencensus.common.Timestamp startTimeStamp = timeSeries.getStartTimestamp();
      for (io.opencensus.metrics.export.Point point : timeSeries.getPoints()) {
        builder.addPoints(createPoint(point, startTimeStamp));
      }
      timeSeriesList.add(builder.build());
    }
    return timeSeriesList;
  }