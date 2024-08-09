@VisibleForTesting
  static Metric createMetric(
      io.opencensus.metrics.export.MetricDescriptor metricDescriptor,
      List<LabelValue> labelValues,
      String domain) {
    Metric.Builder builder = Metric.newBuilder();
    builder.setType(generateType(metricDescriptor.getName(), domain));
    Map<String, String> stringTagMap = Maps.newHashMap();
    List<LabelKey> labelKeys = metricDescriptor.getLabelKeys();
    for (int i = 0; i < labelValues.size(); i++) {
      String value = labelValues.get(i).getValue();
      if (value == null) {
        continue;
      }
      stringTagMap.put(labelKeys.get(i).getKey(), value);
    }
    stringTagMap.put(OPENCENSUS_TASK, OPENCENSUS_TASK_VALUE_DEFAULT);
    builder.putAllLabels(stringTagMap);
    return builder.build();
  }