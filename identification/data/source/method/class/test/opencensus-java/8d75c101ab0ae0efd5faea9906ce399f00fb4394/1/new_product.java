public static Metric create(MetricDescriptor metricDescriptor, List<TimeSeries> timeSeriesList) {
    Utils.checkListElementNotNull(
        Utils.checkNotNull(timeSeriesList, "timeSeriesList"), "timeSeries");
    return createInternal(
        metricDescriptor, Collections.unmodifiableList(new ArrayList<TimeSeries>(timeSeriesList)));
  }