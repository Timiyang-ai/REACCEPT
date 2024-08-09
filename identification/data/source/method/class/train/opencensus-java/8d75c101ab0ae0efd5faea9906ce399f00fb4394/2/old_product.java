public static Metric create(MetricDescriptor metricDescriptor, TimeSeriesList timeSeriesList) {
    checkTypeMatch(metricDescriptor.getType(), timeSeriesList);
    return new AutoValue_Metric(metricDescriptor, timeSeriesList);
  }