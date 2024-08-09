@javax.annotation.Nullable
  static MetricDescriptor createMetricDescriptor(View view, String projectId) {
    if (!(view.getWindow() instanceof View.AggregationWindow.Cumulative)) {
      // TODO(songya): Only Cumulative view will be exported to Stackdriver in this version.
      return null;
    }

    MetricDescriptor.Builder builder = MetricDescriptor.newBuilder();
    String viewName = view.getName().asString();
    String type = generateType(viewName);
    // Name format refers to
    // cloud.google.com/monitoring/api/ref_v3/rest/v3/projects.metricDescriptors/create
    builder.setName(String.format("projects/%s/metricDescriptors/%s", projectId, type));
    builder.setType(type);
    builder.setDescription(view.getDescription());
    builder.setDisplayName("OpenCensus/" + viewName);
    for (TagKey tagKey : view.getColumns()) {
      builder.addLabels(createLabelDescriptor(tagKey));
    }
    builder.addLabels(
        LabelDescriptor.newBuilder()
            .setKey(OPENCENSUS_TASK)
            .setDescription(OPENCENSUS_TASK_DESCRIPTION)
            .setValueType(ValueType.STRING)
            .build());
    builder.setUnit(createUnit(view.getAggregation(), view.getMeasure()));
    builder.setMetricKind(createMetricKind(view.getWindow()));
    builder.setValueType(createValueType(view.getAggregation(), view.getMeasure()));
    return builder.build();
  }