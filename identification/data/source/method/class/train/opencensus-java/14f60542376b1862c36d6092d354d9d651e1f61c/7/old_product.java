@VisibleForTesting
  static Metric createMetric(View view, List<? extends TagValue> tagValues) {
    Metric.Builder builder = Metric.newBuilder();
    // TODO(songya): use pre-defined metrics for canonical views
    builder.setType(
        String.format("custom.googleapis.com/opencensus/%s", view.getName().asString()));
    Map<String, String> stringTagMap = Maps.newHashMap();
    List<TagKey> columns = view.getColumns();
    checkArgument(
        tagValues.size() == columns.size(), "TagKeys and TagValues don't have same size.");
    for (int i = 0; i < tagValues.size(); i++) {
      TagKey key = columns.get(i);
      TagValue value = tagValues.get(i);
      stringTagMap.put(key.getName(), value.asString());
    }
    builder.putAllLabels(stringTagMap);
    return builder.build();
  }