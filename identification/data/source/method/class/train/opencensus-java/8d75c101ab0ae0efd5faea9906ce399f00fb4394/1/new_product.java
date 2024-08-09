public static Metric create(MetricDescriptor metricDescriptor, List<TimeSeries> timeSeriesList) {
    Utils.checkNotNull(metricDescriptor, "metricDescriptor");
    Utils.checkNotNull(timeSeriesList, "timeSeriesList");
    Utils.checkListElementNotNull(timeSeriesList, "timeSeries");
    checkTypeMatch(metricDescriptor.getType(), timeSeriesList);
    return new AutoValue_Metric(
        metricDescriptor, Collections.unmodifiableList(new ArrayList<TimeSeries>(timeSeriesList)));
  }