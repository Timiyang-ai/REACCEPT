static MetricFamilySamples createDescribableMetricFamilySamples(
      MetricDescriptor metricDescriptor, String namespace) {
    String name = getNamespacedName(metricDescriptor.getName(), namespace);
    Type type = getType(metricDescriptor.getType());
    List<String> labelNames = convertToLabelNames(metricDescriptor.getLabelKeys());

    if (containsDisallowedLeLabelForHistogram(labelNames, type)) {
      throw new IllegalStateException(
          "Prometheus Histogram cannot have a label named 'le', "
              + "because it is a reserved label for bucket boundaries. "
              + "Please remove this key from your view.");
    }

    if (containsDisallowedQuantileLabelForSummary(labelNames, type)) {
      throw new IllegalStateException(
          "Prometheus Summary cannot have a label named 'quantile', "
              + "because it is a reserved label. Please remove this key from your view.");
    }

    return new MetricFamilySamples(
        name, type, metricDescriptor.getDescription(), Collections.<Sample>emptyList());
  }