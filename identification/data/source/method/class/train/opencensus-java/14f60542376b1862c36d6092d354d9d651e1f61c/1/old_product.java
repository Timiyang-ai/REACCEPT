@Nullable
  static MetricDescriptor createMetricDescriptor(View view, String projectId) {
    if (!(view.getWindow() instanceof Cumulative)) {
      // TODO(songya): Only Cumulative view will be exported to Stackdriver in this version.
      return null;
    }

    MetricDescriptor.Builder builder = MetricDescriptor.newBuilder();
    String viewName = view.getName().asString();
    // Name format refers to
    // cloud.google.com/monitoring/api/ref_v3/rest/v3/projects.metricDescriptors/create
    builder.setName(String.format("projects/%s", projectId));
    builder.setType(String.format("custom.googleapis.com/opencensus/%s", viewName));
    builder.setDescription(view.getDescription());
    builder.setUnit(view.getMeasure().getUnit());
    builder.setDisplayName("OpenCensus/" + viewName);
    for (TagKey tagKey : view.getColumns()) {
      builder.addLabels(createLabelDescriptor(tagKey));
    }
    builder.setMetricKind(createMetricKind(view.getWindow()));
    builder.setValueType(createValueType(view.getAggregation(), view.getMeasure()));
    return builder.build();
  }