public static Metric create(MetricDescriptor metricDescriptor, List<TimeSeries> timeSeriesList) {
    checkTypeMatch(metricDescriptor.getType(), timeSeriesList);
    return new AutoValue_Metric(metricDescriptor, timeSeriesList);
  }