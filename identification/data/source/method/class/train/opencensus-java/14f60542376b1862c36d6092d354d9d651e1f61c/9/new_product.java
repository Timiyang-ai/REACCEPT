static MetricDescriptor createMetricDescriptor(
      io.opencensus.metrics.export.MetricDescriptor metricDescriptor,
      String projectId,
      String domain,
      String displayNamePrefix) {

    MetricDescriptor.Builder builder = MetricDescriptor.newBuilder();
    String type = generateType(metricDescriptor.getName(), domain);
    // Name format refers to
    // cloud.google.com/monitoring/api/ref_v3/rest/v3/projects.metricDescriptors/create
    builder.setName("projects/" + projectId + "/metricDescriptors/" + type);
    builder.setType(type);
    builder.setDescription(metricDescriptor.getDescription());
    builder.setDisplayName(createDisplayName(metricDescriptor.getName(), displayNamePrefix));
    for (LabelKey labelKey : metricDescriptor.getLabelKeys()) {
      builder.addLabels(createLabelDescriptor(labelKey));
    }
    builder.addLabels(
        LabelDescriptor.newBuilder()
            .setKey(OPENCENSUS_TASK)
            .setDescription(OPENCENSUS_TASK_DESCRIPTION)
            .setValueType(ValueType.STRING)
            .build());

    builder.setUnit(metricDescriptor.getUnit());
    builder.setMetricKind(createMetricKind(metricDescriptor.getType()));
    builder.setValueType(createValueType(metricDescriptor.getType()));
    return builder.build();
  }