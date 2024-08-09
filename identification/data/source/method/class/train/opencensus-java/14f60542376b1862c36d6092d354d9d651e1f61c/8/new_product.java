@VisibleForTesting
  static Metric createMetric(
      io.opencensus.metrics.export.MetricDescriptor metricDescriptor,
      List<LabelValue> labelValues,
      String domain,
      Map<LabelKey, LabelValue> constantLabels) {
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
    for (Map.Entry<LabelKey, LabelValue> constantLabel : constantLabels.entrySet()) {
      String constantLabelKey = constantLabel.getKey().getKey();
      String constantLabelValue = constantLabel.getValue().getValue();
      constantLabelValue = constantLabelValue == null ? "" : constantLabelValue;
      stringTagMap.put(constantLabelKey, constantLabelValue);
    }
    builder.putAllLabels(stringTagMap);
    return builder.build();
  }